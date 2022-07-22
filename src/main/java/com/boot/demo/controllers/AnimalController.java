package com.boot.demo.controllers;

import com.boot.demo.model.Animal;
import com.boot.demo.model.User;
import com.boot.demo.repos.AnimalRepository;
import com.boot.demo.repos.UserRepository;
import com.boot.demo.service.AnimalService;
import com.boot.demo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;

@Controller
@RequestMapping("/animal")
public class AnimalController {
    AnimalService animalService;

    UserService userService;

    public AnimalController(AnimalService animalService, UserService userService) {
        this.animalService = animalService;
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public String showAnimal(@PathVariable("id") Long id, Model model) {
        Animal animal = animalService.findById(id);
        model.addAttribute("animal", animal);
        model.addAttribute("person", userService.current);
        return "animal";
    }

    @GetMapping("/patch/{id}")
    public String goToChange(Model model, @PathVariable("id") Long id) {
        model.addAttribute("animal", animalService.findById(id));
        return "changeAnimal";
    }

    @PatchMapping("/{id}")
    public String changeAnimal(@PathVariable("id") Long id, @Valid @ModelAttribute("animal") Animal animal, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "changeAnimal";
        }
        Animal old = animalService.findById(id);
        old.setName(animal.getName());
        old.setBirth(animal.getBirth());
        old.setSex(animal.getSex());
        animalService.saveAnimal(old);
        model.addAttribute("animal", animal);
        model.addAttribute("person", userService.current);
        return "animal";
    }

    @DeleteMapping("/{id}")
    public String deleteAnimal(@PathVariable("id") Long id, Model model) {
        animalService.deleteAnimal(animalService.findById(id));
        return "redirect:/person";
    }
}

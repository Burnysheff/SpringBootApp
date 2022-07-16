package com.boot.demo.controllers;

import com.boot.demo.model.Animal;
import com.boot.demo.model.User;
import com.boot.demo.repos.AnimalRepository;
import com.boot.demo.repos.UserRepository;
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
    AnimalRepository animalRepository;

    UserRepository userRepository;

    public AnimalController(AnimalRepository animalRepository, UserRepository userRepository) {
        this.animalRepository = animalRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/{id}")
    public String showAnimal(@PathVariable("id") Long id, Model model) {
        Animal animal = animalRepository.findById(id).get();
        model.addAttribute("animal", animal);
        model.addAttribute("person", PersonController.currentUser);
        return "animal";
    }

    @GetMapping("/patch/{id}")
    public String goToChange(Model model, @PathVariable("id") Long id) {
        model.addAttribute("animal", animalRepository.findById(id).get());
        return "changeAnimal";
    }

    @PatchMapping("/{id}")
    public String changeAnimal(@PathVariable("id") Long id, @Valid @ModelAttribute("animal") Animal animal, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "changeAnimal";
        }
        Animal old = animalRepository.findById(id).get();
        old.setName(animal.getName());
        old.setBirth(animal.getBirth());
        old.setSex(animal.getSex());
        animalRepository.save(old);
        model.addAttribute("animal", animal);
        model.addAttribute("person", PersonController.currentUser);
        return "animal";
    }

    @DeleteMapping("/{id}")
    public String deleteAnimal(@PathVariable("id") Long id, Model model) {
        animalRepository.delete(animalRepository.findById(id).get());
        return "redirect:/person/" + PersonController.currentUser.getId();
    }
}

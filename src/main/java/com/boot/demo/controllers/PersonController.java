package com.boot.demo.controllers;

import com.boot.demo.dto.AnimalCode;
import com.boot.demo.model.Animal;
import com.boot.demo.model.User;
import com.boot.demo.service.AnimalService;
import com.boot.demo.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/person")
public class PersonController {
    UserService userService;

    AnimalService animalService;

    public PersonController(UserService userService, AnimalService animalService) {
        this.userService = userService;
        this.animalService = animalService;
    }

    @GetMapping()
    public String main(Model model) {
        userService.current = userService.findUserById(((User)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId());
        model.addAttribute("person", userService.current);
        model.addAttribute("animalCode", new AnimalCode());
        return "person";
    }

    @GetMapping("/newAnimal")
    public String newAnimal(Model model) {
        model.addAttribute("person", userService.current);
        model.addAttribute("animal", new Animal());
        return "newAnimal";
    }

    @GetMapping("/listAnimal")
    public @ResponseBody String listAnimal(Model model) {
        List<Animal> animalList = animalService.findAllByOwnerId(userService.current.getId());
        model.addAttribute("person", userService.current);
        model.addAttribute("animalList", animalList);
        if (animalList.isEmpty()) {
            return "nothing";
        }
        return "animalList";
    }

    @PostMapping("/find")
    public String find(@Valid @ModelAttribute("animalCode") AnimalCode code, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("person", userService.current);
            return "person";
        }
        model.addAttribute("person", userService.current);
        if (animalService.checkPresentById(code.getValue())) {
            model.addAttribute("animal", animalService.findById(code.getValue()));
        } else {
            return "nothing";
        }
        return "animal";
    }

    @PostMapping("/animal")
    public String showAnimal(@Valid @ModelAttribute("animal") Animal animal, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("person", userService.current);
            return "newAnimal";
        }
        animal.setOwnerId(userService.current.getId());
        animalService.saveAnimal(animal);
        return "redirect:/person";
    }
}
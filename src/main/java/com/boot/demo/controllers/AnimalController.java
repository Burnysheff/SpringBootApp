package com.boot.demo.controllers;

import com.boot.demo.model.Animal;
import com.boot.demo.repos.AnimalRepository;
import com.boot.demo.repos.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
}

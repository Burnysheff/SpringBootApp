package com.boot.demo.controllers;

import com.boot.demo.model.Animal;
import com.boot.demo.model.User;
import com.boot.demo.repos.AnimalRepository;
import com.boot.demo.repos.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping("/{id}")
    public String deleteAnimal(@PathVariable("id") Long id, Model model) {
        System.out.println(id);
        animalRepository.delete(animalRepository.findById(id).get());
        return "redirect:/person/" + PersonController.currentUser.getId();
    }
}

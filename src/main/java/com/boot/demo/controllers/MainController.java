package com.boot.demo.controllers;

import com.boot.demo.model.Animal;
import com.boot.demo.model.User;
import com.boot.demo.repos.AnimalRepository;
import com.boot.demo.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/person")
public class MainController {
    UserRepository personRepository;

    AnimalRepository animalRepository;

    public MainController(UserRepository repository, AnimalRepository animalRepository) {
        this.personRepository = repository;
        this.animalRepository = animalRepository;
    }

    @GetMapping()
    public String main(Model model) {
        model.addAttribute("animal", new Animal());
        return "person";
    }

    @GetMapping("/newPerson")
    public String greeting(Model model) {
        model.addAttribute("person", new User());
        return "newPerson";
    }

    @GetMapping("/newAnimal")
    public String newAnimal(Model model) {
        model.addAttribute("animal", new Animal());
        return "newAnimal";
    }
    @PostMapping()
    public String addPerson(@Valid @ModelAttribute("person") User user, BindingResult result) {
        if (result.hasErrors()) {
            return "newPerson";
        }
        personRepository.save(user);
        return "redirect:/person";
    }

    @PostMapping("/find")
    public String find(@ModelAttribute("animal") Animal animal, Model model) {
        if (animalRepository.findById(animal.getId()).isPresent()) {
            model.addAttribute("animal", animalRepository.findById(animal.getId()).get());
        } else {
            model.addAttribute("animal", new Animal());
        }
        System.out.println(model.getAttribute("animal"));
        return "animal";
    }

    @PostMapping("/animal")
    public String showAnimal(@Valid @ModelAttribute("animal") Animal animal, BindingResult result) {
        if (result.hasErrors()) {
            return "newAnimal";
        }
        animalRepository.save(animal);
        return "redirect:/person";
    }
}
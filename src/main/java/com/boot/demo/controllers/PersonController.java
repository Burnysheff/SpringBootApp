package com.boot.demo.controllers;

import com.boot.demo.dto.AnimalCode;
import com.boot.demo.model.Animal;
import com.boot.demo.model.User;
import com.boot.demo.repos.AnimalRepository;
import com.boot.demo.repos.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/person/{id}")
public class PersonController {
    UserRepository personRepository;

    AnimalRepository animalRepository;

    static User currentUser;

    public PersonController(UserRepository repository, AnimalRepository animalRepository) {
        this.personRepository = repository;
        this.animalRepository = animalRepository;
    }

    @GetMapping()
    public String main(@PathVariable("id") Long id,  Model model) {
        currentUser = personRepository.findById(id).get();
        model.addAttribute("person", currentUser);
        model.addAttribute("animalCode", new AnimalCode());
        return "person";
    }

    @GetMapping("/newAnimal")
    public String newAnimal(Model model) {
        model.addAttribute("person", currentUser);
        model.addAttribute("animal", new Animal());
        return "newAnimal";
    }

    @GetMapping("/listAnimal")
    public String listAnimal(Model model) {
        List<Animal> animalList = animalRepository.findAllByOwnerId(currentUser.getId());
        model.addAttribute("person", currentUser);
        model.addAttribute("animalList", animalList);
        return "animalList";
    }

    @PostMapping()
    public String addPerson(@Valid @ModelAttribute("person") User user, BindingResult result) {
        if (result.hasErrors()) {
            return "newPerson";
        }
        personRepository.save(user);
        return "redirect:/person/" + currentUser.getId();
    }

    @PostMapping("/find")
    public String find(@Valid @ModelAttribute("animalCode") AnimalCode code, BindingResult result, Model model) {
        if (animalRepository.findById(code.getValue()).isPresent()) {
            model.addAttribute("animal", animalRepository.findById(code.getValue()).get());
        } else {
            model.addAttribute("animal", new Animal());
        }
        model.addAttribute("person", currentUser);
        return "animal";
    }

    @PostMapping("/animal")
    public String showAnimal(@Valid @ModelAttribute("animal") Animal animal, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "newAnimal";
        }
        animal.setOwnerId(currentUser.getId());
        animalRepository.save(animal);
        return "redirect:/person/" + currentUser.getId();
    }
}
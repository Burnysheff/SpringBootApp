package com.boot.demo.controllers;

import com.boot.demo.dto.ReviewText;
import com.boot.demo.model.Animal;
import com.boot.demo.model.Review;
import com.boot.demo.repos.AnimalReviewRepository;
import com.boot.demo.service.AnimalService;
import com.boot.demo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    @GetMapping("/review/{id}")
    public String review(Model model, @PathVariable("id") Long id) {
        model.addAttribute("animal", animalService.findById(id));
        model.addAttribute("reviews", animalService.getAllReviewById(id));
        if (animalService.getAllReviewById(id).isEmpty()) {
            return "emptyreview";
        }
        return "review";
    }

    @GetMapping("/review/create/{id}")
    public String createReview(Model model, @PathVariable("id") Long id) {
        model.addAttribute("animal", animalService.findById(id));
        if (animalService.findAllByOwner(userService.current).contains(animalService.getAnimalById(id))) {
            return "selfreview";
        }
        model.addAttribute("review", new ReviewText());
        model.addAttribute("animal", animalService.findById(id));
        return "createreview";
    }

    @PostMapping("/review/{id}")
    public String addReview(@PathVariable("id") Long id, @Valid @ModelAttribute("review") ReviewText text, BindingResult result) {
        if (result.hasErrors()) {
            return "createreview";
        }
        Review review = new Review(text.getText());
        animalService.addReview(review);
        animalService.addAnimalReview(review, animalService.getAnimalById(id));
        return "redirect:/animal/review/" + id;
    }

    @PatchMapping("/{id}")
    public String changeAnimal(@PathVariable("id") Long id, @Valid @ModelAttribute("animal") Animal animal, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "changeAnimal";
        }
        Animal old = animalService.findById(id);
        old.setName(animal.getName());
        old.setBirth(animal.getBirth());
        old.setType(animal.getType());
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

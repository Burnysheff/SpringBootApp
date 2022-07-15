package com.boot.demo.controllers;

import com.boot.demo.dto.RegistrationData;
import com.boot.demo.model.Animal;
import com.boot.demo.model.User;
import com.boot.demo.repos.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class EntryController {
    UserRepository userRepository;

    public EntryController (UserRepository repository) {
        this.userRepository = repository;
    }

    @GetMapping()
    public String entry() {
        return "entrance";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("registration", new RegistrationData());
        return "logPerson";
    }

    @GetMapping("/registration")
    public String auth(Model model) {
        model.addAttribute("registration", new RegistrationData());
        return "newPerson";
    }

    @PostMapping("/auth")
    public String auth(@Valid @ModelAttribute("registration") RegistrationData registrationData, BindingResult result) {
        if (result.hasErrors()) {
            return "newPerson";
        }
        User user = new User(registrationData.name, registrationData.password);
        userRepository.save(user);
        return "redirect:/person/" + user.getId();
    }

    @PostMapping("/login")
    public String log(@Valid @ModelAttribute("registration") RegistrationData registrationData, BindingResult result) {
        if (result.hasErrors()) {
            return "logPerson";
        }
        User user = userRepository.findByName(registrationData.name);
        if (!user.getPassword().equals(registrationData.password)) {
            return "logPerson";
        }
        return "redirect:/person/" + user.getId();
    }
}

package com.boot.demo.controllers;

import com.boot.demo.dto.RegistrationData;
import com.boot.demo.model.Animal;
import com.boot.demo.model.User;
import com.boot.demo.repos.UserRepository;
import com.boot.demo.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class EntryController {
    UserService userService;

    public EntryController (UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String entry() {
        return "entrance";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("registration", new RegistrationData());
        return "login";
    }

    @GetMapping("/login-error")
    public String login(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        String errorMessage = null;
        if (session != null) {
            AuthenticationException ex = (AuthenticationException) session
                    .getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
            if (ex != null) {
                errorMessage = ex.getMessage();
            }
        }
        model.addAttribute("errorMessage", errorMessage);
        return "login";
    }

    @GetMapping("/registration")
    public String auth(Model model) {
        model.addAttribute("registration", new RegistrationData());
        return "registration";
    }

    @PostMapping("/registration")
    public String auth(@Valid @ModelAttribute("registration") RegistrationData registrationData, BindingResult result) {
        if (result.hasErrors()) {
            return "registration";
        }
        if (userService.wasCreated(registrationData.name)) {
            ObjectError error = new ObjectError("globalError", "There is a user with such a name!");
            result.addError(error);
            return "registration";
        }
        userService.addUser(registrationData.name, registrationData.password);
        return "redirect:/";
    }
}

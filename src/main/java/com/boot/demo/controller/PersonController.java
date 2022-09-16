package com.boot.demo.controller;

import com.boot.demo.dto.CourseCode;
import com.boot.demo.dto.CourseName;
import com.boot.demo.dto.CourseType;
import com.boot.demo.model.Course;
import com.boot.demo.model.User;
import com.boot.demo.service.CourseService;
import com.boot.demo.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/person")
public class PersonController {
    UserService userService;

    CourseService courseService;

    public PersonController(UserService userService, CourseService courseService) {
        this.userService = userService;
        this.courseService = courseService;
    }

    @GetMapping()
    public String main(Model model) {
        userService.current = userService.findUserById(((User)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId());
        model.addAttribute("person", userService.current);
        model.addAttribute("courseCode", new CourseCode());
        model.addAttribute("courseName", new CourseName());
        return "person";
    }

    @GetMapping("/newCourse")
    public String newCourse(Model model) {
        model.addAttribute("person", userService.current);
        model.addAttribute("course", new Course());
        model.addAttribute("theme", new CourseType());
        return "newCourse";
    }

    @GetMapping("/listCourse")
    public String listCourse(Model model) {
        List<Course> courseList = courseService.findAllByOwner(userService.current);
        model.addAttribute("person", userService.current);
        model.addAttribute("courseList", courseList);
        if (courseList.isEmpty()) {
            return "nothing";
        }
        return "courseList";
    }

    @PostMapping("/find")
    public String find(@Valid @ModelAttribute("courseCode") CourseCode code, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("person", userService.current);
            return "person";
        }
        model.addAttribute("person", userService.current);
        if (courseService.checkPresentById(code.getValue())) {
            model.addAttribute("course", courseService.findById(code.getValue()));
        } else {
            return "nothing";
        }
        return "course";
    }

    @PostMapping("/findName")
    public String findName(@Valid @ModelAttribute("courseName") CourseName name, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("person", userService.current);
            return "person";
        }
        model.addAttribute("person", userService.current);
        if (courseService.checkPresentByName(name.getName()) || courseService.checkPresentByCompany(name.getName())) {
            List<Course> courses = new ArrayList<>(courseService.findByName(name.getName()));
            courses.addAll(courseService.findByCompany(name.getName()));
            model.addAttribute("courseList", courses);
        } else {
            return "nothing";
        }
        return "courseList";
    }

    @PostMapping("/course")
    public String showAnimal(@Valid @ModelAttribute("course") Course course, BindingResult result,
                             @Valid @ModelAttribute("theme") CourseType type, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("person", userService.current);
            return "newCourse";
        }
        courseService.saveCourse(course);
        userService.connectUserCourse(course);
        courseService.saveTheme(course, type);
        return "redirect:/person";
    }
}
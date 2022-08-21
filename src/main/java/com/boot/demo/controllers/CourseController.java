package com.boot.demo.controllers;

import com.boot.demo.dto.CourseType;
import com.boot.demo.dto.ReviewText;
import com.boot.demo.model.Course;
import com.boot.demo.model.Review;
import com.boot.demo.service.CourseService;
import com.boot.demo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/course")
public class CourseController {
    CourseService courseService;

    UserService userService;

    public CourseController(CourseService courseService, UserService userService) {
        this.courseService = courseService;
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public String showCourse(@PathVariable("id") Long id, Model model) {
        Course course = courseService.findById(id);
        model.addAttribute("course", course);
        model.addAttribute("person", userService.current);
        model.addAttribute("theme", courseService.getTheme(course));
        return "course";
    }

    @GetMapping("/patch/{id}")
    public String goToChange(Model model, @PathVariable("id") Long id) {
        model.addAttribute("course", courseService.findById(id));
        model.addAttribute("theme", new CourseType());
        if (!courseService.findAllByOwner(userService.current).contains(courseService.findById(id)) ||
        !courseService.getAllReviewById(id).isEmpty()) {
            return "changereject";
        }
        return "changecourse";
    }

    @GetMapping("/review/{id}")
    public String review(Model model, @PathVariable("id") Long id) {
        model.addAttribute("course", courseService.findById(id));
        model.addAttribute("reviews", courseService.getAllReviewById(id));
        if (courseService.getAllReviewById(id).isEmpty()) {
            return "emptyreview";
        }
        return "review";
    }

    @GetMapping("/review/create/{id}")
    public String createReview(Model model, @PathVariable("id") Long id) {
        model.addAttribute("review", new ReviewText());
        model.addAttribute("course", courseService.findById(id));
        return "createreview";
    }

    @PostMapping("/review/{id}")
    public String addReview(@PathVariable("id") Long id, @Valid @ModelAttribute("review") ReviewText text, BindingResult result) {
        if (result.hasErrors()) {
            return "createreview";
        }
        Review review = new Review(userService.current.getName() + ": " + text.getText());
        courseService.addReview(review);
        courseService.addCourseReview(review, courseService.findById(id));
        return "redirect:/course/review/" + id;
    }

    @PatchMapping("/{id}")
    public String changeAnimal(@PathVariable("id") Long id, @Valid @ModelAttribute("course") Course course, BindingResult result,
                               @Valid @ModelAttribute("theme") CourseType type, Model model) {
        if (result.hasErrors()) {
            return "changeCourse";
        }
        Course old = courseService.findById(id);
        old.setName(course.getName());
        old.setFinish(course.getFinish());
        old.setCompany(course.getCompany());
        courseService.saveCourse(old);
        courseService.changeTheme(old, type);
        model.addAttribute("course", course);
        model.addAttribute("person", userService.current);
        model.addAttribute("theme", courseService.getTheme(course));
        return "course";
    }

    @DeleteMapping("/{id}")
    public String deleteAnimal(@PathVariable("id") Long id) {
        courseService.deleteCourse(courseService.findById(id));
        return "redirect:/person";
    }
}

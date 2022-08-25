package com.boot.demo.service;

import com.boot.demo.dto.CourseType;
import com.boot.demo.model.*;
import com.boot.demo.repos.*;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseService {
    CourseRepository courseRepository;

    CourseUserRepository courseUserRepository;

    CourseReviewRepository courseReviewRepository;

    ReviewRepository reviewRepository;

    CourseThemeRepository courseThemeRepository;

    ThemeRepository themeRepository;

    public CourseService(CourseRepository courseRepository, CourseUserRepository courseUserRepository,
                         CourseReviewRepository courseReviewRepository, ReviewRepository reviewRepository,
                         CourseThemeRepository courseThemeRepository, ThemeRepository themeRepository) {
        this.courseRepository = courseRepository;
        this.courseUserRepository = courseUserRepository;
        this.courseReviewRepository = courseReviewRepository;
        this.reviewRepository = reviewRepository;
        this.courseThemeRepository = courseThemeRepository;
        this.themeRepository = themeRepository;
    }

    public List<Course> findAllByOwner(User user) {
        return courseUserRepository.findAllByUser(user).stream().map(CourseUser::getCourse).collect(Collectors.toList());
    }

    public boolean checkPresentById(Long id) {
        return courseRepository.findById(id).isPresent();
    }

    public boolean checkPresentByName(String name) {
        return !courseRepository.findAllCourseByName(name).isEmpty();
    }

    public boolean checkPresentByCompany(String company) {
        return !courseRepository.findAllCourseByCompany(company).isEmpty();
    }

    public Course findById(Long id) {
        if (courseRepository.findById(id).isPresent()) {
            return courseRepository.findById(id).get();
        } else {
            return new Course();
        }
    }

    public List<Course> findByName(String name) {
        return courseRepository.findAllCourseByName(name);
    }

    public List<Course> findByCompany(String company) {
        return courseRepository.findAllCourseByCompany(company);
    }

    public List<Review> getAllReviewById(Long id) {
        return courseReviewRepository.getAllByCourse(courseRepository.findById(id)).stream().map(CourseReview::getReview).collect(Collectors.toList());
    }

    public void addReview(Review review) {
        this.reviewRepository.save(review);
    }

    public void addCourseReview(Review review, Course course) {
        this.courseReviewRepository.save(new CourseReview(course, review));
    }

    public void saveCourse(Course course) {
        courseRepository.save(course);
    }

    public void deleteCourse(Course course) {
        courseRepository.delete(course);
    }

    public void saveTheme(Course course, CourseType type) {
        if (type.isIt()) {
            Long meatId = (themeRepository.findThemeByDirect("it")).getId();
            courseThemeRepository.save(new CourseTheme(course, meatId));
        }
        if (type.isFinance()) {
            Long fruitId = (themeRepository.findThemeByDirect("finance")).getId();
            courseThemeRepository.save(new CourseTheme(course, fruitId));
        }
        if (type.isQa()) {
            Long vegesId = (themeRepository.findThemeByDirect("qa")).getId();
            courseThemeRepository.save(new CourseTheme(course, vegesId));
        }
    }

    public List<String> getTheme(Course course) {
        List<CourseTheme> animalFeeds = courseThemeRepository.getAllCourseThemeByCourse(course);
        List<Long> keys = animalFeeds.stream().map(CourseTheme::getThemeId).toList();
        List<String> feeds = new ArrayList<>();
        for (Long id : keys) {
            feeds.add(themeRepository.findFeedById(id).getDirect());
        }
        return feeds;
    }

    @Transactional
    public void changeTheme(Course course, CourseType type) {
        courseThemeRepository.deleteCourseThemeByCourse(course);
        this.saveTheme(course, type);
    }
}

package com.boot.demo;

import com.boot.demo.model.Course;
import com.boot.demo.model.CourseUser;
import com.boot.demo.model.User;
import com.boot.demo.repo.*;
import com.boot.demo.service.CourseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CourseServiceTest {
    private CourseService courseService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    CourseUserRepository courseUserRepository;

    @Autowired
    CourseReviewRepository courseReviewRepository;

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    CourseThemeRepository courseThemeRepository;

    @Autowired
    ThemeRepository themeRepository;

    @BeforeEach
    public void set() {
        this.courseService = new CourseService(courseRepository, courseUserRepository, courseReviewRepository,
                reviewRepository, courseThemeRepository, themeRepository);
    }

    @Test
    public void checkFindByOwner() {
        User user = new User("name", "password");
        userRepository.save(user);

        Course course = new Course("name",
                new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime(), "name");
        courseService.saveCourse(course);

        courseUserRepository.save(new CourseUser(user, course));

        List<Course> courseList = courseService.findAllByOwner(user);
        assertEquals(courseList.get(0).getName(), "name");
    }

    @Test
    public void checkFindById() {
        Course course = new Course("name",
                new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime(), "name");
        courseService.saveCourse(course);

        assertEquals(courseService.findById(course.getId()).getName(), "name");
    }

    @Test
    public void checkPresentById() {
        Course course = new Course("name",
                new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime(), "name");
        courseService.saveCourse(course);

        assertTrue(courseService.checkPresentById(course.getId()));
    }

    @Test
    public void checkPresentByIdFalse() {
        Course course = new Course("name",
                new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime(), "name");
        courseService.saveCourse(course);

        assertFalse(courseService.checkPresentById(course.getId() + 1));
    }

}

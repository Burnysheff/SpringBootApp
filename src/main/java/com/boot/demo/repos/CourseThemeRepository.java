package com.boot.demo.repos;

import com.boot.demo.model.Course;
import com.boot.demo.model.CourseTheme;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseThemeRepository extends CrudRepository<CourseTheme, Long> {
    List<CourseTheme> getAllCourseThemeByCourse(Course course);

    void deleteCourseThemeByCourse(Course course);
}

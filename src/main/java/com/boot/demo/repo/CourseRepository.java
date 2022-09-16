package com.boot.demo.repo;

import com.boot.demo.model.Course;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends CrudRepository<Course, Long> {
    List<Course> findAllCourseByName(String name);

    List<Course> findAllCourseByCompany(String company);
}

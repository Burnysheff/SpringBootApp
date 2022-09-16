package com.boot.demo.repo;

import com.boot.demo.model.Course;
import com.boot.demo.model.CourseReview;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseReviewRepository extends CrudRepository<CourseReview, Long> {
    List<CourseReview> getAllByCourse(Optional<Course> course);
}

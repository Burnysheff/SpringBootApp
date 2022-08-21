package com.boot.demo.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Table(name = "course_review")
@Entity
public class CourseReview {
    @Id
    @GeneratedValue
    private Long Id;


    @ManyToOne
    @NonNull
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne
    @NonNull
    @JoinColumn(name = "review_id")
    private Review review;
}

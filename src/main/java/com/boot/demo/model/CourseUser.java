package com.boot.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Table(name = "courseUser")
@Entity
public class CourseUser {

    @Id
    @GeneratedValue
    @Column(name="courseUser_id")
    private Long courseUser;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;
}

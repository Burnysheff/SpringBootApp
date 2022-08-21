package com.boot.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Table(name = "courseTheme")
@Entity
public class CourseTheme {
    @Id
    @GeneratedValue
    private Long Id;

    @ManyToOne
    @NonNull
    @JoinColumn(name = "course_id")
    private Course course;

    @NonNull
    @JoinColumn(name = "theme_id")
    private Long themeId;
}

package com.boot.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "courses")
@Entity
public class Course {

    @Id
    @GeneratedValue
    @Column(name="course_id")
    Long Id;

    @Size(min = 2, message = "Name should be 2 or more symbols long!")
    @Column(name = "name", nullable = false)
    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Please, specify the date of birth!")
    @Column(name = "finish", nullable = false)
    private Date finish;

    @Size(min = 2, message = "Type should be at least something!")
    @Column(name = "company", nullable = false)
    private String company;

    public Course(String name, Date date, String company) {
        this.setName(name);
        this.setCompany(company);
        this.setFinish(date);
    }
}

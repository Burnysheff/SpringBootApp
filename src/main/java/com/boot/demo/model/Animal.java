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
@Table(name = "animals")
@Entity
public class Animal {

    @Id
    @GeneratedValue
    @Column(name="animal_id")
    Long Id;

    @Size(min = 2, message = "Name should be 2 or more symbols long!")
    @Column(name = "name", nullable = false)
    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Please, specify the date of birth!")
    @Column(name = "birth", nullable = false)
    private Date birth;

    @Size(min = 2, message = "Type should be at least something!")
    @Column(name = "type", nullable = false)
    private String type;
}

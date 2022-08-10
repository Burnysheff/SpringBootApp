package com.boot.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

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

    @Size(min = 2, message = "Sex should be at least something!")
    @Column(name = "sex", nullable = false)
    private String sex;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User owner;
}

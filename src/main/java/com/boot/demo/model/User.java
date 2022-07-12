package com.boot.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "people")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Size(min = 2, max = 15, message = "Name should be 2 or more symbols long!")
    @Column(unique = true)
    private String name;

    @Size(min = 2, max = 15, message = "Password should be 2 or more symbols long!")
    private String password;
}

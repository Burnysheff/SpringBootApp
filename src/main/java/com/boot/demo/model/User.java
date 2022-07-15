package com.boot.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "people")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id", nullable = false)
    Long id;

    @Size(min = 2, max = 15, message = "Name should be 2 or more symbols long!")
    @Column(name = "name", nullable = false)
    private String name;

    @Size(min = 2, max = 15, message = "Password should be 2 or more symbols long!")
    @Column(name = "password", nullable = false)
    private String password;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }
}

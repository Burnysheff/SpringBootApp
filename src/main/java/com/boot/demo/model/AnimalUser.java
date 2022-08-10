package com.boot.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Table(name = "animalUser")
@Entity
public class AnimalUser {

    @Id
    @GeneratedValue
    @Column(name="animalUser_id")
    private Long animalUser;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "animal_id", nullable = false)
    private Animal animal;
}

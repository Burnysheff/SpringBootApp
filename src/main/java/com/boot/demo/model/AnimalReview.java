package com.boot.demo.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Table(name = "animalReview")
@Entity
public class AnimalReview {
    @Id
    @GeneratedValue
    private Long Id;


    @ManyToOne
    @NonNull
    @JoinColumn(name = "animal_id")
    private Animal animal;

    @ManyToOne
    @NonNull
    @JoinColumn(name = "review_id")
    private Review review;
}

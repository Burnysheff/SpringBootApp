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
@Table(name = "animalFeed")
@Entity
public class AnimalFeed {
    @Id
    @GeneratedValue
    private Long Id;

    @ManyToOne
    @NonNull
    @JoinColumn(name = "animal_id")
    private Animal animal;

    @NonNull
    @JoinColumn(name = "feed_id")
    private Long feedId;
}

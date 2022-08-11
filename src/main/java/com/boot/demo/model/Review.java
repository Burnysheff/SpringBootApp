package com.boot.demo.model;

import lombok.*;

import javax.persistence.*;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Table(name = "reviews")
@Entity
public class Review {
    @Id
    @GeneratedValue
    @Column(name = "review_id")
    private Long Id;

    @Column(name = "text")
    @NonNull
    private String text;
}

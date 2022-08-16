package com.boot.demo.model;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "feed")
@Entity
public class Feed {
    @Id
    @Column(name = "feed_id")
    Long id;

    @Column(name = "food")
    private String food;
}

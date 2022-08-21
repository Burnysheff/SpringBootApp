package com.boot.demo.model;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "theme")
@Entity
public class Theme {
    @Id
    @Column(name = "theme_id")
    Long id;

    @Column(name = "direct")
    private String direct;
}

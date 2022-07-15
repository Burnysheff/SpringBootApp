package com.boot.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
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
    @Column
    Long id;

    @Size(min = 2, message = "Name should be 2 or more symbols long!")
    @Column(name = "name", nullable = false)
    private String name;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Temporal(TemporalType.DATE)
    @Column(name = "birth", nullable = false)
    private Date birth;

    @Size(min = 2, message = "Sex should be at least something!")
    @Column(name = "sex", nullable = false)
    private String sex;

    @Column(name = "owner", nullable = false)
    private Long ownerId;
}

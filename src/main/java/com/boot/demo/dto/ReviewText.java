package com.boot.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReviewText {
    @Size(min = 5, max = 100, message = "Write 5 to 100 symbols!")
    private String text;
}

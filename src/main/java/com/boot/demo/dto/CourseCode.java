package com.boot.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CourseCode {

    @Min(value = 1, message = "Enter a positive number!")
    @NotNull(message = "Enter a positive number!")
    private Long value;
}

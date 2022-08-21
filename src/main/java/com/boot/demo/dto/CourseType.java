package com.boot.demo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CourseType {
    private boolean it;

    private boolean finance;

    private boolean qa;
}

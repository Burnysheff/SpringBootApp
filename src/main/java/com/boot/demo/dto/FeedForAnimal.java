package com.boot.demo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FeedForAnimal {
    private boolean meat;

    private boolean fruit;

    private boolean veges;
}

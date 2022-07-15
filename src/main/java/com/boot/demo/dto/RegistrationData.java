package com.boot.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RegistrationData {
    @Size(min = 2, max = 15, message = "Name should be 2 or more symbols long!")
    public String name;

    @Size(min = 2, max = 15, message = "Password should be 2 or more symbols long!")
    public String password;
}

package com.example.magazine.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MagazineDTO {

    @NotBlank(message = "Lastname cannot be blank")
    private String name;

    @NotBlank(message = "Lastname cannot be blank")
    private String description;

    @NotBlank(message = "Lastname cannot be blank")
    @Pattern(regexp = "\\d+", message = "The string must consist of digits")
    private Double price;
}
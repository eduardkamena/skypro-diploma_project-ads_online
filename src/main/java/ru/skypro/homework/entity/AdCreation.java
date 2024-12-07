package ru.skypro.homework.entity;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AdCreation {
        @NotBlank
        private String title;

        @NotBlank
        private String description;

        @NotNull
        @Min(0)
        private Double price;
    }

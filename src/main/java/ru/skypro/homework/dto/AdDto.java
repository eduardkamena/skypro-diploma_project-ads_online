package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class AdDto {
    private Long id;
    private String title;
    private String description;
    private Double price;
    private String imageUrl;
}

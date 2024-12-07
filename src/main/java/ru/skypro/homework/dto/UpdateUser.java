package ru.skypro.homework.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import ru.skypro.homework.entity.User;

@Data
public class UpdateUser {

    @Size(min = 3, max = 10)
    private String firstName;
    @Size(min = 3, max = 10)
    private String lastName;
    @Pattern(regexp = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}")
    private String phone;

}


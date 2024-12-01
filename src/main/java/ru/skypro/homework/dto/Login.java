package ru.skypro.homework.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class Login {

    @NotNull
    @Length(min = 4, max = 32)
    private String username;

    @NotNull
    @Length(min = 8, max = 16)
    private String password;
}

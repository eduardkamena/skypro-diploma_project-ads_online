package ru.skypro.homework.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;
import lombok.Data;

@Data
public class Register {

    @NotBlank(message = "Имя пользователя не может быть пустым")
    @Length(min = 4, max = 32,
            message = "Имя пользователя должно содержать от 4 до 32 символов")
    @Email(message = "Email адрес должен быть в формате user@example.com")
    private String username;

    @NotBlank(message = "Пароль не может быть пустым")
    @Length(min = 8, max = 16,
            message = "Длина пароля должна быть от 8 до 16 символов")
    private String password;

    @NotBlank(message = "Имя должно быть указано")
    @Length(min = 2, max = 16,
            message = "Имя должно содержать от 2 до 16 символов")
    private String firstName;

    @NotBlank(message = "Должна быть указана фамилия")
    @Length(min = 2, max = 16,
            message = "Фамилия должна содержать от 2 до 16 символов")
    private String lastName;

    @NotBlank(message = "Должен быть указан номер телефона")
    @Pattern(regexp = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}"
            ,message = "Телефон должен начинаться с +7 и продолжаться 10 числами")
    private String phone;

    private Role role;
}

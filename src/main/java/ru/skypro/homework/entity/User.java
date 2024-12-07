package ru.skypro.homework.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import ru.skypro.homework.dto.Role;

import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Data
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @JsonProperty("id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq")
    @SequenceGenerator(name = "user_id_seq", sequenceName = "user_id_seq", allocationSize = 1)
    @Schema(description = "Уникальный идентификатор пользователя")
    private Long id;

    @Column(name = "email", unique = true, nullable = false)
    @Schema(description = "Почта пользователя")
    @NotBlank(message = "Почта пользователя не может быть пустым")
    private String email;

    @Column(name = "password", nullable = false)
    @Schema(description = "Пароль пользователя")
    @NotBlank(message = "Пароль не может быть пустым")
    private String password;

    @Column(name = "phone", unique = true, nullable = false)
    @Schema(description = "Телефон пользователя")
    @Pattern(regexp = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}",
            message = "Телефон должен соответствовать формату +7 (XXX) XXX-XX-XX")
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    @Schema(description = "Роль пользователя")
    private Role role;

    @Column(name = "image")
    @Schema(description = "Ссылка на аватар пользователя")
    private String image;

    @Column(name = "first_name", nullable = true)
    @Schema(description = "Имя пользователя")
    @Size(min = 3, max = 10, message = "Имя должно быть длиной от 3 до 10 символов")
    private String firstName;

    @Column(name = "last_name", nullable = true)
    @Schema(description = "Фамилия пользователя")
    @Size(min = 3, max = 10, message = "Фамилия должна быть длиной от 3 до 10 символов")
    private String lastName;

    public Long getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getLastName() {
        return lastName;
    }

    public Role getRole() {
        return role;
    }

    public String getImage() {
        return image;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}

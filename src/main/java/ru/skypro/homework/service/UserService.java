package ru.skypro.homework.service;

import ru.skypro.homework.entity.User;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.dto.UpdateUser;

public interface UserService {
    User registerUser(Register register);
    User getUserByUsername(String username);
    User updateUser(Long id, UpdateUser updateUser);
    void deleteUser(Long id);
}

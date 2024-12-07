package ru.skypro.homework.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    /**
     * Сохранение пользователя
     *
     * @return сохраненный пользователь
     */
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    /**
     * Создание пользователя
     *
     * @return созданный пользователь
     */
    public User createUser(User user) {
        if (userRepository.existsByUsername(user.getEmail())) {
            throw new RuntimeException("Пользователь с таким именем уже существует");
        }
        if (userRepository.existsByPhone(user.getPhone())) {
            throw new RuntimeException("Данный телефон уже зарегистрирован");
        }

        return userRepository.save(user);
    }

    /**
     * Получение пользователя по имени пользователя
     *
     * @return пользователь
     */
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
    }

    @Override
    public User registerUser(Register register) {
        if (userRepository.existsByUsername(register.getUsername())) {
            throw new RuntimeException("Пользователь с таким именем уже существует");
        }
        if (userRepository.existsByPhone(register.getPhone())) {
            throw new RuntimeException("Данный телефон уже зарегистрирован");
        }

        User user = new User();
        user.setEmail(register.getUsername());
        user.setPassword(register.getPassword());
        user.setPhone(register.getPhone());
        user.setFirstName(register.getFirstName());
        user.setLastName(register.getLastName());
        user.setRole(register.getRole());

        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long id, UpdateUser updateUser) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Пользователь не найден"));

        user.setFirstName(updateUser.getFirstName());
        user.setLastName(updateUser.getLastName());
        user.setPhone(updateUser.getPhone());
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException("Пользователь не найден");
        }
        userRepository.deleteById(id);
    }
}

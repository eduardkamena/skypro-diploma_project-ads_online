package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.skypro.homework.config.CustomUserDetails;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.exception.UserNotFoundException;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new CustomUserDetails(userRepository.findByUsername(username)
                .orElseThrow(UserNotFoundException::new));
    }

    public boolean userExists(String username) {
        User notExist = new User();
        User count = userRepository.findByUsername(username).orElse(notExist);
        return !notExist.equals(count);
    }

    public void createUser(Register register, String password) {
        User user = userMapper.fromRegisterToUser(register);
        user.setPassword(password);
        userRepository.save(user);
    }
}
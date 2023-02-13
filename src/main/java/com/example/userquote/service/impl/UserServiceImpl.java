package com.example.userquote.service.impl;

import com.example.userquote.dto.UserDto;
import com.example.userquote.entity.UserEntity;
import com.example.userquote.repository.UserRepository;
import com.example.userquote.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder bCryptPasswordEncoder;


    @Override
    public void createUser(UserDto userDto) {

        UserEntity user=new UserEntity();

        user.setFName(userDto.getFName());
        user.setLName(userDto.getLName());
        user.setEmail(userDto.getEmail());
        user.setDateOfCreation(LocalDateTime.now());
        user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        userRepository.save(user);

    }


}

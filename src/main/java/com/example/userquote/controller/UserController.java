package com.example.userquote.controller;

import com.example.userquote.dto.UserDto;
import com.example.userquote.entity.UserEntity;
import com.example.userquote.repository.UserRepository;
import com.example.userquote.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    @PostMapping
    public void createUser(@RequestBody UserDto userDto){
        userService.createUser(userDto);

    }

    @GetMapping
    public List<UserEntity> get(){
        return userRepository.findAll();
    }
}

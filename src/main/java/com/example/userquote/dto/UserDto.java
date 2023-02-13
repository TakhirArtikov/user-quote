package com.example.userquote.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class UserDto {

    private Long userId;
    private String fName;
    private String lName;
    private String email;
    private String password;
    private LocalDateTime dateOfCreation;
}

package com.example.userquote.dto;

import com.example.userquote.entity.UserEntity;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class QuoteDto {

    private Long quoteId;

    private String content;
    private LocalDateTime creationDate;
    private LocalDateTime updatedDate;
    private UserDto user;
    private Long userId;
    private Long vote;
}

package com.example.userquote.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class VoteDto {

    private Long voteId;
    private QuoteDto quote;
    private Long quoteId;
    private UserDto user;
    private Long userId;
    private LocalDateTime time;
    private boolean plus;
}

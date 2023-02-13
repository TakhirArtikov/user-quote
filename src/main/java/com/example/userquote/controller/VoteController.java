package com.example.userquote.controller;

import com.example.userquote.dto.VoteDto;
import com.example.userquote.service.QuoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/vote")
public class VoteController {

    private final QuoteService quoteService;

    @PostMapping
    public void vote(@RequestBody VoteDto voteDto){
        quoteService.vote(voteDto);
    }
}

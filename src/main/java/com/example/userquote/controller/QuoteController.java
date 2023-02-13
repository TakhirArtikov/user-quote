package com.example.userquote.controller;

import com.example.userquote.dto.QuoteDto;
import com.example.userquote.dto.VoteDto;
import com.example.userquote.service.QuoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/quote")
public class QuoteController {

    private final QuoteService quoteService;

    @PostMapping
    public void addQuote(@RequestBody QuoteDto quoteDto){
        quoteService.addQuote(quoteDto);
    }
    @GetMapping
    public List<QuoteDto> view(){
      return quoteService.view();
    }
    @PutMapping
    public void update(QuoteDto quoteDto){
        quoteService.update(quoteDto);
    }

    @DeleteMapping
    public void deleteQuote(Long id){
        quoteService.deleteQuote(id);
    }

    @GetMapping("/random")
    public QuoteDto randomQuote(){
        return quoteService.randomQuote();
    }

    @GetMapping("/top10")
    public List<QuoteDto> top10Quote(){
        return quoteService.top10Quote();
    }

    @GetMapping("/worst10")
    public List<QuoteDto> worst10Quote(){
        return quoteService.worst10Quote();
    }

}

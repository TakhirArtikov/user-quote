package com.example.userquote.service;

import com.example.userquote.dto.QuoteDto;
import com.example.userquote.dto.VoteDto;

import java.util.List;

public interface QuoteService {

    void addQuote(QuoteDto quoteDto);
    void deleteQuote(Long id);
    List<QuoteDto> view();
    void update(QuoteDto quoteDto);

    QuoteDto randomQuote();

    List<QuoteDto> top10Quote();

    List<QuoteDto> worst10Quote();
    void vote(VoteDto voteDto);
}

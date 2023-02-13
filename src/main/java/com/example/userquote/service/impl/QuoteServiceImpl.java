package com.example.userquote.service.impl;

import com.example.userquote.dto.QuoteDto;
import com.example.userquote.dto.UserDto;
import com.example.userquote.dto.VoteDto;
import com.example.userquote.entity.Quote;
import com.example.userquote.entity.UserEntity;
import com.example.userquote.entity.Vote;
import com.example.userquote.repository.QuoteRepository;
import com.example.userquote.repository.UserRepository;
import com.example.userquote.repository.VoteRepository;
import com.example.userquote.service.QuoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuoteServiceImpl implements QuoteService {

    private final QuoteRepository quoteRepository;
    private final UserRepository userRepository;
    private final VoteRepository voteRepository;
    private final Random random=new Random();

    @Override
    public void addQuote(QuoteDto quoteDto) {
        Quote quote=new Quote();

        BeanUtils.copyProperties(quoteDto,quote);
        Optional<UserEntity> optionalUserEntity = userRepository.findById(quoteDto.getUserId());
        if(optionalUserEntity.isEmpty()) throw new NoSuchElementException("User not found");
        quote.setCreationDate(LocalDateTime.now());
        quote.setUser(optionalUserEntity.get());
        quoteRepository.save(quote);
    }

    @Override
    public void deleteQuote(Long id) {
        Optional<Quote> quote=quoteRepository.findById(id);
        if (quote.isEmpty()){
            throw new NoSuchElementException("Quote does not exist!");
        }
        quoteRepository.delete(quote.get());
    }

    @Override
    public List<QuoteDto> view() {
        return quoteRepository.findAll().stream().map(this::getQuoteDto).toList();
    }

    @Override
    public void update(QuoteDto quoteDto) {
        Optional<Quote> quote=quoteRepository.findById(quoteDto.getQuoteId());
        if (quote.isEmpty()){
            throw new NoSuchElementException("Quote does not exist!");
        }
        quoteDto.setUpdatedDate(LocalDateTime.now());
        quoteRepository.save(getQuoteEntity(quoteDto));
    }

    @Override
    public QuoteDto randomQuote() {
        List<Quote> quotes=quoteRepository.findAll();
        int randomIndex=random.nextInt(quotes.size());
        Quote quote=quotes.get(randomIndex);
        return getQuoteDto(quote);
    }

    @Override
    public List<QuoteDto> top10Quote() {
        List<Quote> quotes = quoteRepository.findAll();
        List<QuoteDto> quoteDtoList = quotes.stream().map(quote -> {
            int plus = voteRepository.findAllByPlusAndQuoteId(Boolean.TRUE, quote.getQuoteId()).size();
            int minus = voteRepository.findAllByPlusAndQuoteId(Boolean.FALSE, quote.getQuoteId()).size();
            QuoteDto quoteDto = getQuoteDto(quote);
            quoteDto.setVote((long) (plus-minus));
            return quoteDto;
        }).sorted(Comparator.comparingLong(QuoteDto::getVote)).toList();
        return quoteDtoList.subList(Math.max(quoteDtoList.size()-10, 0), quoteDtoList.size());
    }
    @Override
    public List<QuoteDto> worst10Quote() {
        List<Quote> quotes = quoteRepository.findAll();
        List<QuoteDto> quoteDtoList = quotes.stream().map(quote -> {
            int plus = voteRepository.findAllByPlusAndQuoteId(true, quote.getQuoteId()).size();
            int minus = voteRepository.findAllByPlusAndQuoteId(false, quote.getQuoteId()).size();
            QuoteDto quoteDto = getQuoteDto(quote);
            quoteDto.setVote((long) (plus-minus));
            return quoteDto;
        }).sorted(Comparator.comparingLong(QuoteDto::getVote)).toList();
        return quoteDtoList.subList(0, Math.min(quoteDtoList.size(), 10));
    }

    @Override
    public void vote(VoteDto voteDto) {
        Vote voteEntity=new Vote();
        BeanUtils.copyProperties(voteDto, voteEntity);
        voteEntity.setTime(LocalDateTime.now());
        voteRepository.save(voteEntity);
    }

    private QuoteDto getQuoteDto(Quote quote){
        QuoteDto dto=new QuoteDto();
        BeanUtils.copyProperties(quote,dto);
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(quote.getUser(), userDto);
        dto.setUser(userDto);
        int plus = voteRepository.findAllByPlusAndQuoteId(Boolean.TRUE, quote.getQuoteId()).size();
        int minus = voteRepository.findAllByPlusAndQuoteId(Boolean.FALSE, quote.getQuoteId()).size();
        dto.setVote((long) (plus-minus));
      return dto;
    }

    private Quote getQuoteEntity(QuoteDto dto){
        Quote quoteEntity=new Quote();
        BeanUtils.copyProperties(dto,quoteEntity);
        return quoteEntity;
    }
}

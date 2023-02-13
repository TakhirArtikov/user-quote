package com.example.userquote.repository;

import com.example.userquote.entity.Quote;
import com.example.userquote.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VoteRepository extends JpaRepository<Vote, Long> {
    List<Vote> findAllByPlusAndQuoteId(Boolean plus, Long quoteId);
}

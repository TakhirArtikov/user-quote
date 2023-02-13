package com.example.userquote.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long voteId;

    @ManyToOne
    @MapsId("quoteId")
    @JoinColumn(name = "quote_id")
    private Quote quote;

    @Column(name = "quote_id")
    private Long quoteId;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column(name = "user_id")
    private Long userId;

    private LocalDateTime time;

    private Boolean plus;
}

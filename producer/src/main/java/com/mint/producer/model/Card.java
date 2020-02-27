package com.mint.producer.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * Created by Toyin on 2/26/20.
 */
@Getter
@Setter
@Entity
@Table(name = "cards")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true)
    private String cardDigit;

    @NotNull
    @Column(name = "card_scheme")
    private String scheme;

    @Column(name = "card_type")
    private String type;

    private String bank;

    private LocalDateTime createdAt = LocalDateTime.now();
}

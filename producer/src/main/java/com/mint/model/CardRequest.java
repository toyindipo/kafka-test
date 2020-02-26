package com.mint.model;

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
@Table(name = "card_requests")
public class CardRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    private Card card;

    private LocalDateTime createdAt = LocalDateTime.now();
}

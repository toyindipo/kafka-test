package com.mint.producer.controller;

import com.mint.producer.dto.CardResponse;
import com.mint.producer.dto.CardStat;
import com.mint.producer.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Toyin on 2/26/20.
 */
@RestController
@RequestMapping("/card-scheme")
public class CardController {
    private CardService cardService;

    @Autowired
    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping("/verify/{digit}")
    public ResponseEntity<CardResponse> validateCard(@PathVariable("digit") String digit) {
        return ResponseEntity.ok(cardService.validateCard(digit));
    }

    @GetMapping("/stats")
    public ResponseEntity<CardStat> getCardStats(
            @RequestParam(value = "start", defaultValue = "1", required = false) int start,
                         @RequestParam(value = "limit", defaultValue = "3", required = false) int limit) {
        return ResponseEntity.ok(cardService.getStats(start, limit));
    }
}

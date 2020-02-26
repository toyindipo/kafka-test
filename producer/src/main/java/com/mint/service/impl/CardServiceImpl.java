package com.mint.service.impl;

import com.mint.dto.*;
import com.mint.model.Card;
import com.mint.model.CardRequest;
import com.mint.repository.CardRepository;
import com.mint.repository.CardRequestRepository;
import com.mint.service.BinService;
import com.mint.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Toyin on 2/26/20.
 */
@Service
public class CardServiceImpl implements CardService {
    private BinService binService;
    private CardRepository cardRepository;
    private CardRequestRepository cardRequestRepository;

    @Autowired
    public CardServiceImpl(BinService binService, CardRepository cardRepository,
                           CardRequestRepository cardRequestRepository) {
        this.binService = binService;
        this.cardRepository = cardRepository;
        this.cardRequestRepository = cardRequestRepository;
    }

    @Override
    public CardResponse validateCard(String digit) {
        Card card = findByDigit(digit);
        if (card == null) {
            ResponseEntity<ServiceResponse> responseEntity = binService.validateCard(digit);
            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                card = createCard(digit, responseEntity.getBody());
            }
        }
        CardResponse cardResponse = new CardResponse();
        if (card != null) {
            logCardRequest(card);
            cardResponse.setSuccess(true);
            cardResponse.setPayload(CardData.fromCard(card));
        }
        return cardResponse;
    }

    @Override
    public Card findByDigit(String digit) {
        return cardRepository.findByCardDigit(digit);
    }

    @Override
    public Card createCard(String digit, ServiceResponse serviceResponse) {
        Card card = new Card();
        card.setCardDigit(digit);
        card.setScheme(serviceResponse.getScheme());
        card.setType(serviceResponse.getType());
        if (serviceResponse.getBank() != null) {
            card.setBank(serviceResponse.getBank().getName());
        }
        return cardRepository.saveAndFlush(card);
    }

    @Override
    public CardRequest logCardRequest(Card card) {
        CardRequest cardRequest = new CardRequest();
        cardRequest.setCard(card);
        return cardRequestRepository.saveAndFlush(cardRequest);
    }

    @Override
    public CardStat getStats(int start, int limit) {
        CardStat cardStat = new CardStat();
        List<CardLog> cardLogs = cardRequestRepository.getCardLogs();
        if (!cardLogs.isEmpty()) {
            long sum = cardLogs.stream().map(cardLog -> cardLog.getCount()).reduce(0l, Long::sum);
            cardStat.setSize(sum);
            Map<String, Long> payload = new HashMap<>();
            if (start <= cardLogs.size()) {
                int current = start;
                while (current <= cardLogs.size() && current < (start + limit)) {
                    CardLog cardLog = cardLogs.get(current - 1);
                    payload.put(cardLog.getCardDigit(), cardLog.getCount());
                }
            }
            cardStat.setPayload(payload);
            cardStat.setStart(start);
            cardStat.setLimit(limit);
        }
        return cardStat;
    }
}

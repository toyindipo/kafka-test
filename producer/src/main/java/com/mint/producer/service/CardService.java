package com.mint.producer.service;

import com.mint.producer.dto.CardData;
import com.mint.producer.dto.CardResponse;
import com.mint.producer.dto.CardStat;
import com.mint.producer.dto.ServiceResponse;
import com.mint.producer.model.Card;
import com.mint.producer.model.CardRequest;

/**
 * Created by Toyin on 2/26/20.
 */
public interface CardService {
    CardResponse validateCard(String digit);
    Card findByDigit(String digit);
    Card createCard(String digit, ServiceResponse serviceResponse);
    CardRequest logCardRequest(Card card);

    void publishCardData(CardData cardData);

    CardStat getStats(int start, int limit);
}

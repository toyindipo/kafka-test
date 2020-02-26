package com.mint.service;

import com.mint.dto.CardResponse;
import com.mint.dto.CardStat;
import com.mint.dto.ServiceResponse;
import com.mint.model.Card;
import com.mint.model.CardRequest;

/**
 * Created by Toyin on 2/26/20.
 */
public interface CardService {
    CardResponse validateCard(String digit);
    Card findByDigit(String digit);
    Card createCard(String digit, ServiceResponse serviceResponse);
    CardRequest logCardRequest(Card card);
    CardStat getStats(int start, int limit);
}

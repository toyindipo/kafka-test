package com.mint.mock;

import com.mint.producer.dto.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Toyin on 2/27/20.
 */
public class MockData {
    public static ServiceResponse mockServiceResponse() {
        ServiceResponse serviceResponse = new ServiceResponse();
        serviceResponse.setScheme("visa");
        serviceResponse.setType("debit");
        Bank bank = new Bank();
        bank.setName("UBS");
        serviceResponse.setBank(bank);
        return serviceResponse;
    }

    public static CardResponse mockValidCardResponse() {
        CardResponse cardResponse = new CardResponse();
        cardResponse.setSuccess(true);
        CardData cardData = new CardData();
        cardData.setBank("UBS");
        cardData.setScheme("visa");
        cardData.setType("debit");
        cardResponse.setPayload(cardData);
        return cardResponse;
    }

    public static CardResponse mockInvalidCardResponse() {
        CardResponse cardResponse = new CardResponse();
        cardResponse.setSuccess(false);
        return cardResponse;
    }

    public static CardStat mockCardStat() {
        CardStat cardStat = new CardStat();
        cardStat.setSize(2);
        cardStat.setStart(1);
        Map<String, Long> payload = new HashMap<>();
        payload.put("11111111", 2l);
        cardStat.setPayload(payload);
        return cardStat;
    }
}

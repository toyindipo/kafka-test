package com.mint.dto;

import com.mint.model.Card;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Toyin on 2/26/20.
 */
@Getter
@Setter
public class CardData {
    private String scheme;
    private String type;
    private String bank;

    public static CardData fromCard(Card card) {
        CardData cardData = new CardData();
        cardData.setBank(card.getBank());
        cardData.setScheme(card.getScheme());
        cardData.setType(card.getType());
        return cardData;
    }
}

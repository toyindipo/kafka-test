package com.mint.producer.service;

import com.mint.producer.dto.CardData;

/**
 * Created by Toyin on 2/26/20.
 */
public interface MessageSource {
    void publishCardData(CardData cardData);
}

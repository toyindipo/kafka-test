package com.mint.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Toyin on 2/26/20.
 */
@Getter
@Setter
public class CardLog {
    private String cardDigit;
    private Long count;

    public CardLog(String cardDigit, Long count) {
        this.cardDigit = cardDigit;
        this.count = count;
    }
}

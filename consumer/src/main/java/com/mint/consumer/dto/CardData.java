package com.mint.consumer.dto;

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

    @Override
    public String toString() {
        return "CardData{" +
                "scheme='" + scheme + '\'' +
                ", type='" + type + '\'' +
                ", bank='" + bank + '\'' +
                '}';
    }
}

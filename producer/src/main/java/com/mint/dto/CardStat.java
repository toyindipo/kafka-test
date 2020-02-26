package com.mint.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Toyin on 2/26/20.
 */
@Getter
@Setter
public class CardStat {
    private boolean success;
    private int start;
    private int limit;
    private long size;
    private Map<String, Long> payload = new HashMap<>();
}

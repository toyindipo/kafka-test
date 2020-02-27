package com.mint.producer.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Toyin on 2/26/20.
 */
@Getter
@Setter
public class ServiceResponse {
    private String scheme;
    private String type;
    private Bank bank;
}

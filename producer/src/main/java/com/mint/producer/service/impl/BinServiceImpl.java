package com.mint.producer.service.impl;

import com.mint.producer.dto.ServiceResponse;
import com.mint.producer.service.BinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Toyin on 2/26/20.
 */
@Service
public class BinServiceImpl implements BinService {
    private RestTemplate restTemplate;
    private static final String BIN_CARD_CHECK_URL = "https://lookup.binlist.net/";

    @Autowired
    public BinServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public ResponseEntity<ServiceResponse> validateCard(String digit) {
        return restTemplate.getForEntity(BIN_CARD_CHECK_URL + digit, ServiceResponse.class);
    }
}

package com.mint.producer.service;

import com.mint.producer.dto.ServiceResponse;
import org.springframework.http.ResponseEntity;

/**
 * Created by Toyin on 2/26/20.
 */
public interface BinService {
    ResponseEntity<ServiceResponse> validateCard(String digit);
}

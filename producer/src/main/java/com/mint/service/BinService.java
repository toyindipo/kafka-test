package com.mint.service;

import com.mint.dto.ServiceResponse;
import org.springframework.http.ResponseEntity;

/**
 * Created by Toyin on 2/26/20.
 */
public interface BinService {
    ResponseEntity<ServiceResponse> validateCard(String digit);
}

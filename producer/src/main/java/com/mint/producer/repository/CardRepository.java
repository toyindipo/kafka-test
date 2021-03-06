package com.mint.producer.repository;

import com.mint.producer.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Toyin on 2/26/20.
 */
public interface CardRepository extends JpaRepository<Card, Long> {
    Card findByCardDigit(String digit);
}

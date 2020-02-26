package com.mint.repository;

import com.mint.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Toyin on 2/26/20.
 */
public interface CardRepository extends JpaRepository<Card, Long> {
    Card findByCardDigit(String digit);
}

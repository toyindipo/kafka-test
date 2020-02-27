package com.mint.producer.repository;

import com.mint.producer.dto.CardLog;
import com.mint.producer.model.CardRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Toyin on 2/26/20.
 */
public interface CardRequestRepository extends JpaRepository<CardRequest, Long> {
    @Query("select new com.mint.producer.dto.CardLog(c.card.cardDigit, count(c.id)) from CardRequest c group by c.card.cardDigit" +
            " order by count(c.id) desc")
    List<CardLog> getCardLogs();
}

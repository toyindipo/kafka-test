package com.mint.repository;

import com.mint.dto.CardLog;
import com.mint.model.CardRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Toyin on 2/26/20.
 */
public interface CardRequestRepository extends JpaRepository<CardRequest, Long> {
    @Query("select new com.mint.dto.CardLog(c.card.cardDigit, count(c)) from CardRequest c group by c.card.cardDigit" +
            " order by count(c) desc")
    List<CardLog> getCardLogs();
}

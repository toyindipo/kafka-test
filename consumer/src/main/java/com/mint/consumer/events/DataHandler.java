package com.mint.consumer.events;

import com.mint.consumer.dto.CardData;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * Created by Toyin on 2/26/20.
 */
@Component
public class DataHandler {
    private static final Logger logger =
            LoggerFactory.getLogger(DataHandler.class);

    @KafkaListener(topics = "com.ng.vela.even.card_verified")
    public void listen(CardData cardData) {
        logger.debug("Received a message " + cardData);
    }
}

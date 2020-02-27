package com.mint.consumer.events;

import com.mint.consumer.dto.CardData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;

/**
 * Created by Toyin on 2/26/20.
 */
@EnableBinding(CustomChannel.class)
public class DataHandler {
    private static final Logger logger =
            LoggerFactory.getLogger(DataHandler.class);

    @StreamListener("inboundCardData")
    public void loggerSink(@Payload CardData cardData) {
        logger.debug("Received a message" + cardData);
    }
}

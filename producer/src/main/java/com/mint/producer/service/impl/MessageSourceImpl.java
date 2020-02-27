package com.mint.producer.service.impl;

import com.mint.producer.dto.CardData;
import com.mint.producer.service.MessageSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.stereotype.Service;
import org.springframework.messaging.support.MessageBuilder;


/**
 * Created by Toyin on 2/26/20.
 */
@Service
public class MessageSourceImpl implements MessageSource {
    private Source source;
    private static final Logger logger =
            LoggerFactory.getLogger(MessageSourceImpl.class);

    @Autowired
    public MessageSourceImpl(Source source) {
        this.source = source;
    }

    @Override
    public void publishCardData(CardData cardData) {
        logger.debug("Publishing " + cardData);
        source.output().send(MessageBuilder.withPayload(cardData).build());
    }
}

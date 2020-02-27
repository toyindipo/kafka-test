package com.mint.producer.service.impl;

import com.mint.producer.dto.CardData;
import com.mint.producer.service.MessageSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


/**
 * Created by Toyin on 2/26/20.
 */
@Service
public class MessageSourceImpl implements MessageSource {
    private static final Logger logger =
            LoggerFactory.getLogger(MessageSourceImpl.class);
    private final KafkaTemplate<String, Object> template;
    private final String topicName;

    @Autowired
    public MessageSourceImpl(
            final KafkaTemplate<String, Object> template,
            @Value("${tpd.topic-name}") final String topicName) {
        this.template = template;
        this.topicName = topicName;
    }

    @Override
    public void publishCardData(CardData cardData) {
        logger.debug("Publishing " + cardData);
        this.template.send(topicName,
                cardData);
    }
}

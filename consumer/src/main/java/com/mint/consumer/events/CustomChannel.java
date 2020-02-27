package com.mint.consumer.events;

/**
 * Created by Toyin on 2/26/20.
 */
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface CustomChannel {
    @Input("inboundCardData")
    SubscribableChannel orgs();
}

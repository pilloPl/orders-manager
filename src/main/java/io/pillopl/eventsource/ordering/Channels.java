package io.pillopl.eventsource.ordering;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface Channels {

    @Input
    SubscribableChannel items();

    @Input
    SubscribableChannel shipments();

    @Output
    MessageChannel commands();
}

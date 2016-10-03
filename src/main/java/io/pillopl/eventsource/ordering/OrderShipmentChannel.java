package io.pillopl.eventsource.ordering;

import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.annotation.Publisher;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OrderShipmentChannel {

    @Publisher(channel = "commands")
    public void ship() {
    }
}

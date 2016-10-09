package io.pillopl.eventsource.ordering;

import io.pillopl.eventsource.ordering.shipment.CreateShipment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.annotation.Publisher;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OrderShipmentChannel {


    @Publisher(channel = "commands")
    public CreateShipment ship(CreateShipment create) {
        log.info("sending:  {}", create);
        return create;
    }
}

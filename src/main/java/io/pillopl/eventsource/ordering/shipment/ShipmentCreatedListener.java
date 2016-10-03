package io.pillopl.eventsource.ordering.shipment;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ShipmentCreatedListener {

    @StreamListener("shipments")
    public void shipmentEvents(ShipmentCreated shipmentCreated) {

    }

}



package io.pillopl.eventsource.ordering.shipment;

import io.pillopl.eventsource.ordering.Ordering;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ShipmentCreatedListener {

    private final Ordering ordering;

    @Autowired
    public ShipmentCreatedListener(Ordering ordering) {
        this.ordering = ordering;
    }

    @StreamListener("shipments")
    public void shipmentEvents(ShipmentCreated shipmentCreated) {
        log.info("got:  {}", shipmentCreated);
    }

}



package io.pillopl.eventsource.ordering.shop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ItemPaidListener {

    @StreamListener("items")
    public void shipmentEvents(ItemPaid itemPaid) {
        log.info("Received item paid event {}", itemPaid);
    }

}



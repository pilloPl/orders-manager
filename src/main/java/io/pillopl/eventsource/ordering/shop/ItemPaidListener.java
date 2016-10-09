package io.pillopl.eventsource.ordering.shop;

import io.pillopl.eventsource.ordering.Ordering;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ItemPaidListener {

    private final Ordering ordering;

    @Autowired
    public ItemPaidListener(Ordering ordering) {
        this.ordering = ordering;
    }


    @StreamListener("items")
    public void shipmentEvents(ItemPaid itemPaid) {
        log.info("Received item paid event {}", itemPaid);
    }

}



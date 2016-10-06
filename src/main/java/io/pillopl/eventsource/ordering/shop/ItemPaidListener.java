package io.pillopl.eventsource.ordering.shop;

import io.pillopl.eventsource.ordering.Ordering;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ItemPaidListener {

    private final Ordering orders;

    @Autowired
    public ItemPaidListener(Ordering orders) {
        this.orders = orders;
    }

    @StreamListener("items")
    public void shipmentEvents(ItemPaid itemPaid) {
        log.info("Received item paid event {}", itemPaid);
        orders.addPendingShipment(itemPaid.getUuid(), itemPaid.getWhen());
    }
}



package io.pillopl.eventsource.ordering;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@Slf4j
@EnableScheduling
public class Ordering {

    private final OrderShipmentChannel orderShipmentChannel;

    public Ordering(OrderShipmentChannel orderShipmentChannel) {
        this.orderShipmentChannel = orderShipmentChannel;
    }

    public void createPendingShipment(UUID itemUUID, Instant when) {
    }

    @Scheduled(fixedRate = 1000 * 60 * 60 * 24)
    public void createShipments() {
    }

    private void createShipment(UUID uuid) {
    }

    public void shipmentCreated(UUID orderedItemUuid) {
    }
}

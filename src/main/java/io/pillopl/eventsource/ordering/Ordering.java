package io.pillopl.eventsource.ordering;

import io.pillopl.eventsource.ordering.shipment.CreateShipment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Service
@Slf4j
@EnableScheduling
public class Ordering {

    private final ConcurrentMap<UUID, Instant> pendingOrders = new ConcurrentHashMap<>();
    private final OrderShipmentChannel orderShipmentChannel;

    @Autowired
    public Ordering(OrderShipmentChannel orderShipmentChannel) {
        this.orderShipmentChannel = orderShipmentChannel;
    }

    public void addPendingShipment(UUID uuid, Instant when) {
        pendingOrders.put(uuid, when);
        log.info("Added pending shipment {}", uuid);
    }

    public Map<UUID, Instant> getState() {
        return pendingOrders;
    }

    @Scheduled(fixedRate = 10000)
    public void orderPending() {
        pendingOrders.keySet().forEach(uuid -> orderShipmentChannel.ship(new CreateShipment(UUID.randomUUID(), Instant.now(), "DHL", uuid)));
    }

    public void shipmentOrdered(UUID orderedItemUUID) {
        pendingOrders.remove(orderedItemUUID);
        log.info("Shipment marked as ordered {}", orderedItemUUID);

    }
}
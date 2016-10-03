package io.pillopl.eventsource.ordering;


import io.pillopl.eventsource.ordering.shipment.CreateShipment;

import java.time.Instant;
import java.util.UUID;

import static java.time.Instant.now;

public class CommandFixture {

    public static CreateShipment orderShipmentCommand(UUID deliverable) {
        return new CreateShipment(deliverable, now(), "DHL", UUID.randomUUID());
    }

    public static CreateShipment orderShipmentCommand(UUID shipmentUUID, UUID deliverable) {
        return new CreateShipment(deliverable, now(), "DHL", shipmentUUID);
    }

    public static CreateShipment orderShipmentCommand(UUID shipmentUUID, UUID deliverable, Instant when) {
        return new CreateShipment(deliverable, when, "DHL", shipmentUUID);
    }

    public static CreateShipment orderShipmentCommand(UUID deliverable, Instant when) {
        return new CreateShipment(deliverable, when, "DHL", UUID.randomUUID());
    }



}

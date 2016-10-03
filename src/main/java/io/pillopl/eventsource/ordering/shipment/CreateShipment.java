package io.pillopl.eventsource.ordering.shipment;

import lombok.Value;

import java.time.Instant;
import java.util.UUID;

@Value
public class CreateShipment {

    private final String type = "shipment.order";

    private UUID shipmentUUID;
    private Instant when;
    private String supplier;
    private UUID orderedItemUuid;

}

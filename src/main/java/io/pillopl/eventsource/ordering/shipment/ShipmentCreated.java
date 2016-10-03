package io.pillopl.eventsource.ordering.shipment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShipmentCreated {

    public static final String TYPE = "shipment.ordered";

    private UUID shipmentUUID;
    private UUID orderedItemUuid;
    private Instant when;

}
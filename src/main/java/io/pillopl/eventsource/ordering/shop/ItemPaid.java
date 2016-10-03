package io.pillopl.eventsource.ordering.shop;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemPaid {

    public static final String TYPE = "item.paid";

    private UUID uuid;
    private Instant when;

}

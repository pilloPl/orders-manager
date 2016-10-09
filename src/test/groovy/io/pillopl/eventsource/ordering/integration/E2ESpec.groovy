package io.pillopl.eventsource.ordering.integration

import io.pillopl.eventsource.ordering.Channels
import io.pillopl.eventsource.ordering.Ordering
import org.springframework.beans.factory.annotation.Autowired

class E2ESpec extends IntegrationSpec {

    private static final UUID ANY_ITEM_UUID = UUID.randomUUID()

    @Autowired Channels channels
    @Autowired Ordering ordering

    def setup() {

    }

    private static String samplePayInJson(UUID uuid) {
        return "{\"type\":\"item.pay\",\"uuid\":\"$uuid\",\"when\":\"2016-10-06T10:29:23.956Z\"}"
    }

    private static String sampleShipmentOrdered(UUID itemUUID) {
        return "{\"type\":\"shipment.ordered\",\"orderedItemUuid\":\"$itemUUID\",\"when\":\"2016-10-06T10:29:23.956Z\"}"
    }


}
package io.pillopl.eventsource.ordering.integration

import io.pillopl.eventsource.ordering.Ordering
import io.pillopl.eventsource.ordering.OrderingBindings
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cloud.stream.test.binder.MessageCollector
import org.springframework.messaging.Message
import org.springframework.messaging.support.GenericMessage
import spock.util.concurrent.PollingConditions

import java.time.Instant
import java.util.concurrent.BlockingQueue

class E2ESpec extends IntegrationSpec {

    private static final UUID ANY_ITEM_UUID = UUID.randomUUID()

    @Autowired OrderingBindings channels
    @Autowired MessageCollector messageCollector
    @Autowired Ordering ordering

    BlockingQueue<Message<?>> commands

    def setup() {
        this.commands = messageCollector.forChannel(channels.commands())
    }

    def 'when item is paid, pending delivery should be saved'() {
        when:
            channels.items().send(new GenericMessage<>(itemPaidInJson(ANY_ITEM_UUID, Instant.now())))
        then:
            ordering.getState().containsKey(ANY_ITEM_UUID)
    }

    def 'adding pending delivery should be idempotent'() {
        when:
            channels.items().send(new GenericMessage<>(itemPaidInJson(ANY_ITEM_UUID, Instant.now())))
        and:
            channels.items().send(new GenericMessage<>(itemPaidInJson(ANY_ITEM_UUID, Instant.now())))
        then:
            ordering.getState().containsKey(ANY_ITEM_UUID)
            ordering.getState().size() == 1
    }

    def 'should create shipment for pending delivery'() {
        given:
            PollingConditions conditions = new PollingConditions(delay: 0, factor: 1, timeout: 10)
        when:
            channels.items().send(new GenericMessage<>(itemPaidInJson(ANY_ITEM_UUID, Instant.now())))
        and:
            ordering.orderPending()
        then:
            conditions.eventually {
                ((Message<String>) commands.poll()).getPayload().contains(ANY_ITEM_UUID.toString())
            }
    }

    def 'should remove pending shipment when shipment is ordered'() {
        when:
            channels.items().send(new GenericMessage<>(itemPaidInJson(ANY_ITEM_UUID, Instant.now())))
        and:
            ordering.orderPending()
        and:
            channels.shipments().send(new GenericMessage<Object>(shipmentCreatedInJson(UUID.randomUUID(), ANY_ITEM_UUID, Instant.now())))
        then:
            !ordering.getState().containsKey(ANY_ITEM_UUID)
    }

    private static String itemPaidInJson(UUID uuid, Instant when) {
        return "{\"type\":\"item.paid\",\"uuid\":\"$uuid\",\"when\":\"$when\"}"
    }

    private static String shipmentCreatedInJson(UUID shipmentUUID, UUID itemUuid, Instant when) {
        return "{\"type\":\"shipment.ordered\",\"shipmentUUID\":\"$shipmentUUID\",\"orderedItemUuid\":\"$itemUuid\",\"when\":\"$when\"}"
    }

}
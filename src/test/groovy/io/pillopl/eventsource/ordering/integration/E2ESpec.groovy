package io.pillopl.eventsource.ordering.integration

import io.pillopl.eventsource.ordering.Ordering
import io.pillopl.eventsource.ordering.OrderingBindings
import org.springframework.beans.factory.annotation.Autowired

class E2ESpec extends IntegrationSpec {

    private static final UUID ANY_ITEM_UUID = UUID.randomUUID()

    @Autowired OrderingBindings channels
    @Autowired Ordering ordering


}

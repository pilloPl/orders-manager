# Sample event sourced application with Command Query Responsibility Segregation

** Event sourcing **

Event store is constructed in database as EventStream table with collection of EventDescriptors. EventStream is fetched by unique aggregate root uuid.

Application keep sending events to kafka broker. Kafka is bound with the use of spring stream
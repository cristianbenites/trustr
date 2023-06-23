package com.nostrrelay.trustr;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RelayTest {

    @Test
    public void createsEventFromJson() {

        Event expectedEvent = new Event().setContent("Hello World");

        Relay relay = new Relay();

        String jsonEvent = """
                {
                    "content": "Hello World"
                }
                """;

        assertThat(relay.getEvent(jsonEvent)).usingRecursiveComparison().isEqualTo(expectedEvent);
    }
}

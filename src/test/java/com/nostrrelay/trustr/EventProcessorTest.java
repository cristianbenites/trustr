package com.nostrrelay.trustr;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class EventProcessorTest {

    private EventProcessor subject;

    @BeforeEach
    public void setup() {
        this.subject = new EventProcessor();
    }

    @Test
    public void givenIncorrectFields_whenConvertFromJSON_thenRaiseException() {
        assertThatThrownBy(() -> subject.convertFromJSON("""
                {
                    "id": "anything",
                    "pubkey": "anything",
                    "created_at": 123,
                    "kind": 1,
                    "tags": [
                        ["e", "anything", "https://relay.damus.io"],
                        ["p", "anything", "https://relay.damus.io"]
                    ],
                    "content": "Hello World",
                    "sig": "anything",
                    "extraField": "thisFieldShouldNotExist"
                }
                """))
                .isInstanceOf(EventProcessor.IncorrectJsonEventException.class)
                .hasMessageStartingWith("Could not process the incoming event:");
    }

    @Test
    public void givenJsonEvent_whenConvertFromJSON_thenReturnsAnEvent() {
        String id = "1a85838f8c124d90a03cd1002b2e33bbdd65f6e8bff0fb8bb07859f616a07a7c";
        String pubkey = "9f66a9048b6193a1d9d677e92c483bc6b04e2d4a1ea7151b0f500ce3e9710b25";
        String eTag = "b38de6d0928cdb0f3922ee0b5777880a21f73f829439a94159bb28209a96e29c";
        String pTag = "522df6e5da52281cdbb13faf283c6687012388ff587aa95629fd72ca84d5fabd";
        String signature = "b41ae11266ed2aa22b72bc72f7b932107a83e1f858457c7bb78f3428bf6f5c54";

        String relayUrl = "https://relay.damus.io";
        long time = Instant.now().getEpochSecond();

        List<List<String>> tags = List.of(
                List.of("e", eTag, relayUrl),
                List.of("p", pTag, relayUrl));

        Event expected = new Event()
                .setId(id)
                .setPubkey(pubkey)
                .setCreated_at(time)
                .setKind(1)
                .setTags(tags)
                .setContent("Hello World")
                .setSig(signature);

        Event event = subject.convertFromJSON(String.format("""
                {
                    "id": "%s",
                    "pubkey": "%s",
                    "created_at": %s,
                    "kind": 1,
                    "tags": [
                        ["e", "%s", "https://relay.damus.io"],
                        ["p", "%s", "https://relay.damus.io"]
                    ],
                    "content": "Hello World",
                    "sig": "%s"
                }
                """, id, pubkey, time, eTag, pTag, signature));

        assertThat(event).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    public void givenJsonEventWithIncorrectId_whenConvertFromJSON_thenThrowsException() {

    }
}

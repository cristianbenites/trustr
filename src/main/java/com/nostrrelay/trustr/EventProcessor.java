package com.nostrrelay.trustr;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class EventProcessor {

    public Event convertFromJSON(String jsonEvent) {
        try {
            return new ObjectMapper().readValue(jsonEvent.replaceAll("'", "\""), Event.class);
        } catch (JsonProcessingException e) {
            throw new IncorrectJsonEventException("Could not process the incoming event: %s", e.getMessage());
        }
    }

    public static class IncorrectJsonEventException extends RuntimeException {
        public IncorrectJsonEventException(String message, Object... args) {
            super(String.format(message, args));
        }
    }
}

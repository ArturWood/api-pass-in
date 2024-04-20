package br.com.dev.api.pass.in.dto.event;

import br.com.dev.api.pass.in.model.event.Event;

public class EventResponseDto {
    private EventDetailDto event;

    public EventResponseDto(Event event, Integer numberOfAttendees) {
        this.event = new EventDetailDto(event.getId(),
                event.getTitle(),
                event.getDetails(),
                event.getSlug(),
                event.getMaximumAttendees(),
                numberOfAttendees);
    }

    public EventDetailDto getEvent() {
        return event;
    }
}

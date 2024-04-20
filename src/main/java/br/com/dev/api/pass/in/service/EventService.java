package br.com.dev.api.pass.in.service;

import br.com.dev.api.pass.in.dto.event.EventIdDto;
import br.com.dev.api.pass.in.dto.event.EventRequestDto;
import br.com.dev.api.pass.in.dto.event.EventResponseDto;
import br.com.dev.api.pass.in.infra.exception.EventNotFoundException;
import br.com.dev.api.pass.in.model.event.Event;
import br.com.dev.api.pass.in.repository.AttendeeRepository;
import br.com.dev.api.pass.in.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.Normalizer;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;
    private final AttendeeRepository attendeeRepository;

    public EventResponseDto getEventDetails(String eventId) {
        Event event = this.getEventById(eventId);
        Integer ammountOfAttendees = this.getAmmountOfAttendeesInEventById(eventId);
        return new EventResponseDto(event, ammountOfAttendees);
    }

    @Transactional
    public EventIdDto createEvent(EventRequestDto dto) {
        Event newEvent = new Event(dto.title(), dto.details(), createSlug(dto.title()), dto.maximumAttendees());
        eventRepository.save(newEvent);
        return new EventIdDto(newEvent.getId());
    }

    private Event getEventById(String eventId) {
        return eventRepository.findById(eventId).orElseThrow(() -> new EventNotFoundException("Evento com o ID: " + eventId + " não encontrado"));
    }

    private Integer getAmmountOfAttendeesInEventById(String eventId) {
        return attendeeRepository.countByEventId(eventId);
    }

    private String createSlug(String title) {
        String normalized = Normalizer.normalize(title, Normalizer.Form.NFD);
        return normalized.replaceAll("[\\p{InCOMBINING_DIACRITICAL_MARKS}]", "")
                .replaceAll("[^\\w\\s]", "")
                .replaceAll("\\s+", "")
                .toLowerCase();
    }
}

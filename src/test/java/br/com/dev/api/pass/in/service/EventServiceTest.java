package br.com.dev.api.pass.in.service;

import br.com.dev.api.pass.in.dto.event.EventIdDto;
import br.com.dev.api.pass.in.dto.event.EventRequestDto;
import br.com.dev.api.pass.in.dto.event.EventResponseDto;
import br.com.dev.api.pass.in.infra.exception.EventNotFoundException;
import br.com.dev.api.pass.in.model.event.Event;
import br.com.dev.api.pass.in.repository.AttendeeRepository;
import br.com.dev.api.pass.in.repository.EventRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EventServiceTest {

    @Mock
    private EventRepository eventRepository;

    @Mock
    private AttendeeRepository attendeeRepository;

    @InjectMocks
    private EventService eventService;

    @Test
    void getEventDetails_shouldReturnEventResponseDto() {
        // Arrange
        String eventId = "eventId";
        Event event = new Event("Test Event", "Test Details", "test-event", 100);
        when(eventRepository.findById(eventId)).thenReturn(java.util.Optional.of(event));
        when(attendeeRepository.countByEventId(eventId)).thenReturn(50);

        // Act
        EventResponseDto eventResponseDto = eventService.getEventDetails(eventId);

        // Assert
        assertNotNull(eventResponseDto);
        assertEquals(event.getTitle(), eventResponseDto.getEvent().title());
        assertEquals(50, eventResponseDto.getEvent().numberOfAttendees());
    }

    @Test
    void createEvent_shouldReturnEventIdDto() {
        // Arrange
        EventRequestDto eventRequestDto = new EventRequestDto("Test Event", "Test Details", 100);
        Event newEvent = new Event("Test Event", "Test Details", "test-event", 100);
        when(eventRepository.save(any())).thenReturn(newEvent);

        // Act
        EventIdDto eventIdDto = eventService.createEvent(eventRequestDto);

        // Assert
        assertNotNull(eventIdDto);
    }

    @Test
    void getEventDetails_withInvalidEventId_shouldThrowEventNotFoundException() {
        // Arrange
        String invalidEventId = "invalidEventId";
        when(eventRepository.findById(invalidEventId)).thenReturn(java.util.Optional.empty());

        // Act & Assert
        assertThrows(EventNotFoundException.class, () -> eventService.getEventDetails(invalidEventId));
    }
}

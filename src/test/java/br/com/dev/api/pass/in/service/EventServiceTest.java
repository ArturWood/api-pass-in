package br.com.dev.api.pass.in.service;

import br.com.dev.api.pass.in.dto.attendee.AttendeeIdDto;
import br.com.dev.api.pass.in.dto.attendee.AttendeeRequestDto;
import br.com.dev.api.pass.in.dto.event.EventIdDto;
import br.com.dev.api.pass.in.dto.event.EventRequestDto;
import br.com.dev.api.pass.in.dto.event.EventResponseDto;
import br.com.dev.api.pass.in.infra.exception.EventIsFullException;
import br.com.dev.api.pass.in.infra.exception.EventNotFoundException;
import br.com.dev.api.pass.in.model.attendee.Attendee;
import br.com.dev.api.pass.in.model.event.Event;
import br.com.dev.api.pass.in.repository.AttendeeRepository;
import br.com.dev.api.pass.in.repository.EventRepository;
import org.junit.jupiter.api.DisplayName;
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

    @Mock
    private AttendeeService attendeeService;

    @InjectMocks
    private EventService eventService;

    @Test
    @DisplayName("Deveria retornar um EventResponseDto")
    void scenario01() {
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
    @DisplayName("Deveria retornar um EventIdDto")
    void scenario02() {
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
    @DisplayName("Deveria lançar EventNotFoundException com eventId invalido")
    void scenario03() {
        // Arrange
        String invalidEventId = "invalidEventId";
        when(eventRepository.findById(invalidEventId)).thenReturn(java.util.Optional.empty());

        // Act & Assert
        assertThrows(EventNotFoundException.class, () -> eventService.getEventDetails(invalidEventId));
    }

    @Test
    @DisplayName("Deveria retornar um AttendeeIdDto ao registrar um participante no evento")
    void scenario04() {
        // Arrange
        String eventId = "eventId";
        AttendeeRequestDto attendeeRequestDto = new AttendeeRequestDto("John Doe", "john@example.com");
        Event event = new Event("Test Event", "Test Details", "test-event", 100);
        when(eventRepository.findById(eventId)).thenReturn(java.util.Optional.of(event));
        when(attendeeRepository.countByEventId(eventId)).thenReturn(50);
        doNothing().when(attendeeService).verifyAttendeeSubscription(anyString(), anyString());
        when(attendeeService.registerAttendee(any(Attendee.class))).thenReturn(new Attendee("John Doe", "john@example.com", event));

        // Act
        AttendeeIdDto attendeeIdDto = eventService.registerAttendeeOnEvent(eventId, attendeeRequestDto);

        // Assert
        assertNotNull(attendeeIdDto);
    }

    @Test
    @DisplayName("Deveria lancar EventIsFullException registrar um participante no evento cheio")
    void scenario05() {
        // Arrange
        String eventId = "eventId";
        AttendeeRequestDto attendeeRequestDto = new AttendeeRequestDto("John Doe", "john@example.com");
        Event event = new Event("Test Event", "Test Details", "test-event", 100);
        when(eventRepository.findById(eventId)).thenReturn(java.util.Optional.of(event));
        when(attendeeRepository.countByEventId(eventId)).thenReturn(100); // event is full

        // Act & Assert
        assertThrows(EventIsFullException.class, () -> eventService.registerAttendeeOnEvent(eventId, attendeeRequestDto));
    }
}

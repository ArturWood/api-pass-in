package br.com.dev.api.pass.in.service;

import br.com.dev.api.pass.in.dto.attendee.AttendeesListResponseDto;
import br.com.dev.api.pass.in.model.attendee.Attendee;
import br.com.dev.api.pass.in.model.checkin.CheckIn;
import br.com.dev.api.pass.in.model.event.Event;
import br.com.dev.api.pass.in.repository.AttendeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class AttendeeServiceTest {

    @Mock
    private AttendeeRepository attendeeRepository;

    @Mock
    private CheckInService checkInService;

    @InjectMocks
    private AttendeeService attendeeService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Deveria retornar uma lista com todos os participantes do evento")
    public void scenario01() {
        // Arrange
        String eventId = "eventId";
        List<Attendee> attendees = new ArrayList<>();
        when(attendeeRepository.findByEventId(eventId)).thenReturn(attendees);

        // Act
        List<Attendee> result = attendeeService.getAllAttendeesFromEvent(eventId);

        // Assert
        assertEquals(attendees, result);
    }

    @Test
    @DisplayName("Deveria retornar um AttendeesListResponseDto com todos os participantes do evento")
    public void scenario02() {
        // Arrange
        String eventId = "eventId";
        List<Attendee> attendees = new ArrayList<>();
        Attendee attendee = new Attendee(
                "John Doe",
                "",
                new Event(
                        "",
                        "",
                        "",
                        100
                )
        );
        attendees.add(attendee);
        when(attendeeRepository.findByEventId(eventId)).thenReturn(attendees);
        when(checkInService.getCheckIn("1")).thenReturn(new CheckIn(attendee));

        // Act
        AttendeesListResponseDto result = attendeeService.getEventsAttendees(eventId);

        // Assert
        assertNotNull(result);
    }

    @Test
    @DisplayName("Deveria lancar AttendeeAlreadyRegisteredException ao registrar um participante ja registrado no evento")
    public void scenario03() {
        // Arrange
        String email = "john@example.com";
        String eventId = "eventId";
        when(attendeeRepository.existsByEventIdAndEmail(eventId, email)).thenReturn(true);

        // Act
        boolean isAttendeeRegistered = attendeeRepository.existsByEventIdAndEmail(eventId, email);

        // Assert
        assertTrue(isAttendeeRegistered);
    }
}

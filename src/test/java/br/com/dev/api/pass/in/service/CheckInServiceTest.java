package br.com.dev.api.pass.in.service;

import br.com.dev.api.pass.in.infra.exception.CheckInNotFoundException;
import br.com.dev.api.pass.in.model.attendee.Attendee;
import br.com.dev.api.pass.in.model.checkin.CheckIn;
import br.com.dev.api.pass.in.repository.CheckInRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class CheckInServiceTest {

    @Mock
    private CheckInRepository checkInRepository;

    @InjectMocks
    private CheckInService checkInService;

    @Mock
    private Attendee attendee;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Deveria salvar uma nova entidade check in")
    public void scenario01() {
        // Arrange
        //Attendee attendee = new Attendee("attendeeId");
        when(checkInRepository.existsByAttendeeId(attendee.getId())).thenReturn(false);

        // Act
        checkInService.registerCheckIn(attendee);

        // Assert
        verify(checkInRepository, times(1)).save(any());
    }

    @Test
    @DisplayName("Deveria retornar uma entidade Check In pelo ID")
    public void scenario02() {
        // Arrange
        String attendeeId = "attendeeId";
        CheckIn checkIn = new CheckIn(attendee);
        when(checkInRepository.findByAttendeeId(attendeeId)).thenReturn(java.util.Optional.of(checkIn));

        // Act
        CheckIn result = checkInService.getCheckIn(attendeeId);

        // Assert
        assertEquals(checkIn, result);
    }

    @Test
    @DisplayName("Deveria lancar CheckInNotFoundException ao buscar por attendeeId invalido ou inexistente")
    public void scenario03() {
        // Arrange
        String attendeeId = "attendeeId";

        when(checkInRepository.findByAttendeeId(attendeeId)).thenReturn(java.util.Optional.empty());
        // Act & Assert
        assertThrows(CheckInNotFoundException.class, () -> checkInService.getCheckIn(attendeeId));
    }
}

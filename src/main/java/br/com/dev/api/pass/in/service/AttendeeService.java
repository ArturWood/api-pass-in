package br.com.dev.api.pass.in.service;

import br.com.dev.api.pass.in.dto.attendee.AttendeeBadgeDto;
import br.com.dev.api.pass.in.dto.attendee.AttendeeBadgeResponseDto;
import br.com.dev.api.pass.in.dto.attendee.AttendeeDetailDto;
import br.com.dev.api.pass.in.dto.attendee.AttendeesListResponseDto;
import br.com.dev.api.pass.in.infra.exception.AttendeeAlreadyRegisteredException;
import br.com.dev.api.pass.in.infra.exception.AttendeeNotFoundException;
import br.com.dev.api.pass.in.model.attendee.Attendee;
import br.com.dev.api.pass.in.model.checkin.CheckIn;
import br.com.dev.api.pass.in.repository.AttendeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AttendeeService {
    private final AttendeeRepository attendeeRepository;
    private final CheckInService checkInService;

    public List<Attendee> getAllAttendeesFromEvent(String eventId) {
        return attendeeRepository.findByEventId(eventId);
    }

    public AttendeesListResponseDto getEventsAttendees(String eventId) {
        List<Attendee> attendeeList = this.getAllAttendeesFromEvent(eventId);

        List<AttendeeDetailDto> attendeeDetailDtoList = attendeeList.stream().map(attendee -> {
            CheckIn checkIn = checkInService.getCheckIn(attendee.getId());
            LocalDateTime checkedInAt = checkIn.getCreatedAt();
            return new AttendeeDetailDto(attendee.getId(), attendee.getName(), attendee.getEmail(), attendee.getCreatedAt(), checkedInAt);
        }).toList();

        return new AttendeesListResponseDto(attendeeDetailDtoList);
    }

    public void verifyAttendeeSubscription(String email, String eventId) {
        Boolean isAttendeeRegistered = attendeeRepository.existsByEventIdAndEmail(eventId, email);
        if (isAttendeeRegistered) {
            throw new AttendeeAlreadyRegisteredException("Attendee is already registered");
        }
    }

    @Transactional
    public Attendee registerAttendee(Attendee newAttendee) {
        attendeeRepository.save(newAttendee);
        return newAttendee;
    }

    public AttendeeBadgeResponseDto getAttendeeBadge(String attendeeId, UriComponentsBuilder uriBuilder) {
        Attendee attendee = this.getAttendee(attendeeId);
        var uri = uriBuilder.path("/attendees/{attendeeId}/check-in").buildAndExpand(attendeeId).toUri().toString();
        return new AttendeeBadgeResponseDto(new AttendeeBadgeDto(attendee.getName(), attendee.getEmail(), uri, attendee.getEvent().getId()));
    }

    public void checkInAttendee(String attendeeId) {
        Attendee attendee = this.getAttendee(attendeeId);
        checkInService.registerCheckIn(attendee);
    }

    private Attendee getAttendee(String attendeeId) {
        return attendeeRepository.findById(attendeeId).orElseThrow(() -> new AttendeeNotFoundException("Participante com o ID: " + attendeeId + " não encontrado"));
    }
}

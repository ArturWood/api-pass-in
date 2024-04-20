package br.com.dev.api.pass.in.controller;

import br.com.dev.api.pass.in.dto.attendee.AttendeeIdDto;
import br.com.dev.api.pass.in.dto.attendee.AttendeeRequestDto;
import br.com.dev.api.pass.in.dto.attendee.AttendeesListResponseDto;
import br.com.dev.api.pass.in.dto.event.EventIdDto;
import br.com.dev.api.pass.in.dto.event.EventRequestDto;
import br.com.dev.api.pass.in.dto.event.EventResponseDto;
import br.com.dev.api.pass.in.service.AttendeeService;
import br.com.dev.api.pass.in.service.EventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;
    private final AttendeeService attendeeService;

    @GetMapping("/{eventId}")
    public ResponseEntity<EventResponseDto> getEvent(@PathVariable String eventId) {
        EventResponseDto dto = eventService.getEventDetails(eventId);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<EventIdDto> createEvent(@RequestBody @Valid EventRequestDto body, UriComponentsBuilder uriBuilder) {
        EventIdDto dto = eventService.createEvent(body);
        var uri = uriBuilder.path("/events/{id}").buildAndExpand(dto.eventId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @GetMapping("/{eventId}/attendees")
    public ResponseEntity<AttendeesListResponseDto> getEventAttendees(@PathVariable String eventId) {
        AttendeesListResponseDto attendeesListResponse = attendeeService.getEventsAttendees(eventId);
        return ResponseEntity.ok(attendeesListResponse);
    }

    @PostMapping("/{eventId}/attendees")
    public ResponseEntity<AttendeeIdDto> registerParticipant(@PathVariable String eventId, @RequestBody @Valid AttendeeRequestDto body, UriComponentsBuilder uriBuilder) {
        AttendeeIdDto dto = eventService.registerAttendeeOnEvent(eventId, body);
        var uri = uriBuilder.path("/attendees/{attendeeId}/badge").buildAndExpand(dto.attendeeId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }
}

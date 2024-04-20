package br.com.dev.api.pass.in.controller;

import br.com.dev.api.pass.in.dto.attendee.AttendeeBadgeResponseDto;
import br.com.dev.api.pass.in.service.AttendeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/attendees")
@RequiredArgsConstructor
public class AttendeeController {
    private final AttendeeService attendeeService;

    @GetMapping("/{attendeeId}/badge")
    public ResponseEntity<AttendeeBadgeResponseDto> getAttendeeBadge(@PathVariable String attendeeId, UriComponentsBuilder uriBuilder) {
        AttendeeBadgeResponseDto attendeeBadge = attendeeService.getAttendeeBadge(attendeeId, uriBuilder);
        return ResponseEntity.ok(attendeeBadge);
    }

    @PostMapping("/{attendeeId}/check-in")
    public ResponseEntity registerCheckIn(@PathVariable String attendeeId, UriComponentsBuilder uriBuilder) {
        attendeeService.checkInAttendee(attendeeId);
        var uri = uriBuilder.path("/attendees/{attendeeId}/badge").buildAndExpand(attendeeId).toUri();
        return ResponseEntity.created(uri).build();
    }
}

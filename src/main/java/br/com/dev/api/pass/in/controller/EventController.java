package br.com.dev.api.pass.in.controller;

import br.com.dev.api.pass.in.dto.event.EventIdDto;
import br.com.dev.api.pass.in.dto.event.EventRequestDto;
import br.com.dev.api.pass.in.dto.event.EventResponseDto;
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
}

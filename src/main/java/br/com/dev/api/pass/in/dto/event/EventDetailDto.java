package br.com.dev.api.pass.in.dto.event;

public record EventDetailDto(
        String id,
        String title,
        String details,
        String slug,
        Integer maximumAttendees,
        Integer numberOfAttendees
) {
}

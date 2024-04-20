package br.com.dev.api.pass.in.dto.attendee;

public record AttendeeBadgeDto(
        String name,
        String email,
        String checkInUrl,
        String eventId
) {
}

package br.com.dev.api.pass.in.dto.attendee;

import java.time.LocalDateTime;

public record AttendeeDetailDto(
        String id,
        String name,
        String email,
        LocalDateTime createdAt,
        LocalDateTime checkedInAt
) {
}

package br.com.dev.api.pass.in.dto.attendee;

import java.util.List;

public record AttendeesListResponseDto(
        List<AttendeeDetailDto> attendees
) {
}

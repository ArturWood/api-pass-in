package br.com.dev.api.pass.in.dto.attendee;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AttendeeRequestDto(
        @NotBlank
        String name,
        @NotBlank
        @Email
        String email
) {
}

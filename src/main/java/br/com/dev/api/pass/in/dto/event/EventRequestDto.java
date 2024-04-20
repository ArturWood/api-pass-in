package br.com.dev.api.pass.in.dto.event;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record EventRequestDto(
        @NotBlank(message = "Titulo nao pode ser vazio")
        String title,
        @NotBlank(message = "Detalhes nao pode ser vazio")
        String details,
        @Positive(message = "O numero de participantes deve ser maior que zero")
        @Min(value = 50, message = "O numero de participantes deve ser maior que 100")
        @Max(value = 5001, message = "O numero de participantes nao pode ultrapassar 5000")
        Integer maximumAttendees
) {
}

package hr.fer.oap.dao.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record EditOglasDTO(
        @NotNull Long oglasId,
        @NotBlank String naziv,
        @NotBlank String opis,
        @NotNull Float pocetnaCijena,
        @NotNull LocalDateTime pocetnoVrijeme,
        @NotNull LocalDateTime zavrsnoVrijeme,
        @NotNull Long mjesto
) {
}
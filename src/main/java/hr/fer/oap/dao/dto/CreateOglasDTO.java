package hr.fer.oap.dao.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

public record CreateOglasDTO(
        @NotBlank String naziv,
        @NotBlank String opis,
        @NotNull Float pocetnaCijena,
        @NotNull LocalDateTime pocetnoVrijeme,
        @NotNull LocalDateTime zavrsnoVrijeme,
        @NotNull Long mjesto,
        @NotNull List<Long> kategorije
) {
}

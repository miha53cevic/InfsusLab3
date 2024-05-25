package hr.fer.oap.dao.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateEditMjestoDTO(
        @NotBlank String mjestoNaziv,
        @NotBlank String drzavaOznaka
) {
}

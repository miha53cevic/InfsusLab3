package hr.fer.oap.dao.dto;

import jakarta.validation.constraints.NotNull;

public record EditPripadaKategorijiDTO(
        @NotNull Long oglasId,
        @NotNull Long kategorijaId,
        @NotNull Long staraKategorijaId
) {
}

package hr.fer.oap.dao.dto;

import jakarta.validation.constraints.NotNull;

public record CreatePripadaKategorijiDTO(
        @NotNull Long oglasId,
        @NotNull Long kategorijaId
) {
}

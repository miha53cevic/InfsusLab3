package hr.fer.oap.dao.dto;

import jakarta.validation.constraints.NotNull;

public record DeletePripadaKategorijiDTO(
        @NotNull Long oglasId,
        @NotNull Long kategorijaId
) {
}

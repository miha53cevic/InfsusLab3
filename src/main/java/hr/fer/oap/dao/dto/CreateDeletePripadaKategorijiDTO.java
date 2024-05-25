package hr.fer.oap.dao.dto;

import jakarta.validation.constraints.NotNull;

public record CreateDeletePripadaKategorijiDTO(
        @NotNull Long oglasId,
        @NotNull Long kategorijaId
) {
}

package hr.fer.oap.dao.dto;

import hr.fer.oap.dao.dto.validation.PresentTime;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.List;

public record CreateOglasDTO(
        @NotBlank @Length(max = 100) String naziv,
        @NotBlank @Length(max = 300) String opis,
        @NotNull @Positive Float pocetnaCijena,
        @NotNull @PresentTime LocalDateTime pocetnoVrijeme,
        @NotNull @PresentTime LocalDateTime zavrsnoVrijeme,
        @NotNull Long mjesto,
        @NotNull List<Long> kategorije
) {
}

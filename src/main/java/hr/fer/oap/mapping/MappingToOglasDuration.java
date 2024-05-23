package hr.fer.oap.mapping;

import hr.fer.oap.domain.Oglas;

import java.time.Duration;
import java.time.LocalDateTime;
public class MappingToOglasDuration {
    public static Long oglasToDuration(Oglas oglas) {
        var now = LocalDateTime.now();
        var duration = Duration.between(now, oglas.getPocetnoVrijeme());
        return duration.toHours() < 0 ? 0L : duration.toHours();
    }
}

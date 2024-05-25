package hr.fer.oap.mapping;

import hr.fer.oap.domain.Oglas;

import java.time.Duration;
import java.time.LocalDateTime;

public class MappingToOglasDuration {
    public static long oglasToDuration(Oglas oglas) {
        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(now, oglas.getZavrsnoVrijeme());
        return Math.max(duration.toHours(), 0);
    }
}

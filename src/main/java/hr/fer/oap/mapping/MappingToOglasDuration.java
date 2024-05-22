package hr.fer.oap.mapping;

import hr.fer.oap.domain.Oglas;

import java.time.Duration;

public class MappingToOglasDuration {
    public static Long oglasToDuration(Oglas oglas) {
        var duration = Duration.between(oglas.getPocetnoVrijeme(), oglas.getZavrsnoVrijeme());
        return duration.toHours();
    }
}

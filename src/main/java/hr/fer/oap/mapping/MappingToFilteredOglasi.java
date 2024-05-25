package hr.fer.oap.mapping;

import hr.fer.oap.domain.Oglas;

import java.time.LocalDateTime;
import java.util.List;

public class MappingToFilteredOglasi {
    public static List<Oglas> fromOglasiByNaziv(List<Oglas> oglasiList, String naziv) {
        return oglasiList.stream()
                .filter(oglas -> oglas.getNaziv().toLowerCase().contains(naziv.toLowerCase()))
                .toList();
    }

    public static List<Oglas> fromOglasiByTime(List<Oglas> oglasiList, Boolean istekli) {
        if (istekli) {
            return oglasiList.stream()
                    .filter(oglas -> !oglas.getZavrsnoVrijeme().isAfter(LocalDateTime.now()))
                    .toList();
        } else {
            return oglasiList.stream()
                    .filter(oglas -> oglas.getZavrsnoVrijeme().isAfter(LocalDateTime.now()))
                    .toList();
        }
    }
}

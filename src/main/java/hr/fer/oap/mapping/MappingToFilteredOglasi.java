package hr.fer.oap.mapping;

import hr.fer.oap.domain.Oglas;

import java.util.List;

public class MappingToFilteredOglasi {
    public static List<Oglas> fromOglasiByNaziv(List<Oglas> oglasiList, String naziv) {
        return oglasiList.stream()
                .filter(oglas -> oglas.getNaziv().toLowerCase().contains(naziv.toLowerCase()))
                .toList();
    }
}

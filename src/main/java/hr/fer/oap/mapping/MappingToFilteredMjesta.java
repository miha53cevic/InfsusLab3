package hr.fer.oap.mapping;

import hr.fer.oap.domain.Mjesto;

import java.util.List;

public class MappingToFilteredMjesta {
    public static List<Mjesto> fromMjestaByNaziv(List<Mjesto> mjestaList, String naziv) {
        return mjestaList.stream()
                .filter(mjesto -> mjesto.getNaziv().toLowerCase().contains(naziv.toLowerCase()))
                .toList();
    }

    public static List<Mjesto> fromMjestaByDrzavaOznaka(List<Mjesto> mjestaList, String drzavaOznaka) {
        return mjestaList.stream()
                .filter(mjesto -> mjesto.getDrzava().getOznaka().toLowerCase().contains(drzavaOznaka.toLowerCase()))
                .toList();
    }
}

package hr.fer.oap.service;

import hr.fer.oap.domain.Kategorija;

import java.util.List;

public interface KategorijaService {
    List<Kategorija> fetchAll();
    Kategorija createNewKategorija(Kategorija kategorija);
}

package hr.fer.oap.service;

import hr.fer.oap.domain.Kategorija;
import hr.fer.oap.domain.Oglas;

import java.util.List;
import java.util.Optional;

public interface KategorijaService {
    List<Kategorija> fetchAll();
    Kategorija createNewKategorija(Kategorija kategorija);
    List<Kategorija> findAllByOglas(Oglas oglas);
    Optional<Kategorija> findById(Long id);
}

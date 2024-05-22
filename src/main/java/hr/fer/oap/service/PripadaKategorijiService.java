package hr.fer.oap.service;

import hr.fer.oap.domain.Kategorija;
import hr.fer.oap.domain.Oglas;
import hr.fer.oap.domain.Pripadakategoriji;

public interface PripadaKategorijiService {
    Pripadakategoriji addKategorijaToOglas(Kategorija kategorija, Oglas oglas);
}

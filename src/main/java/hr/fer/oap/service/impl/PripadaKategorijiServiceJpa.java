package hr.fer.oap.service.impl;

import hr.fer.oap.dao.repository.PripadaKategorijiRepository;
import hr.fer.oap.domain.Kategorija;
import hr.fer.oap.domain.Oglas;
import hr.fer.oap.domain.Pripadakategoriji;
import hr.fer.oap.service.PripadaKategorijiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PripadaKategorijiServiceJpa implements PripadaKategorijiService {
    private final PripadaKategorijiRepository pripadaKategorijiRepository;

    @Autowired
    public PripadaKategorijiServiceJpa(PripadaKategorijiRepository pripadaKategorijiRepository) {
        this.pripadaKategorijiRepository = pripadaKategorijiRepository;
    }

    @Override
    public Pripadakategoriji addKategorijaToOglas(Kategorija kategorija, Oglas oglas) {
        return pripadaKategorijiRepository.save(new Pripadakategoriji(oglas, kategorija));
    }
}

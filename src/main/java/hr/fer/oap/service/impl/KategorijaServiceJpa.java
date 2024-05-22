package hr.fer.oap.service.impl;

import hr.fer.oap.dao.repository.KategorijaRepository;
import hr.fer.oap.domain.Kategorija;
import hr.fer.oap.service.KategorijaService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KategorijaServiceJpa implements KategorijaService {
    private final KategorijaRepository kategorijaRepository;

    public KategorijaServiceJpa(KategorijaRepository repository) {
        this.kategorijaRepository = repository;
    }

    @Override
    public List<Kategorija> fetchAll() {
        return this.kategorijaRepository.findAll();
    }

    @Override
    public Kategorija createNewKategorija(Kategorija kategorija) {
        return kategorijaRepository.save(kategorija);
    }
}

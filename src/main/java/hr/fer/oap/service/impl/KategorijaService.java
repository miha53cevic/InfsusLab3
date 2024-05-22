package hr.fer.oap.service.impl;

import hr.fer.oap.dao.repository.KategorijaRepository;
import hr.fer.oap.dao.repository.PripadaKategorijiRepository;
import hr.fer.oap.domain.Kategorija;
import hr.fer.oap.domain.Oglas;
import hr.fer.oap.domain.Pripadakategoriji;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KategorijaService implements hr.fer.oap.service.KategorijaService {
    private final KategorijaRepository kategorijaRepository;
    private final PripadaKategorijiRepository pripadaKategorijiRepository;

    @Autowired
    public KategorijaService(KategorijaRepository repository, PripadaKategorijiRepository pripadaKategorijiRepository) {
        this.kategorijaRepository = repository;
        this.pripadaKategorijiRepository = pripadaKategorijiRepository;
    }

    @Override
    public List<Kategorija> fetchAll() {
        return this.kategorijaRepository.findAll();
    }

    @Override
    public Kategorija createNewKategorija(Kategorija kategorija) {
        return kategorijaRepository.save(kategorija);
    }

    @Override
    public List<Kategorija> findAllByOglas(Oglas oglas) {
        var pripadaKategorijiLista = pripadaKategorijiRepository.findAllByOglas(oglas);
        return pripadaKategorijiLista.stream().map(Pripadakategoriji::getKategorija).toList();
    }

    @Override
    public Optional<Kategorija> findById(Long id) {
        return kategorijaRepository.findById(id);
    }
}

package hr.fer.oap.service.impl;

import hr.fer.oap.dao.dto.CreatePripadaKategorijiDTO;
import hr.fer.oap.dao.dto.DeletePripadaKategorijiDTO;
import hr.fer.oap.dao.repository.KategorijaRepository;
import hr.fer.oap.dao.repository.OglasRepository;
import hr.fer.oap.dao.repository.PripadaKategorijiRepository;
import hr.fer.oap.domain.Kategorija;
import hr.fer.oap.domain.Oglas;
import hr.fer.oap.domain.Pripadakategoriji;
import hr.fer.oap.domain.PripadakategorijiId;
import hr.fer.oap.service.PripadaKategorijiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PripadaKategorijiServiceJpa implements PripadaKategorijiService {
    private final PripadaKategorijiRepository pripadaKategorijiRepository;
    private final OglasRepository oglasRepository;
    private final KategorijaRepository kategorijaRepository;

    @Autowired
    public PripadaKategorijiServiceJpa(
            PripadaKategorijiRepository pripadaKategorijiRepository,
            OglasRepository oglasRepository,
            KategorijaRepository kategorijaRepository
    ) {
        this.pripadaKategorijiRepository = pripadaKategorijiRepository;
        this.oglasRepository = oglasRepository;
        this.kategorijaRepository = kategorijaRepository;
    }

    @Override
    public Pripadakategoriji addKategorijaToOglas(Kategorija kategorija, Oglas oglas) {
        return pripadaKategorijiRepository.save(new Pripadakategoriji(oglas, kategorija));
    }

    @Override
    public void delete(DeletePripadaKategorijiDTO dto) {
        var pripadaKategoriji = this.pripadaKategorijiRepository.findById(new PripadakategorijiId(dto.oglasId(), dto.kategorijaId())).get();
        this.pripadaKategorijiRepository.delete(pripadaKategoriji);
    }

    @Override
    public Pripadakategoriji create(CreatePripadaKategorijiDTO dto) {
        var oglas = oglasRepository.findById(dto.oglasId()).get();
        var kategorija = kategorijaRepository.findById(dto.kategorijaId()).get();
        var pripadaKategoriji = pripadaKategorijiRepository.findById(new PripadakategorijiId(oglas.getId(), kategorija.getId()));
        if (pripadaKategoriji.isPresent()) {
            throw new Error("Kategorija vec postoji na tom oglasu!");
        }
        return this.pripadaKategorijiRepository.save(new Pripadakategoriji(oglas, kategorija));
    }
}

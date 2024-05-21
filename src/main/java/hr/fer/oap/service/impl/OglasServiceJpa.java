package hr.fer.oap.service.impl;

import hr.fer.oap.dao.dto.CreateOglasDTO;
import hr.fer.oap.dao.repository.OglasRepository;
import hr.fer.oap.domain.Korisnik;
import hr.fer.oap.domain.Mjesto;
import hr.fer.oap.domain.Oglas;
import hr.fer.oap.service.MjestoService;
import hr.fer.oap.service.OglasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@Service
public class OglasServiceJpa implements OglasService {
    private final OglasRepository oglasRepository;

    @Autowired
    public OglasServiceJpa(OglasRepository oglasRepository) {
        this.oglasRepository = oglasRepository;
    }

    @Override
    public List<Oglas> fetchAll() {
        return oglasRepository.findAll();
    }

    @Override
    public Optional<Oglas> fetchById(Long id) {
        return this.oglasRepository.findById(id);
    }

    @Override
    public List<Oglas> fetchByKorisnikId(Long korisnikId) {
        return this.oglasRepository.findBySifraKorisnik(korisnikId);
    }

    @Override
    public Oglas createOglas(CreateOglasDTO dto, Mjesto mjesto, Korisnik korisnik) {
        var pocetnoVrijeme = dto.getPocetnoVrijeme().atZone(ZoneId.systemDefault()).toInstant();
        var zavrsnoVrijeme = dto.getZavrsnoVrijeme().atZone(ZoneId.systemDefault()).toInstant();
        var oglas = new Oglas(dto.getNaziv(), dto.getOpis(), dto.getPocetnaCijena(),
                pocetnoVrijeme, zavrsnoVrijeme, korisnik, mjesto);
        return this.oglasRepository.save(oglas);
    }
}

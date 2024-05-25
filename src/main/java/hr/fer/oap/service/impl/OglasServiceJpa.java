package hr.fer.oap.service.impl;

import hr.fer.oap.dao.dto.CreateOglasDTO;
import hr.fer.oap.dao.dto.EditOglasDTO;
import hr.fer.oap.dao.repository.MjestoRepository;
import hr.fer.oap.dao.repository.OglasRepository;
import hr.fer.oap.dao.repository.PripadaKategorijiRepository;
import hr.fer.oap.domain.Kategorija;
import hr.fer.oap.domain.Korisnik;
import hr.fer.oap.domain.Oglas;
import hr.fer.oap.domain.Pripadakategoriji;
import hr.fer.oap.service.OglasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class OglasServiceJpa implements OglasService {
    private final OglasRepository oglasRepository;
    private final MjestoRepository mjestoRepository;
    private final PripadaKategorijiRepository pripadaKategorijiRepository;

    @Autowired
    public OglasServiceJpa(OglasRepository oglasRepository, MjestoRepository mjestoRepository,
                           PripadaKategorijiRepository pripadaKategorijiRepository) {
        this.oglasRepository = oglasRepository;
        this.mjestoRepository = mjestoRepository;
        this.pripadaKategorijiRepository = pripadaKategorijiRepository;
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
    public Oglas createOglas(CreateOglasDTO dto, Korisnik korisnik) {
        var mjesto = mjestoRepository.findById(dto.mjesto()).get();
        var oglas = new Oglas(dto.naziv(), dto.opis(), dto.pocetnaCijena(),
                dto.pocetnoVrijeme(), dto.zavrsnoVrijeme(), korisnik, mjesto);
        return this.oglasRepository.save(oglas);
    }

    @Override
    public List<Oglas> findAllByKategorija(Kategorija kategorija) {
        var pripadaOglasuLista = pripadaKategorijiRepository.findAllByKategorija(kategorija);
        return pripadaOglasuLista.stream().map(Pripadakategoriji::getOglas).toList();
    }

    @Override
    public Oglas editOglas(EditOglasDTO dto) {
        var mjesto = mjestoRepository.findById(dto.mjesto()).get();
        var oglas = oglasRepository.findById(dto.oglasId()).get();
        oglas.setNaziv(dto.naziv());
        oglas.setOpis(dto.opis());
        oglas.setMjesto(mjesto);
        oglas.setPocetnaCijena(dto.pocetnaCijena());
        oglas.setPocetnoVrijeme(dto.pocetnoVrijeme());
        oglas.setZavrsnoVrijeme(dto.zavrsnoVrijeme());
        return oglasRepository.save(oglas);
    }

    @Override
    @Transactional
    public void deleteOglas(Long oglasId) {
        var oglas = oglasRepository.findById(oglasId).get();
        this.pripadaKategorijiRepository.deleteByOglas(oglas);
        this.oglasRepository.delete(oglas);
    }
}

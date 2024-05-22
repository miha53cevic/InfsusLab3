package hr.fer.oap.service.impl;

import hr.fer.oap.dao.repository.KorisnikRepository;
import hr.fer.oap.domain.Korisnik;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class KorisnikService implements hr.fer.oap.service.KorisnikService {
    private final KorisnikRepository korisnikRepository;

    @Autowired
    public KorisnikService(KorisnikRepository korisnikRepository) {
        this.korisnikRepository = korisnikRepository;
    }

    @Override
    public Optional<Korisnik> fetchByUsername(String username) {
        return korisnikRepository.findByIme(username);
    }

    @Override
    public Korisnik createKorisnik(Korisnik korisnik) {
        return korisnikRepository.save(korisnik);
    }
}

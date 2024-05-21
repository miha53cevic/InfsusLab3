package hr.fer.oap.service.impl;

import hr.fer.oap.dao.repository.KorisnikRepository;
import hr.fer.oap.domain.Korisnik;
import hr.fer.oap.service.KorisnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class KorisnikServiceJpa implements KorisnikService {
    private KorisnikRepository korisnikRepository;

    @Autowired
    public KorisnikServiceJpa(KorisnikRepository korisnikRepository) {
        this.korisnikRepository = korisnikRepository;
    }

    @Override
    public Optional<Korisnik> fetchByUsername(String username) {
        return korisnikRepository.findByIme(username);
    }
}

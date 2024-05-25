package hr.fer.oap.service;

import hr.fer.oap.domain.Korisnik;

import java.util.Optional;

public interface KorisnikService {
    Optional<Korisnik> fetchByUsername(String username);

    Korisnik createKorisnik(Korisnik korisnik);
}

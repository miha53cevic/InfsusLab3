package hr.fer.oap.service;

import hr.fer.oap.dao.dto.CreateEditOglasDTO;
import hr.fer.oap.domain.Korisnik;
import hr.fer.oap.domain.Oglas;

import java.util.List;
import java.util.Optional;

public interface OglasService {
    List<Oglas> fetchAll();
    Optional<Oglas> fetchById(Long id);
    List<Oglas> fetchByKorisnikId(Long korisnikId);
    Oglas createOglas(CreateEditOglasDTO dto, Korisnik korisnik);
}

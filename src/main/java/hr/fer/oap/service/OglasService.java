package hr.fer.oap.service;

import hr.fer.oap.domain.Oglas;

import java.util.List;
import java.util.Optional;

public interface OglasService {
    List<Oglas> fetchAll();
    Optional<Oglas> fetchById(Long id);
}

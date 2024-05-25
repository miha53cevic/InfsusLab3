package hr.fer.oap.service;

import hr.fer.oap.domain.Mjesto;

import java.util.List;
import java.util.Optional;

public interface MjestoService {
    List<Mjesto> fetchByDrzavaOznaka(String drzavaOznaka);

    Optional<Mjesto> fetchById(Long id);
}

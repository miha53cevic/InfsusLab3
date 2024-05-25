package hr.fer.oap.service;

import hr.fer.oap.dao.dto.CreateEditMjestoDTO;
import hr.fer.oap.domain.Mjesto;

import java.util.List;
import java.util.Optional;

public interface MjestoService {
    List<Mjesto> fetchByDrzavaOznaka(String drzavaOznaka);

    Optional<Mjesto> fetchById(Long id);

    List<Mjesto> fetchAll();

    void deleteById(Long mjestoId);

    Mjesto createMjesto(CreateEditMjestoDTO dto);

    Mjesto editMjesto(Mjesto mjesto);
}

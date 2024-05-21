package hr.fer.oap.service.impl;

import hr.fer.oap.dao.repository.OglasRepository;
import hr.fer.oap.domain.Oglas;
import hr.fer.oap.service.OglasService;

import java.util.List;
import java.util.Optional;

public class OglasServiceJpa implements OglasService {
    private final OglasRepository oglasRepository;

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
}

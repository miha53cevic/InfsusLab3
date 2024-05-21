package hr.fer.oap.service.impl;

import hr.fer.oap.dao.repository.MjestoRepository;
import hr.fer.oap.domain.Mjesto;
import hr.fer.oap.service.MjestoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MjestoServiceJpa implements MjestoService {
    private final MjestoRepository mjestoRepository;

    @Autowired
    public MjestoServiceJpa(MjestoRepository mjestoRepository) {
        this.mjestoRepository = mjestoRepository;
    }

    @Override
    public List<Mjesto> fetchByDrzavaOznaka(String drzavaOznaka) {
        return mjestoRepository.fetchByDrzavaOznaka(drzavaOznaka);
    }

    @Override
    public Optional<Mjesto> fetchById(Long id) {
        return mjestoRepository.findById(id);
    }
}

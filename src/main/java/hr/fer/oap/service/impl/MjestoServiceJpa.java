package hr.fer.oap.service.impl;

import hr.fer.oap.dao.repository.MjestoRepository;
import hr.fer.oap.domain.Mjesto;
import hr.fer.oap.service.MjestoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MjestoServiceJpa implements MjestoService {
    private final MjestoRepository mjestoRepository;

    @Autowired
    public MjestoServiceJpa(MjestoRepository mjestoRepository) {
        this.mjestoRepository = mjestoRepository;
    }

    @Override
    public List<Mjesto> fetchAll() {
        return mjestoRepository.findAll();
    }
}

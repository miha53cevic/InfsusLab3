package hr.fer.oap.service.impl;

import hr.fer.oap.dao.dto.CreateEditMjestoDTO;
import hr.fer.oap.dao.repository.DrzavaRepository;
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
    private final DrzavaRepository drzavaRepository;

    @Autowired
    public MjestoServiceJpa(MjestoRepository mjestoRepository, DrzavaRepository drzavaRepository) {
        this.mjestoRepository = mjestoRepository;
        this.drzavaRepository = drzavaRepository;
    }

    @Override
    public List<Mjesto> fetchByDrzavaOznaka(String drzavaOznaka) {
        return mjestoRepository.fetchByDrzavaOznaka(drzavaOznaka);
    }

    @Override
    public Optional<Mjesto> fetchById(Long id) {
        return mjestoRepository.findById(id);
    }

    @Override
    public List<Mjesto> fetchAll() {
        return this.mjestoRepository.findAll();
    }

    @Override
    public void deleteById(Long mjestoId) {
        this.mjestoRepository.deleteById(mjestoId);
    }

    @Override
    public Mjesto createMjesto(CreateEditMjestoDTO dto) {
        var drzava = drzavaRepository.findById(dto.drzavaOznaka()).get();
        return this.mjestoRepository.save(new Mjesto(dto.mjestoNaziv(), drzava));
    }

    @Override
    public Mjesto editMjesto(Mjesto mjesto) {
        return this.mjestoRepository.save(mjesto);
    }
}

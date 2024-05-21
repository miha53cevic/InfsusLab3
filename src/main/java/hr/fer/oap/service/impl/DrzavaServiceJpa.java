package hr.fer.oap.service.impl;

import hr.fer.oap.dao.repository.DrzavaRepository;
import hr.fer.oap.domain.Drzava;
import hr.fer.oap.service.DrzavaService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DrzavaServiceJpa implements DrzavaService {
    private DrzavaRepository drzavaRepository;

    public DrzavaServiceJpa(DrzavaRepository drzavaRepository) {
        this.drzavaRepository = drzavaRepository;
    }

    @Override
    public List<Drzava> fetchAll() {
        return this.drzavaRepository.findAll(Sort.by(Sort.Direction.ASC, "naziv"));
    }
}

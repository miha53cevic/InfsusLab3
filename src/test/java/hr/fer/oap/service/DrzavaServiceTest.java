package hr.fer.oap.service;

import hr.fer.oap.controller.MojiOglasiController;
import hr.fer.oap.dao.repository.DrzavaRepository;
import hr.fer.oap.domain.Drzava;
import hr.fer.oap.service.impl.DrzavaServiceJpa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DrzavaServiceTest {
    private DrzavaService service;
    private DrzavaRepository drzavaRepository;

    @BeforeEach
    void setUp() {
        drzavaRepository = mock(DrzavaRepository.class);
        service = new DrzavaServiceJpa(drzavaRepository);
    }

    @Test
    void DrzavaServiceTest_FetchAll() {
        List<Drzava> drzavaList = new ArrayList<>();
        Drzava drzava = new Drzava("HR", "Croatia");
        Drzava slovenia = new Drzava("SI", "Slovenia");
        drzavaList.add(drzava);
        drzavaList.add(slovenia);

        when(drzavaRepository.findAll(Sort.by(Sort.Direction.ASC, "naziv"))).thenReturn(drzavaList);
        var result = service.fetchAll();
        assertEquals(drzavaList, result);

        verify(drzavaRepository, times(1)).findAll(Sort.by(Sort.Direction.ASC, "naziv"));
    }
}
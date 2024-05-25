package hr.fer.oap.service;

import hr.fer.oap.dao.repository.MjestoRepository;
import hr.fer.oap.domain.Drzava;
import hr.fer.oap.domain.Mjesto;
import hr.fer.oap.service.impl.MjestoServiceJpa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class MjestoServiceTest {
    private MjestoService service;
    private MjestoRepository mjestoRepository;

    @BeforeEach
    void setUp() {
        mjestoRepository = mock(MjestoRepository.class);
        service = new MjestoServiceJpa(mjestoRepository);
    }

    @Test
    void fetchByDrzavaOznaka() {
        Drzava drzava = new Drzava("HR", "Croatia");
        Mjesto zagreb = new Mjesto("Zagreb", drzava);
        Mjesto split = new Mjesto("Split", drzava);
        List<Mjesto> mjesta = new ArrayList<>();
        mjesta.add(zagreb);
        mjesta.add(split);

        when(mjestoRepository.fetchByDrzavaOznaka(drzava.getOznaka())).thenReturn(mjesta);

        var result = service.fetchByDrzavaOznaka(drzava.getOznaka());
        assertEquals(mjesta, result);

        verify(mjestoRepository, times(1)).fetchByDrzavaOznaka(drzava.getOznaka());
    }

    @Test
    void fetchById() {
        Drzava drzava = new Drzava("HR", "Croatia");
        Mjesto mjesto = new Mjesto("Zagreb", drzava);

        when(mjestoRepository.findById(mjesto.getId())).thenReturn(Optional.of(mjesto));

        var result = service.fetchById(mjesto.getId());
        assertEquals(Optional.of(mjesto), result);

        verify(mjestoRepository, times(1)).findById(mjesto.getId());
    }
}
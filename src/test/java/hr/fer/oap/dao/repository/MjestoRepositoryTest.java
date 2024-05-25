package hr.fer.oap.dao.repository;

import hr.fer.oap.domain.Drzava;
import hr.fer.oap.domain.Korisnik;
import hr.fer.oap.domain.Mjesto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MjestoRepositoryTest {
    @Mock
    private MjestoRepository mjestoRepository;

    @Test
    void fetchByDrzavaOznaka() {
        Drzava drzava = new Drzava("HR", "Croatia");
        Mjesto zagreb = new Mjesto("Zagreb", drzava);
        Mjesto split = new Mjesto("Split", drzava);
        List<Mjesto> mjesta = new ArrayList<>();
        mjesta.add(zagreb);
        mjesta.add(split);

        when(mjestoRepository.fetchByDrzavaOznaka(drzava.getOznaka())).thenReturn(mjesta);

        List<Mjesto> result = mjestoRepository.fetchByDrzavaOznaka(drzava.getOznaka());

        assertEquals(mjesta, result);

        verify(mjestoRepository, times(1)).fetchByDrzavaOznaka(drzava.getOznaka());
    }
}
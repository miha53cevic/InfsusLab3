package hr.fer.oap.dao.repository;

import hr.fer.oap.domain.Korisnik;
import hr.fer.oap.domain.Oglas;
import hr.fer.oap.mapping.OglasTestInstances;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OglasRepositoryTest {

    @Mock
    private OglasRepository oglasRepository;
    private Korisnik korisnik;

    @Test
    void testFindBySifraKorisnik() {
        korisnik = new Korisnik("john.doe@example.com", "John", "password123", (byte) 0);
        Oglas oglas1 = OglasTestInstances.createOglas1();
        Oglas oglas2 = OglasTestInstances.createOglas2();
        List<Oglas> oglasi = Arrays.asList(oglas1, oglas2);

        when(oglasRepository.findBySifraKorisnik(korisnik.getId())).thenReturn(oglasi);

        List<Oglas> result = oglasRepository.findBySifraKorisnik(korisnik.getId());

        assertEquals(oglasi, result);

        verify(oglasRepository, times(1)).findBySifraKorisnik(korisnik.getId());
    }
}
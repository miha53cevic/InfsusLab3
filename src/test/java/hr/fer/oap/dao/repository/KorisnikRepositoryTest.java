package hr.fer.oap.dao.repository;

import hr.fer.oap.domain.Korisnik;
import hr.fer.oap.service.KategorijaService;
import hr.fer.oap.service.impl.KategorijaServiceJpa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class KorisnikRepositoryTest {

    @Mock
    private KorisnikRepository korisnikRepository;
    private Korisnik korisnik;

    @BeforeEach
    void setUp() {
        korisnik = new Korisnik("john.doe@example.com", "John", "password123", (byte) 0);
    }

    @Test
    void testFindByIme() {
        when(korisnikRepository.findByIme("John")).thenReturn(Optional.of(korisnik));
        Optional<Korisnik> foundKorisnik = korisnikRepository.findByIme("John");
        assertTrue(foundKorisnik.isPresent());
        assertEquals("John", foundKorisnik.get().getIme());
        verify(korisnikRepository, times(1)).findByIme("John");
    }

    @Test
    void testFindByIme_NotFound() {
        when(korisnikRepository.findByIme("NonExistentName")).thenReturn(Optional.empty());
        Optional<Korisnik> foundKorisnik = korisnikRepository.findByIme("NonExistentName");
        assertFalse(foundKorisnik.isPresent());
        verify(korisnikRepository, times(1)).findByIme("NonExistentName");
    }
}
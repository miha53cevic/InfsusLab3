package hr.fer.oap.service;

import hr.fer.oap.dao.repository.KategorijaRepository;
import hr.fer.oap.dao.repository.KorisnikRepository;
import hr.fer.oap.dao.repository.PripadaKategorijiRepository;
import hr.fer.oap.domain.Korisnik;
import hr.fer.oap.service.impl.KorisnikServiceJpa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class KorisnikServiceTest {
    private KorisnikService service;
    private KorisnikRepository korisnikRepository;


    @BeforeEach
    void setUp() {
        korisnikRepository = mock(KorisnikRepository.class);
        service = new KorisnikServiceJpa(korisnikRepository);
    }

    @Test
    void fetchByUsername() {
        Korisnik korisnik = new Korisnik("john.doe@example.com", "John", "password123", (byte) 0);
        when(korisnikRepository.findByIme(korisnik.getIme())).thenReturn(Optional.of(korisnik));

        var result = service.fetchByUsername(korisnik.getIme());
        assertEquals(Optional.of(korisnik), result);

        verify(korisnikRepository, times(1)).findByIme(korisnik.getIme());
    }

    @Test
    void createKorisnik() {
        Korisnik korisnik = new Korisnik("john.doe@example.com", "John", "password123", (byte) 0);
        when(korisnikRepository.save(korisnik)).thenReturn(korisnik);

        var result = service.createKorisnik(korisnik);
        assertEquals(korisnik, result);

        verify(korisnikRepository, times(1)).save(korisnik);
    }
}
package hr.fer.oap.service.impl;

import hr.fer.oap.dao.repository.KategorijaRepository;
import hr.fer.oap.dao.repository.KorisnikRepository;
import hr.fer.oap.dao.repository.PripadaKategorijiRepository;
import hr.fer.oap.domain.Korisnik;
import hr.fer.oap.service.KategorijaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class KorisnikDetailsTest {
    private KorisnikDetails service;
    private KorisnikRepository korisnikRepository;
    private UserDetails userDetails;

    @BeforeEach
    void setUp() {
        userDetails = mock(UserDetails.class);
        korisnikRepository = mock(KorisnikRepository.class);
        service = new KorisnikDetails(korisnikRepository);
    }

    @Test
    void KorisnikDetailsTest_LoadUserByUsername() {
        Korisnik korisnik = new Korisnik("john.doe@example.com", "John", "password123", (byte) 0);

        when(korisnikRepository.findByIme("John")).thenReturn(Optional.of(korisnik));
        when(korisnikRepository.findByIme("random")).thenThrow(new UsernameNotFoundException("Unknown user random"));

        UserDetails expectedUserDetails = User.withUsername("John")
                .password("password123")
                .authorities(Collections.emptyList())
                .build();

        var result = service.loadUserByUsername("John");
        assertEquals(expectedUserDetails, result);

        assertThrows(UsernameNotFoundException.class, () -> service.loadUserByUsername("random"));
    }
}
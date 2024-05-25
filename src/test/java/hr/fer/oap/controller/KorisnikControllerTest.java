package hr.fer.oap.controller;

import hr.fer.oap.domain.Korisnik;
import hr.fer.oap.service.KorisnikService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class KorisnikControllerTest {
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private KorisnikService korisnikService;
    private KorisnikController controller;

    @BeforeEach
    void setUp() {
        korisnikService = mock(KorisnikService.class);
        controller = new KorisnikController(korisnikService, passwordEncoder);
    }

    @Test
    void KorisnikControllerTest_Korisnik() {
        String viewName = controller.korisnik();
        assertEquals("Stvoreni novi korisnici: admin, ivan, pajdo (password=password za sve)", viewName);

        verify(korisnikService, times(3)).createKorisnik(any(Korisnik.class));
    }
}
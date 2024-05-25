package hr.fer.oap.controller;

import hr.fer.oap.domain.Kategorija;
import hr.fer.oap.service.KategorijaService;
import hr.fer.oap.service.KorisnikService;
import hr.fer.oap.service.OglasService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class OglasnikControllerTest {
    private OglasnikController controller;
    private OglasService oglasService;
    private KategorijaService kategorijaService;
    private Model model;
    private UserDetails userDetails;

    @BeforeEach
    void setUp() {
        oglasService = mock(OglasService.class);
        kategorijaService = mock(KategorijaService.class);
        model = mock(Model.class);
        userDetails = mock(UserDetails.class);
        controller = new OglasnikController(oglasService, kategorijaService);
    }

    @Test
    void OglasnikControllerTest_Index() {
        String viewName = controller.index(userDetails, model);
        assertEquals("oglasnik", viewName);

        verify(oglasService, times(1)).fetchAll();
        verify(model, times(5)).addAttribute(any(String.class), any());
    }

    @Test
    void OglasnikControllerTest_DohvatiOglaseForm() {
        Kategorija kategorija1 = new Kategorija("Automobili");
        when(kategorijaService.findById(1L)).thenReturn(Optional.of(kategorija1));

        String viewName = controller.dohvatiOglaseForm(userDetails, model, null, 1L, null);
        assertEquals("oglasnik", viewName);

        verify(oglasService, times(1)).fetchAll();
        verify(kategorijaService, times(1)).findById(1L);
        verify(oglasService, times(1)).findAllByKategorija(any(Kategorija.class));
        verify(kategorijaService, times(1)).fetchAll();
        verify(model, times(8)).addAttribute(any(String.class), any());
    }

    @Test
    void OglasnikControllerTest_Login() {
        String viewName = controller.login(model);
        assertEquals("login", viewName);
    }
}
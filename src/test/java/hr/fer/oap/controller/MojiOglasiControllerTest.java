package hr.fer.oap.controller;

import hr.fer.oap.controller.MojiOglasiController;
import hr.fer.oap.domain.Kategorija;
import hr.fer.oap.mapping.MappingToFilteredOglasi;
import hr.fer.oap.service.KategorijaService;
import hr.fer.oap.service.KorisnikService;
import hr.fer.oap.service.OglasService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MojiOglasiControllerTest {

    private MojiOglasiController controller;
    private OglasService oglasService;
    private KorisnikService korisnikService;
    private KategorijaService kategorijaService;
    private Model model;
    private UserDetails userDetails;

    @BeforeEach
    void setUp() {
        oglasService = mock(OglasService.class);
        korisnikService = mock(KorisnikService.class);
        kategorijaService = mock(KategorijaService.class);
        model = mock(Model.class);
        userDetails = mock(UserDetails.class);
        controller = new MojiOglasiController(oglasService, korisnikService, kategorijaService);
    }

    @Test
    void MojiOglasiControllerTest_Index() {
        Kategorija kategorija1 = new Kategorija("Automobili");

        when(oglasService.fetchAll()).thenReturn(new ArrayList<>());
        when(kategorijaService.findById(any(Long.class))).thenReturn(Optional.of(kategorija1));
        when(oglasService.findAllByKategorija(any(Kategorija.class))).thenReturn(new ArrayList<>());

        String viewName = controller.index(userDetails, model, "searchInput", 1L, "istekli");
        assertEquals("mojiOglasi", viewName);

        verify(oglasService, times(1)).fetchAll();
        verify(kategorijaService, times(1)).findById(any(Long.class));
        verify(oglasService, times(1)).findAllByKategorija(any(Kategorija.class));
        verify(kategorijaService, times(1)).fetchAll();
        verify(model, times(8)).addAttribute(any(String.class), any());
    }
}
package hr.fer.oap.controller;

import hr.fer.oap.dao.dto.CreateOglasDTO;
import hr.fer.oap.dao.dto.EditOglasDTO;
import hr.fer.oap.domain.Kategorija;
import hr.fer.oap.domain.Korisnik;
import hr.fer.oap.domain.Oglas;
import hr.fer.oap.mapping.OglasTestInstances;
import hr.fer.oap.service.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class OglasControllerTest {
    private OglasController controller;
    private MjestoService mjestoService;
    private DrzavaService drzavaService;
    private OglasService oglasService;
    private KorisnikService korisnikService;
    private KategorijaService kategorijaService;
    private PripadaKategorijiService pripadaKategorijiService;
    private Model model;
    private UserDetails userDetails;
    private BindingResult bindingResult;

    @BeforeEach
    void setUp() {
        mjestoService = mock(MjestoService.class);
        drzavaService = mock(DrzavaService.class);
        oglasService = mock(OglasService.class);
        korisnikService = mock(KorisnikService.class);
        kategorijaService = mock(KategorijaService.class);
        pripadaKategorijiService = mock(PripadaKategorijiService.class);
        bindingResult = mock(BindingResult.class);
        model = mock(Model.class);
        userDetails = mock(UserDetails.class);
        controller = new OglasController(mjestoService, drzavaService, oglasService, korisnikService, kategorijaService, pripadaKategorijiService);
    }

    @Test
    void OglasControllerTest_Index() {
        Oglas oglas = OglasTestInstances.createOglas1();
        when(oglasService.fetchById(any(Long.class))).thenReturn(Optional.of(oglas));

        String viewName = controller.index(userDetails, any(Long.class), model);
        assertEquals("oglas/prikaz", viewName);

        verify(oglasService, times(1)).fetchById(any(Long.class));
        verify(model, times(6)).addAttribute(any(String.class), any());
    }

    @Test
    void OglasControllerTest_EditForm() {
        Oglas oglas = OglasTestInstances.createOglas1();
        when(oglasService.fetchById(any(Long.class))).thenReturn(Optional.of(oglas));

        String viewName = controller.editForm(userDetails, model, null, any(Long.class));
        assertEquals("oglas/edit", viewName);

        verify(oglasService, times(1)).fetchById(any(Long.class));
        verify(kategorijaService, times(1)).findAllByOglas(any(Oglas.class));
        verify(model, times(7)).addAttribute(any(String.class), any());
    }

    @Test
    void OglasControllerTest_Edit() {
        Oglas oglas = OglasTestInstances.createOglas1();
        EditOglasDTO dto = new EditOglasDTO(
                oglas.getId(), oglas.getNaziv(), oglas.getOpis(), oglas.getPocetnaCijena(), oglas.getPocetnoVrijeme(),
                oglas.getZavrsnoVrijeme(), oglas.getMjesto().getId()
        );

        String viewName = controller.edit(dto, userDetails);
        assertEquals("redirect:/moji-oglasi", viewName);

        verify(oglasService, times(1)).editOglas(dto);
    }

    @Test
    void OglasControllerTest_StvoriOglasForm() {

        String viewName = controller.stvoriOglasForm(userDetails, model, null, null);
        assertEquals("oglas/stvori", viewName);

        verify(model, times(6)).addAttribute(any(String.class), any());
    }

    @Test
    void OglasControllerTest_StvoriOglas() {
        Kategorija kategorija1 = new Kategorija("Automobili");
        List<Long> kategorije = new ArrayList<>();
        kategorije.add(kategorija1.getId());

        Korisnik korisnik = new Korisnik("john.doe@example.com", "John", "password123", (byte) 0);

        Oglas oglas = OglasTestInstances.createOglas1();
        CreateOglasDTO dto = new CreateOglasDTO(oglas.getNaziv(), oglas.getOpis(), oglas.getPocetnaCijena(),
                oglas.getPocetnoVrijeme(), oglas.getZavrsnoVrijeme(), oglas.getMjesto().getId(), kategorije
        );

        when(userDetails.getUsername()).thenReturn("John");
        when(korisnikService.fetchByUsername(korisnik.getIme())).thenReturn(Optional.of(korisnik));
        when(oglasService.createOglas(dto, korisnik)).thenReturn(oglas);
        when(kategorijaService.findById(kategorija1.getId())).thenReturn(Optional.of(kategorija1));

        String viewName = controller.stvoriOglas(dto, bindingResult, model, userDetails);
        assertEquals("redirect:/oglas/" + oglas.getId(), viewName);

        verify(korisnikService, times(1)).fetchByUsername("John");
        verify(oglasService, times(1)).createOglas(dto, korisnik);
        verify(kategorijaService, times(1)).findById(kategorija1.getId());
        verify(pripadaKategorijiService, times(1)).addKategorijaToOglas(any(Kategorija.class), any(Oglas.class));
    }

    @Test
    void OglasControllerTest_Delete() {
        Oglas oglas = OglasTestInstances.createOglas1();
        oglas.setId(1L);

        String viewName = controller.delete(userDetails, oglas.getId());
        assertEquals("redirect:/moji-oglasi", viewName);

        verify(oglasService, times(1)).deleteOglas(any(Long.class));
    }
}
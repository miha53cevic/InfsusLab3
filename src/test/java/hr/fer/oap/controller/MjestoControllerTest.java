package hr.fer.oap.controller;

import hr.fer.oap.dao.dto.CreateEditMjestoDTO;
import hr.fer.oap.domain.Drzava;
import hr.fer.oap.domain.Mjesto;
import hr.fer.oap.service.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class MjestoControllerTest {
    private MjestoController controller;
    private MjestoService mjestoService;
    private DrzavaService drzavaService;
    private PripadaKategorijiService pripadaKategorijiService;
    private Model model;
    private UserDetails userDetails;

    @BeforeEach
    void setUp() {
        mjestoService = mock(MjestoService.class);
        drzavaService = mock(DrzavaService.class);
        model = mock(Model.class);
        userDetails = mock(UserDetails.class);
        controller = new MjestoController(mjestoService, drzavaService);
    }

    @Test
    void index() {
        assertEquals("mjesto/crud", controller.index(model, userDetails, 0, 200, null, null));
        verify(mjestoService, times(1)).fetchAll();
        verify(model, times(6)).addAttribute(any(String.class), any());
    }

    @Test
    void delete() {
        Drzava drzava = new Drzava("HR", "Croatia");
        Mjesto mjesto = new Mjesto("Zagreb", drzava);
        mjesto.setId(5L);

        assertEquals("redirect:/mjesto/crud", controller.delete(userDetails, mjesto.getId()));

        verify(mjestoService, times(1)).deleteById(anyLong());
    }

    @Test
    void addForm() {
        Drzava drzava = new Drzava("HR", "Croatia");
        Mjesto mjesto = new Mjesto("Zagreb", drzava);
        mjesto.setId(5L);

        CreateEditMjestoDTO dto = new CreateEditMjestoDTO(mjesto.getNaziv(), drzava.getOznaka());

        assertEquals("mjesto/add", controller.addForm(userDetails, model, dto));
        verify(drzavaService, times(1)).fetchAll();
        verify(model, times(3)).addAttribute(any(String.class), any());
    }

    @Test
    void add() {
        Drzava drzava = new Drzava("HR", "Croatia");
        Mjesto mjesto = new Mjesto("Zagreb", drzava);
        mjesto.setId(5L);

        CreateEditMjestoDTO dto = new CreateEditMjestoDTO(mjesto.getNaziv(), drzava.getOznaka());

        assertEquals("redirect:/mjesto/crud", controller.add(userDetails, dto));

        verify(mjestoService, times(1)).createMjesto(dto);
    }

    @Test
    void edit() {
        Drzava drzava = new Drzava("HR", "Croatia");
        Mjesto mjesto = new Mjesto("Zagreb", drzava);
        mjesto.setId(5L);

        when(mjestoService.fetchById(mjesto.getId())).thenReturn(Optional.of(mjesto));

        assertEquals("mjesto/edit", controller.edit(userDetails, mjesto.getId(), model));

        verify(model, times(3)).addAttribute(any(String.class), any());
    }

    @Test
    void testAdd() {
        Drzava drzava = new Drzava("HR", "Croatia");
        Mjesto mjesto = new Mjesto("Zagreb", drzava);
        mjesto.setId(5L);

        assertEquals("redirect:/mjesto/crud", controller.add(userDetails, mjesto.getId(), mjesto));

        verify(mjestoService, times(1)).editMjesto(mjesto);
    }
}
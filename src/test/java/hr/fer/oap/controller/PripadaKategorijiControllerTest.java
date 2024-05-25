package hr.fer.oap.controller;

import hr.fer.oap.dao.dto.CreateDeletePripadaKategorijiDTO;
import hr.fer.oap.dao.dto.EditPripadaKategorijiDTO;
import hr.fer.oap.domain.Kategorija;
import hr.fer.oap.domain.Oglas;
import hr.fer.oap.domain.Pripadakategoriji;
import hr.fer.oap.mapping.OglasTestInstances;
import hr.fer.oap.service.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PripadaKategorijiControllerTest {
    private PripadaKategorijiController controller;
    private PripadaKategorijiService pripadaKategorijiService;
    private UserDetails userDetails;

    @BeforeEach
    void setUp() {
        userDetails = mock(UserDetails.class);
        pripadaKategorijiService = mock(PripadaKategorijiService.class);
        controller = new PripadaKategorijiController(pripadaKategorijiService);
    }

    @Test
    void PripadaKategorijiControllerTest_Add() {
        Oglas oglas = OglasTestInstances.createOglas1();
        Kategorija kategorija1 = new Kategorija("Automobili");
        CreateDeletePripadaKategorijiDTO dto = new CreateDeletePripadaKategorijiDTO(oglas.getId(), kategorija1.getId());
        Pripadakategoriji pripadakategoriji = new Pripadakategoriji(oglas, kategorija1);

        when(pripadaKategorijiService.create(dto)).thenReturn(pripadakategoriji);

        String viewName = controller.add(dto, userDetails);
        assertEquals(pripadakategoriji.toString(), viewName);

        verify(pripadaKategorijiService, times(1)).create(dto);
    }

    @Test
    void PripadaKategorijiControllerTest_Edit() {
        Oglas oglas = OglasTestInstances.createOglas1();
        Kategorija kategorija1 = new Kategorija("Automobili");
        Kategorija kategorija2 = new Kategorija("Dječje igračke");
        EditPripadaKategorijiDTO dto = new EditPripadaKategorijiDTO(oglas.getId(), kategorija1.getId(), kategorija2.getId());
        Pripadakategoriji pripadakategoriji = new Pripadakategoriji(oglas, kategorija1);

        when(pripadaKategorijiService.edit(dto)).thenReturn(pripadakategoriji);

        String viewName = controller.edit(dto, userDetails);
        assertEquals(pripadakategoriji.toString(), viewName);

        verify(pripadaKategorijiService, times(1)).edit(dto);
    }

    @Test
    void PripadaKategorijiControllerTest_Delete() {
        Oglas oglas = OglasTestInstances.createOglas1();
        Kategorija kategorija1 = new Kategorija("Automobili");
        CreateDeletePripadaKategorijiDTO dto = new CreateDeletePripadaKategorijiDTO(oglas.getId(), kategorija1.getId());

        String viewName = controller.delete(dto, userDetails);
        assertEquals("OK", viewName);

        verify(pripadaKategorijiService, times(1)).delete(dto);
    }
}
package hr.fer.oap.service;

import hr.fer.oap.dao.dto.CreateDeletePripadaKategorijiDTO;
import hr.fer.oap.dao.dto.EditOglasDTO;
import hr.fer.oap.dao.dto.EditPripadaKategorijiDTO;
import hr.fer.oap.dao.repository.KategorijaRepository;
import hr.fer.oap.dao.repository.MjestoRepository;
import hr.fer.oap.dao.repository.OglasRepository;
import hr.fer.oap.dao.repository.PripadaKategorijiRepository;
import hr.fer.oap.domain.Kategorija;
import hr.fer.oap.domain.Oglas;
import hr.fer.oap.domain.Pripadakategoriji;
import hr.fer.oap.domain.PripadakategorijiId;
import hr.fer.oap.mapping.OglasTestInstances;
import hr.fer.oap.service.impl.MjestoServiceJpa;
import hr.fer.oap.service.impl.PripadaKategorijiServiceJpa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class PripadaKategorijiServiceTest {
    private PripadaKategorijiService service;
    private PripadaKategorijiRepository pripadaKategorijiRepository;
    private OglasRepository oglasRepository;
    private KategorijaRepository kategorijaRepository;

    @BeforeEach
    void PripadaKategorijiServiceTest_SetUp() {
        pripadaKategorijiRepository = mock(PripadaKategorijiRepository.class);
        oglasRepository = mock(OglasRepository.class);
        kategorijaRepository = mock(KategorijaRepository.class);
        service = new PripadaKategorijiServiceJpa(pripadaKategorijiRepository, oglasRepository, kategorijaRepository);
    }

    @Test
    void PripadaKategorijiServiceTest_AddKategorijaToOglas() {
        Kategorija kategorija1 = new Kategorija("Automobili");
        Oglas oglas = OglasTestInstances.createOglas1();
        Pripadakategoriji pripadakategoriji = new Pripadakategoriji(oglas, kategorija1);

        when(pripadaKategorijiRepository.save(any(Pripadakategoriji.class))).thenReturn(pripadakategoriji);

        var result = service.addKategorijaToOglas(kategorija1, oglas);
        assertEquals(pripadakategoriji, result);

        verify(pripadaKategorijiRepository, times(1)).save(any(Pripadakategoriji.class));
    }

    @Test
    void PripadaKategorijiServiceTest_Delete() {
        Oglas oglas = OglasTestInstances.createOglas1();
        Kategorija kategorija1 = new Kategorija("Automobili");
        CreateDeletePripadaKategorijiDTO dto = new CreateDeletePripadaKategorijiDTO(oglas.getId(), kategorija1.getId());
        Pripadakategoriji pripadakategoriji = new Pripadakategoriji(oglas, kategorija1);

        when(pripadaKategorijiRepository.findById(any(PripadakategorijiId.class))).thenReturn(Optional.of(pripadakategoriji));

        service.delete(dto);

        verify(pripadaKategorijiRepository, times(1)).findById(any(PripadakategorijiId.class));
        verify(pripadaKategorijiRepository, times(1)).delete(any(Pripadakategoriji.class));
    }

    @Test
    void PripadaKategorijiServiceTest_CreateExistent() {
        Oglas oglas = OglasTestInstances.createOglas1();
        Kategorija kategorija1 = new Kategorija("Automobili");
        CreateDeletePripadaKategorijiDTO dto = new CreateDeletePripadaKategorijiDTO(oglas.getId(), kategorija1.getId());
        Pripadakategoriji pripadakategoriji = new Pripadakategoriji(oglas, kategorija1);

        when(oglasRepository.findById(oglas.getId())).thenReturn(Optional.of(oglas));
        when(kategorijaRepository.findById(kategorija1.getId())).thenReturn(Optional.of(kategorija1));
        when(pripadaKategorijiRepository.findById(any(PripadakategorijiId.class))).thenReturn(Optional.of(pripadakategoriji));

        Error error = assertThrows(Error.class, () -> service.create(dto));
        assertEquals("Kategorija vec postoji na tom oglasu!", error.getMessage());

        verify(oglasRepository, times(1)).findById(oglas.getId());
        verify(kategorijaRepository, times(1)).findById(kategorija1.getId());
        verify(pripadaKategorijiRepository, times(1)).findById(any(PripadakategorijiId.class));
    }

    @Test
    void PripadaKategorijiServiceTest_Create() {
        Oglas oglas = OglasTestInstances.createOglas1();
        Kategorija kategorija1 = new Kategorija("Automobili");
        CreateDeletePripadaKategorijiDTO dto = new CreateDeletePripadaKategorijiDTO(oglas.getId(), kategorija1.getId());
        Pripadakategoriji pripadakategoriji = new Pripadakategoriji(oglas, kategorija1);

        when(oglasRepository.findById(oglas.getId())).thenReturn(Optional.of(oglas));
        when(kategorijaRepository.findById(kategorija1.getId())).thenReturn(Optional.of(kategorija1));
        when(pripadaKategorijiRepository.findById(any(PripadakategorijiId.class))).thenReturn(Optional.empty());
        when(pripadaKategorijiRepository.save(any(Pripadakategoriji.class))).thenReturn(pripadakategoriji);

        var result = service.create(dto);
        assertEquals(pripadakategoriji, result);

        verify(oglasRepository, times(1)).findById(oglas.getId());
        verify(kategorijaRepository, times(1)).findById(kategorija1.getId());
        verify(pripadaKategorijiRepository, times(1)).findById(any(PripadakategorijiId.class));
        verify(pripadaKategorijiRepository, times(1)).save(any(Pripadakategoriji.class));
    }

    @Test
    void PripadaKategorijiServiceTest_Edit() {
        Oglas oglas = OglasTestInstances.createOglas1();
        Kategorija kategorija1 = new Kategorija("Automobili");
        Kategorija kategorija2 = new Kategorija("Dječje igračke");
        kategorija1.setId(1L);
        kategorija2.setId(2L);
        EditPripadaKategorijiDTO dto = new EditPripadaKategorijiDTO(oglas.getId(), kategorija1.getId(), kategorija2.getId());
        Pripadakategoriji pripadakategoriji1 = new Pripadakategoriji(oglas, kategorija1);
        Pripadakategoriji pripadakategoriji2 = new Pripadakategoriji(oglas, kategorija2);

        when(oglasRepository.findById(oglas.getId())).thenReturn(Optional.of(oglas));
        when(kategorijaRepository.findById(kategorija1.getId())).thenReturn(Optional.of(kategorija1));
        when(pripadaKategorijiRepository.findById(pripadakategoriji1.getId())).thenReturn(Optional.empty());
        when(pripadaKategorijiRepository.save(any(Pripadakategoriji.class))).thenReturn(pripadakategoriji1);

        when(pripadaKategorijiRepository.findById(pripadakategoriji2.getId())).thenReturn(Optional.of(pripadakategoriji2));

        var result = service.edit(dto);
        assertEquals(pripadakategoriji1, result);

        verify(oglasRepository, times(1)).findById(oglas.getId());
        verify(kategorijaRepository, times(1)).findById(kategorija1.getId());
        verify(pripadaKategorijiRepository, times(2)).findById(any(PripadakategorijiId.class));
        verify(pripadaKategorijiRepository, times(1)).save(any(Pripadakategoriji.class));
        verify(pripadaKategorijiRepository, times(1)).delete(any(Pripadakategoriji.class));
    }
}
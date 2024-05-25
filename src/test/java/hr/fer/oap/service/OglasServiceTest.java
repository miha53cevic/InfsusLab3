package hr.fer.oap.service;

import hr.fer.oap.dao.dto.CreateOglasDTO;
import hr.fer.oap.dao.dto.EditOglasDTO;
import hr.fer.oap.dao.repository.KategorijaRepository;
import hr.fer.oap.dao.repository.MjestoRepository;
import hr.fer.oap.dao.repository.OglasRepository;
import hr.fer.oap.dao.repository.PripadaKategorijiRepository;
import hr.fer.oap.domain.*;
import hr.fer.oap.mapping.OglasTestInstances;
import hr.fer.oap.service.impl.KategorijaServiceJpa;
import hr.fer.oap.service.impl.OglasServiceJpa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class OglasServiceTest {
    private OglasService service;
    private OglasRepository oglasRepository;
    private MjestoRepository mjestoRepository;
    private PripadaKategorijiRepository pripadaKategorijiRepository;


    @BeforeEach
    void OglasServiceTest_SetUp() {
        oglasRepository = mock(OglasRepository.class);
        mjestoRepository = mock(MjestoRepository.class);
        pripadaKategorijiRepository = mock(PripadaKategorijiRepository.class);
        service = new OglasServiceJpa(oglasRepository, mjestoRepository, pripadaKategorijiRepository);
    }

    @Test
    void OglasServiceTest_FetchAll() {
        Oglas oglas1 = OglasTestInstances.createOglas1();
        Oglas oglas2 = OglasTestInstances.createOglas2();
        List<Oglas> oglasi = new ArrayList<>();
        oglasi.add(oglas1);
        oglasi.add(oglas2);

        when(oglasRepository.findAll()).thenReturn(oglasi);

        var result = service.fetchAll();
        assertEquals(oglasi, result);

        verify(oglasRepository, times(1)).findAll();
    }

    @Test
    void OglasServiceTest_FetchById() {
        Oglas oglas1 = OglasTestInstances.createOglas1();

        when(oglasRepository.findById(oglas1.getId())).thenReturn(Optional.of(oglas1));

        var result = service.fetchById(oglas1.getId());
        assertEquals(Optional.of(oglas1), result);

        verify(oglasRepository, times(1)).findById(oglas1.getId());
    }

    @Test
    void OglasServiceTest_FetchByKorisnikId() {
        Oglas oglas1 = OglasTestInstances.createOglas1();
        Oglas oglas2 = OglasTestInstances.createOglas2();
        List<Oglas> oglasi = new ArrayList<>();
        oglasi.add(oglas1);
        oglasi.add(oglas2);

        when(oglasRepository.findBySifraKorisnik(oglas1.getKorisnik().getId())).thenReturn(oglasi);

        var result = service.fetchByKorisnikId(oglas1.getKorisnik().getId());
        assertEquals(oglasi, result);

        verify(oglasRepository, times(1)).findBySifraKorisnik(oglas1.getKorisnik().getId());
    }

    @Test
    void OglasServiceTest_CreateOglas() {
        Kategorija kategorija1 = new Kategorija("Automobili");
        List<Long> kategorije = new ArrayList<>();
        kategorije.add(kategorija1.getId());

        Oglas oglas = OglasTestInstances.createOglas1();
        CreateOglasDTO dto = new CreateOglasDTO(oglas.getNaziv(), oglas.getOpis(), oglas.getPocetnaCijena(),
                oglas.getPocetnoVrijeme(), oglas.getZavrsnoVrijeme(), oglas.getMjesto().getId(), kategorije
        );

        when(mjestoRepository.findById(dto.mjesto())).thenReturn(Optional.of(oglas.getMjesto()));
        when(oglasRepository.save(any(Oglas.class))).thenReturn(oglas);

        var result = service.createOglas(dto, oglas.getKorisnik());
        assertEquals(oglas, result);

        verify(oglasRepository, times(1)).save(any(Oglas.class));
        verify(mjestoRepository, times(1)).findById(dto.mjesto());
    }

    @Test
    void OglasServiceTest_FindAllByKategorija() {
        Kategorija kategorija1 = new Kategorija("Automobili");
        Oglas oglas = OglasTestInstances.createOglas1();
        Pripadakategoriji pripadakategoriji = new Pripadakategoriji(oglas, kategorija1);

        when(pripadaKategorijiRepository.findAllByKategorija(kategorija1)).thenReturn(List.of(pripadakategoriji));

        var result = service.findAllByKategorija(kategorija1);
        assertEquals(List.of(pripadakategoriji).stream().map(Pripadakategoriji::getOglas).toList(), result);

        verify(pripadaKategorijiRepository, times(1)).findAllByKategorija(kategorija1);
    }

    @Test
    void OglasServiceTest_EditOglas() {
        Oglas oglas = OglasTestInstances.createOglas1();
        EditOglasDTO dto = new EditOglasDTO(
                oglas.getId(), oglas.getNaziv(), oglas.getOpis(), oglas.getPocetnaCijena(), oglas.getPocetnoVrijeme(),
                oglas.getZavrsnoVrijeme(), oglas.getMjesto().getId()
        );

        when(mjestoRepository.findById(dto.mjesto())).thenReturn(Optional.ofNullable(oglas.getMjesto()));
        when(oglasRepository.findById(dto.oglasId())).thenReturn(Optional.of(oglas));
        when(oglasRepository.save(oglas)).thenReturn(oglas);

        var result = service.editOglas(dto);
        assertEquals(oglas, result);

        verify(mjestoRepository, times(1)).findById(dto.mjesto());
        verify(oglasRepository, times(1)).findById(dto.oglasId());
        verify(oglasRepository, times(1)).save(oglas);
    }
}
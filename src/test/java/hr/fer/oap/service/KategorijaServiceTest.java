package hr.fer.oap.service;

import hr.fer.oap.dao.repository.KategorijaRepository;
import hr.fer.oap.dao.repository.PripadaKategorijiRepository;
import hr.fer.oap.domain.Kategorija;
import hr.fer.oap.domain.Oglas;
import hr.fer.oap.domain.Pripadakategoriji;
import hr.fer.oap.mapping.OglasTestInstances;
import hr.fer.oap.service.impl.KategorijaServiceJpa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class KategorijaServiceTest {
    private KategorijaService service;
    private KategorijaRepository kategorijaRepository;
    private PripadaKategorijiRepository pripadaKategorijiRepository;


    @BeforeEach
    void setUp() {
        kategorijaRepository = mock(KategorijaRepository.class);
        pripadaKategorijiRepository = mock(PripadaKategorijiRepository.class);
        service = new KategorijaServiceJpa(kategorijaRepository, pripadaKategorijiRepository);
    }

    @Test
    void KategorijaServiceTest_FetchAll() {
        Kategorija kategorija1 = new Kategorija("Automobili");
        Kategorija kategorija2 = new Kategorija("Dječje igračke");
        List<Kategorija> kategorije = new ArrayList<>();
        kategorije.add(kategorija1);
        kategorije.add(kategorija2);

        when(kategorijaRepository.findAll()).thenReturn(kategorije);

        var result = service.fetchAll();
        assertEquals(kategorije, result);

        verify(kategorijaRepository, times(1)).findAll();
    }

    @Test
    void KategorijaServiceTest_CreateNewKategorija() {
        Kategorija kategorija1 = new Kategorija("Automobili");
        when(kategorijaRepository.save(kategorija1)).thenReturn(kategorija1);

        var result = service.createNewKategorija(kategorija1);
        assertEquals(kategorija1, result);

        verify(kategorijaRepository, times(1)).save(kategorija1);
    }

    @Test
    void KategorijaServiceTest_FindAllByOglas() {
        Oglas oglas = OglasTestInstances.createOglas1();
        Kategorija kategorija1 = new Kategorija("Automobili");
        Kategorija kategorija2 = new Kategorija("Dječje igračke");
        List<Pripadakategoriji> pripadakategorijiList = new ArrayList<>();
        Pripadakategoriji pripadakategoriji1 = new Pripadakategoriji(oglas, kategorija1);
        Pripadakategoriji pripadakategoriji2 = new Pripadakategoriji(oglas, kategorija2);
        pripadakategorijiList.add(pripadakategoriji1);
        pripadakategorijiList.add(pripadakategoriji2);

        when(pripadaKategorijiRepository.findAllByOglas(oglas)).thenReturn(pripadakategorijiList);

        var result = service.findAllByOglas(oglas);
        assertEquals(pripadakategorijiList.stream().map(Pripadakategoriji::getKategorija).toList(), result);

        verify(pripadaKategorijiRepository, times(1)).findAllByOglas(oglas);
    }

    @Test
    void KategorijaServiceTest_FindById() {
        Kategorija kategorija1 = new Kategorija("Automobili");
        when(kategorijaRepository.findById(kategorija1.getId())).thenReturn(Optional.of(kategorija1));

        var result = service.findById(kategorija1.getId());
        assertEquals(Optional.of(kategorija1), result);

        verify(kategorijaRepository, times(1)).findById(kategorija1.getId());
    }
}
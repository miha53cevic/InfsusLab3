package hr.fer.oap.dao.repository;

import hr.fer.oap.domain.Kategorija;
import hr.fer.oap.domain.Oglas;
import hr.fer.oap.domain.Pripadakategoriji;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PripadaKategorijiRepositoryTest {

    @Mock
    private PripadaKategorijiRepository pripadaKategorijiRepository;

    @Test
    void testFindAllByOglas() {
        Oglas oglas = new Oglas();
        Pripadakategoriji pripadakategoriji1 = new Pripadakategoriji(oglas, new Kategorija("Automobili"));
        Pripadakategoriji pripadakategoriji2 = new Pripadakategoriji(oglas, new Kategorija("Dječje igračke"));
        List<Pripadakategoriji> pripadakategorijiList = Arrays.asList(pripadakategoriji1, pripadakategoriji2);

        when(pripadaKategorijiRepository.findAllByOglas(oglas)).thenReturn(pripadakategorijiList);

        List<Pripadakategoriji> result = pripadaKategorijiRepository.findAllByOglas(oglas);

        assertEquals(pripadakategorijiList, result);

        verify(pripadaKategorijiRepository, times(1)).findAllByOglas(oglas);
    }

    @Test
    void testFindAllByKategorija() {
        Kategorija kategorija = new Kategorija("Automobili");
        Pripadakategoriji pripadakategoriji1 = new Pripadakategoriji(new Oglas(), kategorija);
        Pripadakategoriji pripadakategoriji2 = new Pripadakategoriji(new Oglas(), kategorija);
        List<Pripadakategoriji> pripadakategorijiList = Arrays.asList(pripadakategoriji1, pripadakategoriji2);

        when(pripadaKategorijiRepository.findAllByKategorija(kategorija)).thenReturn(pripadakategorijiList);

        List<Pripadakategoriji> result = pripadaKategorijiRepository.findAllByKategorija(kategorija);

        assertEquals(pripadakategorijiList, result);

        verify(pripadaKategorijiRepository, times(1)).findAllByKategorija(kategorija);
    }
}
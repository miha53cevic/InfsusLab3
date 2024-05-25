package hr.fer.oap.mapping;

import hr.fer.oap.domain.Drzava;
import hr.fer.oap.domain.Korisnik;
import hr.fer.oap.domain.Mjesto;
import hr.fer.oap.domain.Oglas;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MappingToFilteredOglasiTest {
    private List<Oglas> oglasiList;

    @BeforeEach
    void setUp() {
        oglasiList = List.of(
                OglasTestInstances.createOglas1(),
                OglasTestInstances.createOglas2(),
                OglasTestInstances.createOglas3(),
                OglasTestInstances.createOglas4()
        );
    }

    @Test
    void testFromOglasiByNaziv_EmptyList() {
        MappingToFilteredOglasi mapping = new MappingToFilteredOglasi();
        List<Oglas> emptyList = new ArrayList<>();
        List<Oglas> result = mapping.fromOglasiByNaziv(emptyList, "Test");
        assertEquals(0, result.size(), "Expected empty result for empty list input");
    }

    @Test
    void testFromOglasiByNaziv_MatchAll() {
        MappingToFilteredOglasi mapping = new MappingToFilteredOglasi();
        List<Oglas> result = mapping.fromOglasiByNaziv(oglasiList, "for");
        assertEquals(4, result.size(), "Expected all items to match 'for'");
    }

    @Test
    void testFromOglasiByNaziv_PartialMatch() {
        MappingToFilteredOglasi mapping = new MappingToFilteredOglasi();
        List<Oglas> result = mapping.fromOglasiByNaziv(oglasiList, "for sale");
        assertEquals(3, result.size(), "Expected one item to match 'for sale'");
    }

    @Test
    void testFromOglasiByNaziv_NoMatch() {
        MappingToFilteredOglasi mapping = new MappingToFilteredOglasi();
        List<Oglas> result = mapping.fromOglasiByNaziv(oglasiList, "Nonexistent");
        assertEquals(0, result.size(), "Expected one item to match 'Nonexistent'");
    }

    @Test
    void testFromOglasiByNaziv_CaseInsensitive() {
        MappingToFilteredOglasi mapping = new MappingToFilteredOglasi();
        List<Oglas> result = mapping.fromOglasiByNaziv(oglasiList, "car fOr SaLe");
        assertEquals(1, result.size(), "Expected one item to match 'car fOr SaLe'");
        assertEquals("Car for sale", result.get(0).getNaziv());
    }

    @Test
    void testFromOglasiByTime_EmptyListForTrue() {
        MappingToFilteredOglasi mapping = new MappingToFilteredOglasi();
        List<Oglas> emptyList = new ArrayList<>();
        List<Oglas> result = mapping.fromOglasiByTime(emptyList, true);
        assertEquals(0, result.size(), "Expected empty result for empty list input");
    }

    @Test
    void testFromOglasiByTime_EmptyListForFalse() {
        MappingToFilteredOglasi mapping = new MappingToFilteredOglasi();
        List<Oglas> emptyList = new ArrayList<>();
        List<Oglas> result = mapping.fromOglasiByTime(emptyList, false);
        assertEquals(0, result.size(), "Expected empty result for empty list input");
    }

    @Test
    void testFromOglasiByTime_TrueGetsExpired() {
        MappingToFilteredOglasi mapping = new MappingToFilteredOglasi();
        List<Oglas> result = mapping.fromOglasiByTime(oglasiList, true);
        assertEquals(1, result.size(), "Expected one item that is expired");
        assertTrue(result.get(0).getZavrsnoVrijeme().isBefore(LocalDateTime.now()));
    }

    @Test
    void testFromOglasiByTime_FalseGetsActive() {
        MappingToFilteredOglasi mapping = new MappingToFilteredOglasi();
        List<Oglas> result = mapping.fromOglasiByTime(oglasiList, false);
        assertEquals(3, result.size(), "Expected three items that are active");
        assertTrue(result.get(0).getZavrsnoVrijeme().isAfter(LocalDateTime.now()));
        assertTrue(result.get(1).getZavrsnoVrijeme().isAfter(LocalDateTime.now()));
        assertTrue(result.get(2).getZavrsnoVrijeme().isAfter(LocalDateTime.now()));
    }
}
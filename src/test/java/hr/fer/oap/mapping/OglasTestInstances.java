package hr.fer.oap.mapping;

import hr.fer.oap.domain.Drzava;
import hr.fer.oap.domain.Korisnik;
import hr.fer.oap.domain.Mjesto;
import hr.fer.oap.domain.Oglas;

import java.time.LocalDateTime;

public class OglasTestInstances {

    public static Oglas createOglas1() {
        LocalDateTime pocetnoVrijeme = LocalDateTime.now().minusDays(5);
        LocalDateTime zavrsnoVrijeme = pocetnoVrijeme.plusDays(10);
        Korisnik korisnik = new Korisnik("john.doe@example.com", "John", "password123", (byte) 0);
        Drzava drzava = new Drzava("US", "United States");
        Mjesto mjesto = new Mjesto("New York", drzava);
        return new Oglas("Car for sale", "Good condition, low mileage", 5000.0f, pocetnoVrijeme, zavrsnoVrijeme, korisnik, mjesto);
    }

    public static Oglas createOglas2() {
        LocalDateTime pocetnoVrijeme = LocalDateTime.now();
        LocalDateTime zavrsnoVrijeme = pocetnoVrijeme.plusDays(5);
        Korisnik korisnik = new Korisnik("jane.smith@example.com", "Jane", "password456", (byte) 0);
        Drzava drzava = new Drzava("UK", "United Kingdom");
        Mjesto mjesto = new Mjesto("London", drzava);
        return new Oglas("Bike for sale", "Brand new, unused", 200.0f, pocetnoVrijeme, zavrsnoVrijeme, korisnik, mjesto);
    }

    public static Oglas createOglas3() {
        LocalDateTime pocetnoVrijeme = LocalDateTime.now().minusDays(10);
        LocalDateTime zavrsnoVrijeme = pocetnoVrijeme.plusDays(4);
        Korisnik korisnik = new Korisnik("alex.brown@example.com", "Alex", "password789", (byte) 0);
        Drzava drzava = new Drzava("DE", "Germany");
        Mjesto mjesto = new Mjesto("Berlin", drzava);
        return new Oglas("Apartment for rent", "Central location, 2 bedrooms", 1200.0f, pocetnoVrijeme, zavrsnoVrijeme, korisnik, mjesto);
    }

    public static Oglas createOglas4() {
        LocalDateTime pocetnoVrijeme = LocalDateTime.now().minusDays(3);
        LocalDateTime zavrsnoVrijeme = pocetnoVrijeme.plusDays(6);
        Korisnik korisnik = new Korisnik("maria.garcia@example.com", "Maria", "password101", (byte) 0);
        Drzava drzava = new Drzava("ES", "Spain");
        Mjesto mjesto = new Mjesto("Madrid", drzava);
        return new Oglas("Furniture for sale", "Used, good condition", 300.0f, pocetnoVrijeme, zavrsnoVrijeme, korisnik, mjesto);
    }
}

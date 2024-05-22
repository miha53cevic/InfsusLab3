package hr.fer.oap.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "oglas", indexes = {
        @Index(name = "sifraKorisnik", columnList = "sifraKorisnik"),
        @Index(name = "sifraMjesto", columnList = "sifraMjesto")
})
public class Oglas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sifraOglas", nullable = false)
    private Long id;

    @Column(name = "naziv", nullable = false, length = 100)
    private String naziv;

    @Column(name = "opis", nullable = false, length = 300)
    private String opis;

    @Column(name = "pocetnaCijena", nullable = false)
    private Float pocetnaCijena;

    @Column(name = "pocetnoVrijeme", nullable = false)
    private LocalDateTime pocetnoVrijeme;

    @Column(name = "zavrsnoVrijeme", nullable = false)
    private LocalDateTime zavrsnoVrijeme;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "sifraKorisnik", nullable = false)
    private Korisnik korisnik;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "sifraMjesto", nullable = false)
    private Mjesto mjesto;

    public Oglas() {

    }

    public Oglas(String naziv, String opis, Float pocetnaCijena, LocalDateTime pocetnoVrijeme, LocalDateTime zavrsnoVrijeme, Korisnik korisnik, Mjesto mjesto) {
        this.naziv = naziv;
        this.opis = opis;
        this.pocetnaCijena = pocetnaCijena;
        this.pocetnoVrijeme = pocetnoVrijeme;
        this.zavrsnoVrijeme = zavrsnoVrijeme;
        this.korisnik = korisnik;
        this.mjesto = mjesto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public Float getPocetnaCijena() {
        return pocetnaCijena;
    }

    public void setPocetnaCijena(Float pocetnaCijena) {
        this.pocetnaCijena = pocetnaCijena;
    }

    public LocalDateTime getPocetnoVrijeme() {
        return pocetnoVrijeme;
    }

    public void setPocetnoVrijeme(LocalDateTime pocetnoVrijeme) {
        this.pocetnoVrijeme = pocetnoVrijeme;
    }

    public LocalDateTime getZavrsnoVrijeme() {
        return zavrsnoVrijeme;
    }

    public void setZavrsnoVrijeme(LocalDateTime zavrsnoVrijeme) {
        this.zavrsnoVrijeme = zavrsnoVrijeme;
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik sifraKorisnik) {
        this.korisnik = sifraKorisnik;
    }

    public Mjesto getMjesto() {
        return mjesto;
    }

    public void setMjesto(Mjesto sifraMjesto) {
        this.mjesto = sifraMjesto;
    }

}
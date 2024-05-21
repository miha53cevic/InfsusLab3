package hr.fer.oap.domain;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "oglas", indexes = {
        @Index(name = "sifraKorisnik", columnList = "sifraKorisnik"),
        @Index(name = "sifraMjesto", columnList = "sifraMjesto")
})
public class Oglas {
    @Id
    @Column(name = "sifraOglas", nullable = false)
    private Long id;

    @Column(name = "naziv", nullable = false, length = 100)
    private String naziv;

    @Column(name = "opis", nullable = false, length = 300)
    private String opis;

    @Column(name = "pocetnaCijena", nullable = false)
    private Float pocetnaCijena;

    @Column(name = "pocetnoVrijeme", nullable = false)
    private Instant pocetnoVrijeme;

    @Column(name = "zavrsnoVrijeme", nullable = false)
    private Instant zavrsnoVrijeme;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "sifraKorisnik", nullable = false)
    private Korisnik sifraKorisnik;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "sifraMjesto", nullable = false)
    private Mjesto sifraMjesto;

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

    public Instant getPocetnoVrijeme() {
        return pocetnoVrijeme;
    }

    public void setPocetnoVrijeme(Instant pocetnoVrijeme) {
        this.pocetnoVrijeme = pocetnoVrijeme;
    }

    public Instant getZavrsnoVrijeme() {
        return zavrsnoVrijeme;
    }

    public void setZavrsnoVrijeme(Instant zavrsnoVrijeme) {
        this.zavrsnoVrijeme = zavrsnoVrijeme;
    }

    public Korisnik getSifraKorisnik() {
        return sifraKorisnik;
    }

    public void setSifraKorisnik(Korisnik sifraKorisnik) {
        this.sifraKorisnik = sifraKorisnik;
    }

    public Mjesto getSifraMjesto() {
        return sifraMjesto;
    }

    public void setSifraMjesto(Mjesto sifraMjesto) {
        this.sifraMjesto = sifraMjesto;
    }

}
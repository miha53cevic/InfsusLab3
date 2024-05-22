package hr.fer.oap.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "pripadakategoriji", indexes = {
        @Index(name = "sifra_kategorija", columnList = "sifra_kategorija")
})
public class Pripadakategoriji {
    @EmbeddedId
    private PripadakategorijiId id;

    @MapsId("sifraOglas")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "sifra_oglas", nullable = false)
    private Oglas oglas;

    public Pripadakategoriji() {
    }

    @MapsId("sifraKategorija")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "sifra_kategorija", nullable = false)
    private Kategorija kategorija;

    public Pripadakategoriji(Oglas sifraOglas, Kategorija sifraKategorija) {
        this.oglas = sifraOglas;
        this.kategorija = sifraKategorija;
        this.id = new PripadakategorijiId(sifraOglas.getId(), sifraKategorija.getId());
    }

    public PripadakategorijiId getId() {
        return id;
    }

    public void setId(PripadakategorijiId id) {
        this.id = id;
    }

    public Oglas getOglas() {
        return oglas;
    }

    public void setOglas(Oglas sifraOglas) {
        this.oglas = sifraOglas;
    }

    public Kategorija getKategorija() {
        return kategorija;
    }

    public void setKategorija(Kategorija sifraKategorija) {
        this.kategorija = sifraKategorija;
    }

}
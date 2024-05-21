package hr.fer.oap.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "pripadakategoriji", indexes = {
        @Index(name = "sifraKategorija", columnList = "sifraKategorija")
})
public class Pripadakategoriji {
    @EmbeddedId
    private PripadakategorijiId id;

    @MapsId("sifraOglas")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "sifraOglas", nullable = false)
    private Oglas oglas;

    @MapsId("sifraKategorija")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "sifraKategorija", nullable = false)
    private Kategorija kategorija;

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
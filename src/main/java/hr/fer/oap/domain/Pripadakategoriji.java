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
    private Oglas sifraOglas;

    @MapsId("sifraKategorija")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "sifraKategorija", nullable = false)
    private Kategorija sifraKategorija;

    public PripadakategorijiId getId() {
        return id;
    }

    public void setId(PripadakategorijiId id) {
        this.id = id;
    }

    public Oglas getSifraOglas() {
        return sifraOglas;
    }

    public void setSifraOglas(Oglas sifraOglas) {
        this.sifraOglas = sifraOglas;
    }

    public Kategorija getSifraKategorija() {
        return sifraKategorija;
    }

    public void setSifraKategorija(Kategorija sifraKategorija) {
        this.sifraKategorija = sifraKategorija;
    }

}
package hr.fer.oap.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "ponuda", indexes = {
        @Index(name = "sifraOglas", columnList = "sifraOglas")
}, uniqueConstraints = {
        @UniqueConstraint(name = "sifraKorisnik", columnNames = {"sifraKorisnik", "sifraOglas"})
})
public class Ponuda {
    @Id
    @Column(name = "sifraPonuda", nullable = false)
    private Long id;

    @Column(name = "trenutniIznos", nullable = false)
    private Float trenutniIznos;

    @Column(name = "povecanje", nullable = false)
    private Float povecanje;

    @Column(name = "maksIznos", nullable = false)
    private Float maksIznos;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "sifraOglas", nullable = false)
    private Oglas sifraOglas;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "sifraKorisnik", nullable = false)
    private Korisnik sifraKorisnik;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getTrenutniIznos() {
        return trenutniIznos;
    }

    public void setTrenutniIznos(Float trenutniIznos) {
        this.trenutniIznos = trenutniIznos;
    }

    public Float getPovecanje() {
        return povecanje;
    }

    public void setPovecanje(Float povecanje) {
        this.povecanje = povecanje;
    }

    public Float getMaksIznos() {
        return maksIznos;
    }

    public void setMaksIznos(Float maksIznos) {
        this.maksIznos = maksIznos;
    }

    public Oglas getSifraOglas() {
        return sifraOglas;
    }

    public void setSifraOglas(Oglas sifraOglas) {
        this.sifraOglas = sifraOglas;
    }

    public Korisnik getSifraKorisnik() {
        return sifraKorisnik;
    }

    public void setSifraKorisnik(Korisnik sifraKorisnik) {
        this.sifraKorisnik = sifraKorisnik;
    }

}
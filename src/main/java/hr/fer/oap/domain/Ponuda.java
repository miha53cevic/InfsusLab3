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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private Oglas oglas;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "sifraKorisnik", nullable = false)
    private Korisnik korisnik;

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

    public Oglas getOglas() {
        return oglas;
    }

    public void setOglas(Oglas sifraOglas) {
        this.oglas = sifraOglas;
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik sifraKorisnik) {
        this.korisnik = sifraKorisnik;
    }

}
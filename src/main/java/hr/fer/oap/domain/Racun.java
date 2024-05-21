package hr.fer.oap.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "racun", indexes = {
        @Index(name = "sifraPonuda", columnList = "sifraPonuda")
})
public class Racun {
    @Id
    @Column(name = "sifraRacun", nullable = false)
    private Long id;

    @Column(name = "iznos", nullable = false)
    private Float iznos;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "sifraPonuda", nullable = false)
    private Ponuda sifraPonuda;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getIznos() {
        return iznos;
    }

    public void setIznos(Float iznos) {
        this.iznos = iznos;
    }

    public Ponuda getSifraPonuda() {
        return sifraPonuda;
    }

    public void setSifraPonuda(Ponuda sifraPonuda) {
        this.sifraPonuda = sifraPonuda;
    }

}
package hr.fer.oap.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "zahtjevpovrataknovca", indexes = {
        @Index(name = "sifraRacun", columnList = "sifraRacun")
})
public class Zahtjevpovrataknovca {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sifraZahtjevPovratakNovca", nullable = false)
    private Long id;

    @Column(name = "prihvacen", nullable = true)
    private Byte prihvacen;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "sifraRacun", nullable = false)
    private Racun sifraRacun;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Byte getPrihvacen() {
        return prihvacen;
    }

    public void setPrihvacen(Byte prihvacen) {
        this.prihvacen = prihvacen;
    }

    public Racun getSifraRacun() {
        return sifraRacun;
    }

    public void setSifraRacun(Racun sifraRacun) {
        this.sifraRacun = sifraRacun;
    }

}
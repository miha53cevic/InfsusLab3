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
    private Racun racun;

    public Zahtjevpovrataknovca() {
    }

    public Zahtjevpovrataknovca(Byte prihvacen, Racun racun) {
        this.prihvacen = prihvacen;
        this.racun = racun;
    }

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

    public Racun getRacun() {
        return racun;
    }

    public void setRacun(Racun sifraRacun) {
        this.racun = sifraRacun;
    }

}
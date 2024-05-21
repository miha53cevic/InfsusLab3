package hr.fer.oap.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "slike", indexes = {
        @Index(name = "sifraOglas", columnList = "sifraOglas")
})
public class Slike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sifraSlike", nullable = false)
    private Long id;

    @Lob
    @Column(name = "poveznica", nullable = false)
    private String poveznica;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "sifraOglas", nullable = false)
    private Oglas oglas;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPoveznica() {
        return poveznica;
    }

    public void setPoveznica(String poveznica) {
        this.poveznica = poveznica;
    }

    public Oglas getOglas() {
        return oglas;
    }

    public void setOglas(Oglas sifraOglas) {
        this.oglas = sifraOglas;
    }

}
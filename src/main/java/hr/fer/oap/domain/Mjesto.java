package hr.fer.oap.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "mjesto", indexes = {
        @Index(name = "oznaka", columnList = "oznaka")
})
public class Mjesto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sifraMjesto", nullable = false)
    private Long id;

    @Column(name = "naziv", nullable = false, length = 100)
    private String naziv;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "oznaka", nullable = false)
    private Drzava oznaka;

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

    public Drzava getOznaka() {
        return oznaka;
    }

    public void setOznaka(Drzava oznaka) {
        this.oznaka = oznaka;
    }

}
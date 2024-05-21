package hr.fer.oap.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "kategorija")
public class Kategorija {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sifraKategorija", nullable = false)
    private Long id;

    @Column(name = "naziv", nullable = false, length = 100)
    private String naziv;

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

}
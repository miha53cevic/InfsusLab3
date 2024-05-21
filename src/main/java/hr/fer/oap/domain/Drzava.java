package hr.fer.oap.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "drzava")
public class Drzava {
    @Id
    @Column(name = "oznaka", nullable = false, length = 10)
    private String oznaka;

    @Column(name = "naziv", nullable = false, length = 50)
    private String naziv;

    public String getOznaka() {
        return oznaka;
    }

    public void setOznaka(String oznaka) {
        this.oznaka = oznaka;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

}
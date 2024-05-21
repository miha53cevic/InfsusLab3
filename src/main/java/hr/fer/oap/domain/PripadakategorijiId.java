package hr.fer.oap.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.util.Objects;

@Embeddable
public class PripadakategorijiId implements java.io.Serializable {
    private static final long serialVersionUID = 3321205406264924740L;
    @Column(name = "sifraOglas", nullable = false)
    private Long sifraOglas;

    @Column(name = "sifraKategorija", nullable = false)
    private Long sifraKategorija;

    public Long getSifraOglas() {
        return sifraOglas;
    }

    public void setSifraOglas(Long sifraOglas) {
        this.sifraOglas = sifraOglas;
    }

    public Long getSifraKategorija() {
        return sifraKategorija;
    }

    public void setSifraKategorija(Long sifraKategorija) {
        this.sifraKategorija = sifraKategorija;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PripadakategorijiId entity = (PripadakategorijiId) o;
        return Objects.equals(this.sifraKategorija, entity.sifraKategorija) &&
                Objects.equals(this.sifraOglas, entity.sifraOglas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sifraKategorija, sifraOglas);
    }

}
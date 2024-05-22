package hr.fer.oap.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import org.hibernate.Hibernate;

import java.util.Objects;

@Embeddable
public class PripadakategorijiId implements java.io.Serializable {
    private static final long serialVersionUID = 3321205406264924740L;
    @NotNull
    @Column(name = "sifra_oglas", nullable = false)
    private Long sifraOglas;

    @NotNull
    @Column(name = "sifra_kategorija", nullable = false)
    private Long sifraKategorija;

    public PripadakategorijiId() {
    }

    public Long getSifraOglas() {
        return sifraOglas;
    }

    public void setSifraOglas(Long sifraOglas) {
        this.sifraOglas = sifraOglas;
    }

    public PripadakategorijiId(Long sifraOglas, Long sifraKategorija) {
        this.sifraOglas = sifraOglas;
        this.sifraKategorija = sifraKategorija;
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
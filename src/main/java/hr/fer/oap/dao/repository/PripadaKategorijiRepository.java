package hr.fer.oap.dao.repository;

import hr.fer.oap.domain.Kategorija;
import hr.fer.oap.domain.Oglas;
import hr.fer.oap.domain.Pripadakategoriji;
import hr.fer.oap.domain.PripadakategorijiId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PripadaKategorijiRepository extends JpaRepository<Pripadakategoriji, PripadakategorijiId> {
    List<Pripadakategoriji> findAllByOglas(Oglas oglas);

    List<Pripadakategoriji> findAllByKategorija(Kategorija kategorija);

    void deleteByOglas(Oglas oglas);
}

package hr.fer.oap.dao.repository;

import hr.fer.oap.domain.Oglas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OglasRepository extends JpaRepository<Oglas, Long> {
    @Query(value = "SELECT * FROM Oglas WHERE Oglas.sifra_korisnik = :id", nativeQuery = true)
    List<Oglas> findBySifraKorisnik(@Param("id") Long sifraKorisnik);
}

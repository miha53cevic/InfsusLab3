package hr.fer.oap.dao.repository;

import hr.fer.oap.domain.Mjesto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MjestoRepository extends JpaRepository<Mjesto, Long> {
    @Query(value = "SELECT * FROM Mjesto WHERE Mjesto.oznaka = :oznaka ORDER BY Mjesto.naziv ASC", nativeQuery = true)
    List<Mjesto> fetchByDrzavaOznaka(@Param("oznaka") String drzavaOznaka);
}

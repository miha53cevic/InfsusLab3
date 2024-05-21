package hr.fer.oap.dao.repository;

import hr.fer.oap.domain.Kategorija;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KategorijaRepository extends JpaRepository<Kategorija, Long> {
}

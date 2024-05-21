package hr.fer.oap.dao.repository;

import hr.fer.oap.domain.Drzava;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DrzavaRepository extends JpaRepository<Drzava, String> {
}

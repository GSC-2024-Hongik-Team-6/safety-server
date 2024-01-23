package gsc.backend.repository;

import gsc.backend.domain.Temp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TempRepository extends JpaRepository<Temp, Long> {
}

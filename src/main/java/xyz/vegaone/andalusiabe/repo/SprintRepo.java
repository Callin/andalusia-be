package xyz.vegaone.andalusiabe.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.vegaone.andalusiabe.domain.SprintEntity;

@Repository
public interface SprintRepo extends JpaRepository<SprintEntity, Long> {
}

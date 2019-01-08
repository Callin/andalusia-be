package xyz.vegaone.andalusiabe.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.vegaone.andalusiabe.domain.BugEntity;

@Repository
public interface BugRepo extends JpaRepository<BugEntity, Long> {
}

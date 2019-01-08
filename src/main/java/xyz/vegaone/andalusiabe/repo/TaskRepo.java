package xyz.vegaone.andalusiabe.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.vegaone.andalusiabe.domain.TaskEntity;

@Repository
public interface TaskRepo extends JpaRepository<TaskEntity, Long> {
}

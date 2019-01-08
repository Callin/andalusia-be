package xyz.vegaone.andalusiabe.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.vegaone.andalusiabe.domain.UserStoryEntity;

@Repository
public interface UserStoryRepo extends JpaRepository<UserStoryEntity, Long> {
}

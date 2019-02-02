package xyz.vegaone.andalusiabe.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import xyz.vegaone.andalusiabe.domain.ProjectEntity;
import xyz.vegaone.andalusiabe.domain.SprintEntity;
import xyz.vegaone.andalusiabe.domain.UserStoryEntity;

import java.util.List;

@Repository
public interface UserStoryRepo extends JpaRepository<UserStoryEntity, Long> {

    List<UserStoryEntity> findAllByProject(ProjectEntity project);

    List<UserStoryEntity> findAllByProjectAndSprint(ProjectEntity project, SprintEntity sprint);
}

package xyz.vegaone.andalusiabe.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.vegaone.andalusiabe.domain.ProjectEntity;
import xyz.vegaone.andalusiabe.domain.UserEntity;

import java.util.List;

@Repository
public interface ProjectRepo extends JpaRepository<ProjectEntity, Long> {

    List<ProjectEntity> findAllByOrganizationId(Long id);

    List<ProjectEntity> findAllByUsersIsContaining(UserEntity userEntity);
}

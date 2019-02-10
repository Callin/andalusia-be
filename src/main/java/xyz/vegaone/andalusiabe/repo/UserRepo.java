package xyz.vegaone.andalusiabe.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.vegaone.andalusiabe.domain.ProjectEntity;
import xyz.vegaone.andalusiabe.domain.UserEntity;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<UserEntity, Long> {
    List<UserEntity> findAllByOrganizationId(Long id);

    List<UserEntity> findAllByProjectsContaining(ProjectEntity project);

    UserEntity findByEmail(String email);
}

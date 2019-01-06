package xyz.vegaone.andalusiabe.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.vegaone.andalusiabe.domain.OrganizationEntity;
import xyz.vegaone.andalusiabe.domain.UserEntity;

import java.util.List;

@Repository
public interface OrganizationRepo extends JpaRepository<OrganizationEntity, Long> {
    List<OrganizationEntity> findAllByUsersIsContaining(UserEntity user);
}

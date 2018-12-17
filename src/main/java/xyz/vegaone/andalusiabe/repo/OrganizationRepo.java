package xyz.vegaone.andalusiabe.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.vegaone.andalusiabe.domain.OrganizationEntity;

@Repository
public interface OrganizationRepo extends JpaRepository<OrganizationEntity, Long> {
}

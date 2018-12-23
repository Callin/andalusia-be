package xyz.vegaone.andalusiabe.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.vegaone.andalusiabe.domain.UserEntity;

@Repository
public interface UserRepo extends JpaRepository<UserEntity, Long> {
}

package undp.judgment.list.judgment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import undp.judgment.list.judgment.models.jpa.UserEntity;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Integer> {
    Optional<UserEntity> findByUsername(String username);
}

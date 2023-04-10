package tt.authorization.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tt.authorization.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByEmail(String email);

    boolean existsByEmail(String email);

    @Query("select u from UserEntity u where u.role = 'ADMIN'")
    Optional<UserEntity> findByAdminRole();

    @Query("select u from UserEntity u where u.role = 'USER'")
    List<UserEntity> findAllByUserRole();
}

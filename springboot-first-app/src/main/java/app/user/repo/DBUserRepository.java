package app.user.repo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import app.user.model.UserEntity;

@Repository
public interface DBUserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByUsername(String username);


}
package app.userauth.repo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import app.userauth.model.UserEntity;

@Repository
public interface DBUserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByUsername(String username);


}
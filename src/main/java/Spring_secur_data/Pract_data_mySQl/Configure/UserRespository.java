package Spring_secur_data.Pract_data_mySQl.Configure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRespository extends JpaRepository<User, UUID> {

    Optional<User> findByUsername(String username);
}

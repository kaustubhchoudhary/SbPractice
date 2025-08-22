package sb.practice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sb.practice.dto.UserLoginDTO;
import sb.practice.entities.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    boolean existsByUsername(String username);
    boolean existsByEmailId(String emailId);

    @Query("SELECT new sb.practice.dto.UserLoginDTO(u.username, u.passwordSalt, u.passwordHash, r.roleName) FROM User u JOIN u.role r WHERE u.username = :username")
    Optional<UserLoginDTO> findLoginDetailsByUsername(@Param("username") String username);

}

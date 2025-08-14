package sb.practice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sb.practice.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    // SELECT * FROM users WHERE username = ?

    // Criteria API
    boolean existsByUsername(String username);
    boolean existsByEmailId(String emailId);
}

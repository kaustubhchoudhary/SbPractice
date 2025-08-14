package sb.practice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sb.practice.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
        boolean existsByRoleName(String roleName);
}

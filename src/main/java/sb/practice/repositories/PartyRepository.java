package sb.practice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sb.practice.entities.Party;

@Repository
public interface PartyRepository extends JpaRepository<Party, Integer> {
}

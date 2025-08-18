package sb.practice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sb.practice.entities.CandidateParty;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CandidatePartyRepository extends JpaRepository<CandidateParty, Integer> {

    // find all candidates of a specific party
    List<CandidateParty> findByPartyPartyId(Integer partyId);

    // find candidate by userId (if needed)
    CandidateParty findByCandidateUserId(Integer candidateUserId);

    // check if candidate is already mapped to a party
    boolean existsByCandidateUserId(Integer candidateUserId);

    // get candidate-party mapping by candidate and party
    CandidateParty findByCandidateUserIdAndPartyPartyId(Integer candidateUserId, Integer partyId);
}



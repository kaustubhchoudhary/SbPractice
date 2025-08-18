package sb.practice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sb.practice.dto.CandidatePartyDTO;
import sb.practice.entities.CandidateParty;
import sb.practice.entities.Party;
import sb.practice.entities.User;
import sb.practice.exceptions.ResourceNotFoundException;
import sb.practice.repositories.CandidatePartyRepository;
import sb.practice.repositories.PartyRepository;
import sb.practice.repositories.UserRepository;

@Service
public class CandidateService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PartyRepository partyRepository;

    @Autowired
    private CandidatePartyRepository candidatePartyRepository;

    public CandidatePartyDTO assignCandidateToParty(CandidatePartyDTO dto) {
        User user = userRepository.findById(dto.getCandidateUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (!user.getRole().getRoleName().equalsIgnoreCase("CANDIDATE")) {
            throw new IllegalArgumentException("User is not a candidate");
        }

        Party party = partyRepository.findById(dto.getPartyId())
                .orElseThrow(() -> new ResourceNotFoundException("Party not found"));

        CandidateParty candidateParty = new CandidateParty();
        candidateParty.setCandidate(user);
        candidateParty.setParty(party);

        CandidateParty saved = candidatePartyRepository.save(candidateParty);

        return new CandidatePartyDTO(saved.getId(), user.getUserId(), party.getPartyId());
    }

}

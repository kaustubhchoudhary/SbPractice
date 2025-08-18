package sb.practice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sb.practice.dto.PartyDTO;
import sb.practice.entities.Party;
import sb.practice.mappers.PartyMapper;
import sb.practice.repositories.PartyRepository;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PartyService {

    @Autowired
    private PartyRepository partyRepository;

    public PartyDTO addParty(PartyDTO partyDTO)  {
        Party party = new Party();
        party.setPartyName(partyDTO.getPartyName());

        // Convert MultipartFile to byte[]
        try {
            if (partyDTO.getPartySymbol() != null && !partyDTO.getPartySymbol().isEmpty()) {
                party.setPartySymbol(partyDTO.getPartySymbol().getBytes());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Party savedParty = partyRepository.save(party);

        return new PartyDTO(savedParty.getPartyId(), savedParty.getPartyName(), null);
    }

    public PartyDTO updateParty(int partyId, PartyDTO partyDTO) {
        try {
            Party party = partyRepository.findById(partyId)
                    .orElseThrow(() -> new RuntimeException("Party not found"));

            party.setPartyName(partyDTO.getPartyName());

            MultipartFile symbolFile = partyDTO.getPartySymbol();

            if (symbolFile != null && !symbolFile.isEmpty()) {
                party.setPartySymbol(symbolFile.getBytes());
            }

            Party updated = partyRepository.save(party);

            return PartyMapper.convertToDTO(updated);

        } catch (IOException e) {
            throw new RuntimeException("Error updating party symbol", e);
        }
    }

    public void deleteParty(int partyId) {
        Party party = partyRepository.findById(partyId)
                .orElseThrow(() -> new RuntimeException("Party not found"));
        partyRepository.delete(party);
    }

    public PartyDTO getPartyById(int partyId) {
        Party party = partyRepository.findById(partyId)
                .orElseThrow(() -> new RuntimeException("Party not found"));
        return PartyMapper.convertToDTO(party);
    }

    public List<PartyDTO> getAllParties() {
        return partyRepository.findAll()
                .stream()
                .map(PartyMapper::convertToDTO)
                .collect(Collectors.toList());
    }
}
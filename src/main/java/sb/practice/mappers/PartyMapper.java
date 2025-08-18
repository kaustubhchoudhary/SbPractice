package sb.practice.mappers;

import org.springframework.web.multipart.MultipartFile;
import sb.practice.dto.PartyDTO;
import sb.practice.entities.Party;

import java.io.IOException;

public class PartyMapper {

    public static PartyDTO convertToDTO(Party party) {
        PartyDTO dto = new PartyDTO();
        dto.setPartyId(party.getPartyId());
        dto.setPartyName(party.getPartyName());
        return dto;
    }

    public static Party convertToParty(PartyDTO partyDTO) throws IOException {
        Party party = new Party();

        party.setPartyName(partyDTO.getPartyName());

        MultipartFile symbolFile = partyDTO.getPartySymbol();
        if (symbolFile != null && !symbolFile.isEmpty()) {
            party.setPartySymbol(symbolFile.getBytes());
        }

        return party;
    }
}

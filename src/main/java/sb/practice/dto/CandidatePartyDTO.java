package sb.practice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CandidatePartyDTO {
    private int id;
    private int candidateUserId;   // candidate from User table
    private int partyId;           // party ID
}

package sb.practice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CandidatePartyResponseDTO {
    private int id;
    private int candidateUserId;
    private String candidateName;
    private int partyId;
    private String partyName;
}
package sb.practice.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PartyDTO {
    private int partyId;
    private String partyName;
    private MultipartFile partySymbol;
}


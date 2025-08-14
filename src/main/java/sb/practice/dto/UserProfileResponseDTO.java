package sb.practice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileResponseDTO {
    private int userId;
    private String fullName;
    private String address;
    private String phoneNo;
    private String emailId;
    private String profileImageUrl; // can be null if no image
    private List<String> idDocUrls; // can be empty
}

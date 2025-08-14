package sb.practice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO {
    private String fullName;
    private LocalDate dateOfBirth;
    private String gender;
    private String username;
    private String address;
    private String emailId;
    private String phoneNo;
}

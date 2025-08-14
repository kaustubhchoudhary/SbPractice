package sb.practice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationDTO {
    private String fullName;
    private LocalDate dateOfBirth;
    private String gender;
    private String username;
    private String password;
    private String emailId;
    private int roleId;
}
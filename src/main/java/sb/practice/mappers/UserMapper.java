package sb.practice.mappers;

import sb.practice.dto.UserProfileResponseDTO;
import sb.practice.dto.UserRegistrationDTO;
import sb.practice.dto.UserProfileDTO;
import sb.practice.dto.UserResponseDTO;
import sb.practice.entities.Role;
import sb.practice.entities.User;

import java.time.LocalDateTime;

public class UserMapper {

    // Convert UserRegistrationDTO to User entity (for new registration)
    public static User toEntity(UserRegistrationDTO dto, Role role,
                                String passwordSalt, String passwordHash) {
        User user = new User();
        user.setFullName(dto.getFullName());
        user.setDateOfBirth(dto.getDateOfBirth());
        user.setGender(dto.getGender());
        user.setUsername(dto.getUsername());
        user.setPasswordSalt(passwordSalt);
        user.setPasswordHash(passwordHash);
        user.setEmailId(dto.getEmailId());
        user.setRole(role);
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());

        // only super admin is by default authorized
        int roleId = role.getRoleId();
        user.setAuthStatus(roleId == 1);

        return user;
    }

    // Convert User to UserResponseDTO
    public static UserResponseDTO toDto(User user) {
        UserResponseDTO userResponseDTO = new UserResponseDTO();

        userResponseDTO.setFullName(user.getFullName());
        userResponseDTO.setDateOfBirth(user.getDateOfBirth());
        userResponseDTO.setGender(user.getGender());
        userResponseDTO.setUsername(user.getUsername());
        userResponseDTO.setEmailId(user.getEmailId());
        userResponseDTO.setAddress(user.getAddress());
        userResponseDTO.setPhoneNo(user.getPhoneNo());

        return userResponseDTO;
    }

    public static UserProfileResponseDTO toProfileResponseDTO(User user) {
        return new UserProfileResponseDTO(
                user.getUserId(),
                user.getFullName(),
                user.getAddress(),
                user.getPhoneNo(),
                user.getEmailId(),
                user.getProfileImage() != null ? "path/to/image/storage/" + user.getUserId() + ".jpg" : null,
                user.getIdDocs()
        );
    }
}
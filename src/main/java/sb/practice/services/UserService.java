package sb.practice.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sb.practice.dto.UserProfileDTO;
import sb.practice.dto.UserProfileResponseDTO;
import sb.practice.dto.UserRegistrationDTO;
import sb.practice.dto.UserResponseDTO;
import sb.practice.entities.Role;
import sb.practice.entities.User;
import sb.practice.exceptions.FileStorageException;
import sb.practice.exceptions.ResourceAlreadyExistsException;
import sb.practice.exceptions.ResourceNotFoundException;
import sb.practice.mappers.UserMapper;
import sb.practice.repositories.RoleRepository;
import sb.practice.repositories.UserRepository;
import sb.practice.utility.Utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final String UPLOAD_DIR = "uploads/";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    // Register a new user
    public UserResponseDTO registerUser(UserRegistrationDTO dto) throws IllegalArgumentException {

        // Validate unique username
        if (userRepository.existsByUsername(dto.getUsername())) {
            throw new ResourceAlreadyExistsException("User", "username", dto.getUsername());
        }

        // Validate unique email
        if (userRepository.existsByEmailId(dto.getEmailId())) {
            throw new ResourceAlreadyExistsException("Email", "emailId", dto.getEmailId());
        }

        // Check whether role is present or not
        Role role = roleRepository.findById(dto.getRoleId())
                .orElseThrow(() -> new ResourceNotFoundException("Role not found with ID: " + dto.getRoleId()));

        // Generate password salt and hash
        String salt = Utils.generateSalt();
        String newPassword = salt + dto.getPassword();
        String hashedPassword = Utils.generateHash(newPassword);

        // Map DTO to User entity
        User userToSave = UserMapper.toEntity(dto, role, salt, hashedPassword);

        User savedUser = userRepository.save(userToSave);

        // Return saved user converted to DTO
        return UserMapper.toDto(savedUser );
    }

    public UserProfileResponseDTO updateUserProfile(int userId, UserProfileDTO dto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User with id " + String.valueOf(userId) + " not found"));

        user.setAddress(dto.getAddress());
        user.setPhoneNo(dto.getPhoneNo());

        // Profile image
        if (dto.getProfileImage() != null && !dto.getProfileImage().isEmpty()) {
            try {
                user.setProfileImage(dto.getProfileImage().getBytes());
            } catch (IOException e) {
                throw new FileStorageException("Failed to save profile image");
            }
        }

        // ID documents
        if (dto.getIdDocs() != null && !dto.getIdDocs().isEmpty()) {
            List<String> urls = new ArrayList<>();
            for (MultipartFile file : dto.getIdDocs()) {
                try {
                    String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
                    Path path = Paths.get(UPLOAD_DIR + fileName);
                    Files.createDirectories(path.getParent());
                    Files.write(path, file.getBytes());
                    urls.add(path.toString());
                } catch (IOException e) {
                    throw new FileStorageException("Failed to save ID document: " + file.getOriginalFilename());
                }
            }
            user.setIdDocs(urls);
        }

        User savedUser = userRepository.save(user);
        return UserMapper.toProfileResponseDTO(savedUser);
    }


    // ✅ Get a single user by ID
    public User getUserById(int userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User not found with ID: " + userId));
    }

    // ✅ Get all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // ✅ Delete a user by ID
    public void deleteUserById(int userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("Cannot delete. User not found with ID: " + userId));
        userRepository.delete(user);
    }
}

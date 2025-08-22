package sb.practice.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sb.practice.dto.*;
import sb.practice.entities.User;
import sb.practice.services.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@Tag(name = "Users", description = "APIs for managing users in the system")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    // Register User
    @PostMapping
    public ResponseEntity<ApiResponseDTO<UserResponseDTO>> registerUser(@RequestBody UserRegistrationDTO userDTO) {

        UserResponseDTO savedUser = userService.registerUser(userDTO);

        ApiResponseDTO<UserResponseDTO> response = new ApiResponseDTO<>(
                201,
                "User registered successfully",
                savedUser
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<ApiResponseDTO<UserProfileResponseDTO>> updateUserProfile(
            @PathVariable int userId,
            @ModelAttribute UserProfileDTO userProfileDTO) {

        UserProfileResponseDTO updatedProfile = userService.updateUserProfile(userId, userProfileDTO);
        ApiResponseDTO<UserProfileResponseDTO> response =
                new ApiResponseDTO<>(200, "Profile updated successfully", updatedProfile);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponseDTO<User>> getUser(@PathVariable int userId) {
        User user = userService.getUserById(userId);
        return ResponseEntity.ok(new ApiResponseDTO<>(200,
                "User retrieved successfully",
                user));
    }

    @GetMapping
    public ResponseEntity<ApiResponseDTO<List<UserResponseDTO>>> getAllUsers() {
        List<UserResponseDTO> userResponseDTO = userService.getAllUsers();
        return ResponseEntity.ok(new ApiResponseDTO<>(200,
                "All users retrieved successfully",
                userResponseDTO));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponseDTO<Void>> deleteUser(@PathVariable int userId) {
        userService.deleteUserById(userId);
        return ResponseEntity.ok(new ApiResponseDTO<>(200, "User deleted successfully", null));
    }

    @PostMapping("/bulk")
    public ResponseEntity<ApiResponseDTO<List<UserResponseDTO>>> addUsersBulk(
            @RequestBody List<UserRegistrationDTO> users) {

        List<UserResponseDTO> savedUsers = userService.addUsersBulk(users);

        ApiResponseDTO<List<UserResponseDTO>> response = new ApiResponseDTO<>(
                HttpStatus.CREATED.value(),
                "Users added successfully",
                savedUsers
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}

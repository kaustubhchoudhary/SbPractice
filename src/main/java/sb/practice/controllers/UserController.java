package sb.practice.controllers;

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
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    // Register User
    @PostMapping
    public ResponseEntity<ApiResponse<UserResponseDTO>> registerUser(@RequestBody UserRegistrationDTO userDTO) {

        UserResponseDTO savedUser = userService.registerUser(userDTO);

        ApiResponse<UserResponseDTO> response = new ApiResponse<>(
                201,
                "User registered successfully",
                savedUser
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{userId}/profile")
    public ResponseEntity<ApiResponse<UserProfileResponseDTO>> updateUserProfile(
            @PathVariable int userId,
            @ModelAttribute UserProfileDTO userProfileDTO) {

        UserProfileResponseDTO updatedProfile = userService.updateUserProfile(userId, userProfileDTO);
        ApiResponse<UserProfileResponseDTO> response =
                new ApiResponse<>(200, "Profile updated successfully", updatedProfile);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse<User>> getUser(@PathVariable int userId) {
        User user = userService.getUserById(userId);
        return ResponseEntity.ok(new ApiResponse<>(200, "User retrieved successfully", user));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<User>>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(new ApiResponse<>(200, "All users retrieved successfully", users));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse<Void>> deleteUser(@PathVariable int userId) {
        userService.deleteUserById(userId);
        return ResponseEntity.ok(new ApiResponse<>(200, "User deleted successfully", null));
    }
}

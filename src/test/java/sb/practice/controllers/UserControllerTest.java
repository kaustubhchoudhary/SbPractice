//package sb.practice.controllers;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.bean.override.mockito.MockitoBean;
//import org.springframework.test.web.servlet.MockMvc;
//import sb.practice.dto.UserProfileDTO;
//import sb.practice.dto.UserProfileResponseDTO;
//import sb.practice.dto.UserRegistrationDTO;
//import sb.practice.dto.UserResponseDTO;
//import sb.practice.entities.User;
//import sb.practice.services.UserService;
//
//import java.time.LocalDate;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.eq;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@WebMvcTest(UserController.class)
//class UserControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockitoBean
//    private UserService userService;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    // ✅ Test Create User
//    @Test
//    void testCreateUser() throws Exception {
//        User user = getSampleUser();
//
//        Mockito.when(userService.registerUser(any(UserRegistrationDTO.class))).thenReturn(new UserResponseDTO());
//
//        mockMvc.perform(post("/api/users")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(user)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.data.fullName").value("Amit Sharma"))
//                .andExpect(jsonPath("$.data.username").value("amitsharma"));
//    }
//
//    // ✅ Test Get All Users
//    @Test
//    void testGetAllUsers() throws Exception {
//        List<User> users = Arrays.asList(getSampleUser());
//        Mockito.when(userService.getAllUsers()).thenReturn(users);
//
//        mockMvc.perform(get("/api/users"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.data[0].username").value("amitsharma"));
//    }
//
//    // ✅ Test Get User By ID
//    @Test
//    void testGetUserById() throws Exception {
//        User user = getSampleUser();
//        Mockito.when(userService.getUserById(1)).thenReturn(user);
//
//        mockMvc.perform(get("/api/users/1"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.data.fullName").value("Amit Sharma"));
//    }
//
//    // ✅ Test Update User
//    @Test
//    void testUpdateUser() throws Exception {
//        User updatedUser = getSampleUser();
//        updatedUser.setFullName("Amit Kumar");
//
//        Mockito.when(userService.updateUserProfile(eq(1), any(UserProfileDTO.class))).thenReturn(updatedUser);
//
//        mockMvc.perform(put("/api/users/1")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(updatedUser)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.data.fullName").value("Amit Kumar"));
//    }
//
//    // ✅ Test Delete User
//    @Test
//    void testDeleteUser() throws Exception {
//        Mockito.when(userService.deleteUser(1)).thenReturn(true);
//
//        mockMvc.perform(delete("/api/users/1"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.message").value("User deleted successfully"));
//    }
//
//    // Helper Method for Sample User
//    private User getSampleUser() {
//        return new User(
//                1,
//                "Amit Sharma",
//                LocalDate.of(1990, 5, 10),
//                "M",
//                "amitsharma",
//                "salt123",
//                "hash123",
//                "Delhi, India",
//                "amit.sharma@example.com",
//                "9876543210",
//                null,
//                null,
//                true,
//                null,
//                Arrays.asList("aadhaar.pdf", "pan.pdf"),
//                null
//        );
//    }
//}
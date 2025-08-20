package sb.practice.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sb.practice.dto.ApiResponseDTO;
import sb.practice.dto.LoginRequestDTO;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentication", description = "Session-based login/logout")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<ApiResponseDTO<String>> login(
            @RequestBody LoginRequestDTO loginRequest,
            HttpServletRequest request) {

        try {
            // 1. Authenticate using your CustomAuthenticationProvider
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );

            // 2. Store authentication in session
            SecurityContextHolder.getContext().setAuthentication(authentication);
            request.getSession(true).setAttribute(
                    HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                    SecurityContextHolder.getContext()
            );

            // 3. Return session ID for Postman/Swagger testing
            ApiResponseDTO<String> response = new ApiResponseDTO<>();
            response.setStatus(HttpStatus.OK.value());
            response.setMessage("Login successful");
            response.setData("Session ID: " + request.getSession().getId());

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            ApiResponseDTO<String> response = new ApiResponseDTO<>();
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setMessage("Invalid username or password");
            response.setData(null);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<ApiResponseDTO<String>> logout(HttpServletRequest request) {
        request.getSession().invalidate();

        ApiResponseDTO<String> response = new ApiResponseDTO<>();
        response.setStatus(HttpStatus.OK.value());
        response.setMessage("Logout successful");
        response.setData(null);

        return ResponseEntity.ok(response);
    }
}


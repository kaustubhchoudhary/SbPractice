package sb.practice.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import sb.practice.utility.Utils;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private CustomUserDetailsService userDetailsService; // our Step 2 service

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String rawPassword = authentication.getCredentials().toString();

        // Load user from DB
        CustomUserDetails userDetails = (CustomUserDetails) userDetailsService.loadUserByUsername(username);

        // Get stored salt + hash
        String salt = userDetails.getPasswordSalt();
        String storedHash = userDetails.getPassword();

        System.err.println("\n rawPassword : " + rawPassword);
        System.err.println("\n Salt : " + salt);
        System.err.println("\n storedHash : " + storedHash);

        // Compute hash from input password + stored salt
        String newPassword = salt + rawPassword;
        String computedHash = Utils.generateHash(newPassword);
        System.err.println("\n computedHash : " + computedHash);

        if (!computedHash.equals(storedHash)) {
            throw new BadCredentialsException("Invalid username or password");
        }

        // Return authenticated token with authorities
        return new UsernamePasswordAuthenticationToken(
                userDetails, rawPassword, userDetails.getAuthorities()
        );
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.equals(authentication);
    }
}
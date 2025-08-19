package sb.practice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

//@Configuration
public class SecurityConfigInMemory {

//    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails admin = User.withUsername("boss")
                .password("{noop}boss123") // {noop} => no password encoder
                .roles("ADMIN")
                .build();

        UserDetails subAdmin = User.withUsername("subadmin")
                .password("{noop}sub123")
                .roles("SUBADMIN")
                .build();

        return new InMemoryUserDetailsManager(admin, subAdmin);
    }

//    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // CSRF is enabled by default for browser-based apps, disable if testing with Postman
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/roles/create").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults()); // Enables HTTP Basic auth
        return http.build();
    }
}
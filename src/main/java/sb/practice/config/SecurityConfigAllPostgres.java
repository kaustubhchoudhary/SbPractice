//package sb.practice.config;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//@RequiredArgsConstructor
//public class SecurityConfigAllPostgres {
//
//    private final CustomAuthenticationProvider customAuthenticationProvider;
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//
//        http
//                .csrf(csrf -> csrf.disable())
//                .authenticationProvider(customAuthenticationProvider)
//                .authorizeHttpRequests(auth -> auth
//                        // RoleController
//                        .requestMatchers(HttpMethod.POST, "/api/roles").hasRole("SuperAdmin")
//                        .requestMatchers(HttpMethod.PUT, "/api/roles/**").hasRole("SuperAdmin")
//                        .requestMatchers(HttpMethod.DELETE, "/api/roles/**").hasRole("SuperAdmin")
//                        .requestMatchers(HttpMethod.GET, "/api/roles/**").hasAnyRole("SuperAdmin", "SubAdmin")
//
//                        // UserController
//                        .requestMatchers(HttpMethod.POST, "/api/users").hasRole("SuperAdmin")
//                        .requestMatchers(HttpMethod.PUT, "/api/users/**").authenticated() // self-update logic in service
//                        .requestMatchers(HttpMethod.GET, "/api/users").hasAnyRole("SuperAdmin", "SubAdmin")
//                        .requestMatchers(HttpMethod.GET, "/api/users/**").authenticated() // service checks self or SubAdmin/SuperAdmin
//                        .requestMatchers(HttpMethod.DELETE, "/api/users/**").hasRole("SuperAdmin")
//                        .requestMatchers(HttpMethod.POST, "/api/users/bulk").hasRole("SuperAdmin")
//
//                        // PartyController
//                        .requestMatchers(HttpMethod.POST, "/api/parties").hasAnyRole("SuperAdmin", "SubAdmin")
//                        .requestMatchers(HttpMethod.PUT, "/api/parties/**").hasAnyRole("SuperAdmin", "SubAdmin")
//                        .requestMatchers(HttpMethod.DELETE, "/api/parties/**").hasRole("SuperAdmin")
//                        .requestMatchers(HttpMethod.GET, "/api/parties/**").authenticated() // all logged-in users
//
//                        // CandidateController
//                        .requestMatchers(HttpMethod.POST, "/api/candidates").hasAnyRole("SuperAdmin", "SubAdmin")
//
//                        // any other request
//                        .anyRequest().authenticated()
//                )
//                .httpBasic(Customizer.withDefaults());
//
//        return http.build();
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
//        return authConfig.getAuthenticationManager();
//    }
//}
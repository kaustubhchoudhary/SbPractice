//package sb.practice.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import sb.practice.dto.UserLoginDTO;
//import sb.practice.entities.User;
//import sb.practice.repositories.UserRepository;
//
//@Service
//public class CustomUserDetailsService implements UserDetailsService {
//
//    @Autowired
//    private UserRepository userRepository; // JPA repository for User entity
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        // Fetch user from DB along with role
//        UserLoginDTO userLoginDTO = userRepository.findLoginDetailsByUsername(username)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
//
//        System.err.println("\n Spring Security : " + userLoginDTO);
//
//        // Build CustomUserDetails
//        return new CustomUserDetails(
//                userLoginDTO.getUsername(),
//                userLoginDTO.getPasswordHash(),
//                userLoginDTO.getPasswordSalt(),
//                userLoginDTO.getRoleName()
//        );
//    }
//}

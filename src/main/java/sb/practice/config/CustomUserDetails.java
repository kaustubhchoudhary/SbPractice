//package sb.practice.config;
//
//import lombok.Getter;
//import org.springframework.security.core.GrantedAuthority;
//
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.Collection;
//
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//
//import java.util.Collections;
//
//public class CustomUserDetails implements UserDetails {
//
//    private final String username;
//    private final String passwordHash;
//
//    @Getter
//    private final String passwordSalt;
//
//    @Getter
//    private final String roleName; // store role name
//
//    public CustomUserDetails(String username, String passwordHash, String passwordSalt, String roleName) {
//        this.username = username;
//        this.passwordHash = passwordHash;
//        this.passwordSalt = passwordSalt;
//        this.roleName = roleName;
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        // create authorities dynamically from roleName
//        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + roleName));
//    }
//
//    @Override
//    public String getPassword() {
//        return passwordHash;
//    }
//
//    @Override
//    public String getUsername() {
//        return username;
//    }
//
//    @Override public boolean isAccountNonExpired() { return true; }
//    @Override public boolean isAccountNonLocked() { return true; }
//    @Override public boolean isCredentialsNonExpired() { return true; }
//    @Override public boolean isEnabled() { return true; }
//}
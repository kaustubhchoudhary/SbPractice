package sb.practice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    // Gen
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;

    // UR
    @Column(name = "full_name", nullable = false, length = 70)
    private String fullName;

    // UR
    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    // UR
    @Column(name = "gender", length = 1)
    private String gender;

    // UR
    @Column(name = "username", nullable = false, unique = true, length = 30)
    private String username;

    // Gen
    @Column(name = "password_salt", nullable = false, length = 10)
    private String passwordSalt;

    // Gen
    @Column(name = "password_hash", nullable = false, length = 64)
    private String passwordHash;

    // UP
    @Column(name = "address", length = 100)
    private String address;

    // UR
    @Column(name = "email_id", nullable = false, unique = true, length = 70)
    private String emailId;

    // UP
    @Column(name = "phone_no", length = 10)
    private String phoneNo;

    // Gen
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    // Gen
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // Gen
    @Column(name = "auth_status", nullable = false)
    private boolean authStatus;

    // UP
    @Lob
    @Column(name = "profile_image")
    private byte[] profileImage;

    // UP
    @ElementCollection
    @CollectionTable(name = "user_id_docs", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "doc_url", length = 100)
    private List<String> idDocs;

    // UR
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

}

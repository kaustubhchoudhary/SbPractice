package sb.practice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "roles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private int roleId;

    @Column(name = "role_name", nullable = false, unique = true, length = 50)
    private String roleName;

    // One Role can be assigned to multiple Users
    // remove cascade = CascadeType.ALL, orphanRemoval = true
    @OneToMany(mappedBy = "role",  fetch = FetchType.LAZY)
    private List<User> users;

}


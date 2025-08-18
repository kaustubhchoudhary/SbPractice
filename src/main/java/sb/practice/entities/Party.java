package sb.practice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "parties")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Party {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "party_id")
    private int partyId;

    @Column(name = "party_name", nullable = false, unique = true, length = 30)
    private String partyName;

    @Lob
    @Column(name = "party_symbol")
    private byte[] partySymbol;
}
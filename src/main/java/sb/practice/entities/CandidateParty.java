package sb.practice.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "candidate_party")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CandidateParty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    // Candidate is just a User with role = CANDIDATE
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "candidate_user_id", nullable = false)
    private User candidate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "party_id", nullable = false)
    private Party party;
}


package me.medelhimar.elhimarmohammedexamjee.entites;

import jakarta.persistence.*;
import lombok.*;
import me.medelhimar.elhimarmohammedexamjee.enums.ContractStatus;

import java.util.Date;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
@Data @NoArgsConstructor @AllArgsConstructor
public abstract class Contrat {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date dateSouscription;
    @ManyToOne
    private Client client;
    @Enumerated(EnumType.STRING)
    private ContractStatus status;
    private Date dateValidation;
    private double montantCotisation;
    private int duree;
    private double tauxCouverture;
    @OneToMany(mappedBy = "contrat", fetch = FetchType.LAZY)
    private List<Paiement> paiements;
}

package me.medelhimar.elhimarmohammedexamjee.entites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor @AllArgsConstructor
public class Paiement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date datePaiement;
    private double montant;
    @Enumerated(EnumType.STRING)
    private TypePaiement typePaiement;
    @ManyToOne(fetch = FetchType.LAZY)
    private Contrat contrat;
}

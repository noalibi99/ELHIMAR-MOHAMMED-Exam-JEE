package me.medelhimar.elhimarmohammedexamjee.entites;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor
@AllArgsConstructor
public class ContratSante extends Contrat {
    @Enumerated(EnumType.STRING)
    private NiveauCouverture niveauCouverture;
    private int nbrPersonnesCouvertes;
}

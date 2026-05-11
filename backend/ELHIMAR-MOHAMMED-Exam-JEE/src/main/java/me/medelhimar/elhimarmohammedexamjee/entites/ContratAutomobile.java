package me.medelhimar.elhimarmohammedexamjee.entites;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data @NoArgsConstructor
@AllArgsConstructor
public class ContratAutomobile extends Contrat {
    private String numeroImmatriculation;
    private String marqueVehicule;
    private String modeleVehicule;
}

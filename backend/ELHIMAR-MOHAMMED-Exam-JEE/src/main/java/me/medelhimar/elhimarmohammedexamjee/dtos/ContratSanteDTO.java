package me.medelhimar.elhimarmohammedexamjee.dtos;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.medelhimar.elhimarmohammedexamjee.entites.Contrat;
import me.medelhimar.elhimarmohammedexamjee.enums.NiveauCouverture;

@Data
public class ContratSanteDTO extends ContratDTO {
    private NiveauCouverture niveauCouverture;
    private int nbrPersonnesCouvertes;
}

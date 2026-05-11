package me.medelhimar.elhimarmohammedexamjee.dtos;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.medelhimar.elhimarmohammedexamjee.entites.Contrat;

@Data
public class ContratAutomobileDTO extends ContratDTO {
    private String numeroImmatriculation;
    private String marqueVehicule;
    private String modeleVehicule;
}

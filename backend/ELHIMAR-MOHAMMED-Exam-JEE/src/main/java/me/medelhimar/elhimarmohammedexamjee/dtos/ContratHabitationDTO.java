package me.medelhimar.elhimarmohammedexamjee.dtos;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.medelhimar.elhimarmohammedexamjee.entites.Contrat;
import me.medelhimar.elhimarmohammedexamjee.enums.TypeLogement;

@Data
public class ContratHabitationDTO extends ContratDTO {
    private TypeLogement typeLogement;
    private String adresseLogement;
    private double superficie;
}
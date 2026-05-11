package me.medelhimar.elhimarmohammedexamjee.entites;

import jakarta.persistence.*;
import lombok.*;
import me.medelhimar.elhimarmohammedexamjee.enums.TypeLogement;

@Entity
@Data @NoArgsConstructor
@AllArgsConstructor
public class ContratHabitation extends Contrat {
    @Enumerated(EnumType.STRING)
    private TypeLogement typeLogement;
    private String adresseLogement;
    private double superficie;
}

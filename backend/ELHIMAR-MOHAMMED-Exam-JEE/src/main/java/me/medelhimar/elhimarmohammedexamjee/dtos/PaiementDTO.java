package me.medelhimar.elhimarmohammedexamjee.dtos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.medelhimar.elhimarmohammedexamjee.entites.Contrat;
import me.medelhimar.elhimarmohammedexamjee.enums.TypePaiement;

import java.util.Date;

@Data
public class PaiementDTO {
    private Long id;
    private Date datePaiement;
    private double montant;
    private TypePaiement typePaiement;
    private Contrat contrat;
}

// java
package me.medelhimar.elhimarmohammedexamjee.dtos;

import lombok.Data;
import me.medelhimar.elhimarmohammedexamjee.enums.ContractStatus;

import java.util.Date;

@Data
public abstract class ContratDTO {
    private Long id;
    private Date dateSouscription;
    private ContractStatus status;
    private Date dateValidation;
    private double montantCotisation;
    private int duree;
    private double tauxCouverture;
    private String type;
    private ClientDTO client;
}
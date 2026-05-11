package me.medelhimar.elhimarmohammedexamjee.dtos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.medelhimar.elhimarmohammedexamjee.entites.Contrat;

import java.util.List;

@Data
public class ClientDTO {
    private Long id;
    private String name;
    private String email;
}

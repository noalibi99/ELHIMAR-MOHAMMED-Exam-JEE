package me.medelhimar.elhimarmohammedexamjee.repositories;

import me.medelhimar.elhimarmohammedexamjee.entites.Contrat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContratRepository extends JpaRepository<Contrat, Long> {

}

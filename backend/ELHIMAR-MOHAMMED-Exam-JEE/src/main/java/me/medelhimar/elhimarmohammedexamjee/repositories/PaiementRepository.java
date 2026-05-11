package me.medelhimar.elhimarmohammedexamjee.repositories;

import me.medelhimar.elhimarmohammedexamjee.entites.Paiement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaiementRepository extends JpaRepository<Paiement, Long> {

}

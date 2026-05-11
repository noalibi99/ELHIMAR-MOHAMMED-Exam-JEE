package me.medelhimar.elhimarmohammedexamjee.repositories;

import me.medelhimar.elhimarmohammedexamjee.entites.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

}

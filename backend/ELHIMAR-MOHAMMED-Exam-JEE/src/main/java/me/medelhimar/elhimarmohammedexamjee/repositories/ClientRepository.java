package me.medelhimar.elhimarmohammedexamjee.repositories;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByEmail(String email);
    List<Client> findByNomContainingIgnoreCase(String nom);
}

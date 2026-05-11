package me.medelhimar.elhimarmohammedexamjee;

import me.medelhimar.elhimarmohammedexamjee.entites.*;
import me.medelhimar.elhimarmohammedexamjee.enums.ContractStatus;
import me.medelhimar.elhimarmohammedexamjee.enums.NiveauCouverture;
import me.medelhimar.elhimarmohammedexamjee.enums.TypeLogement;
import me.medelhimar.elhimarmohammedexamjee.enums.TypePaiement;
import me.medelhimar.elhimarmohammedexamjee.repositories.ClientRepository;
import me.medelhimar.elhimarmohammedexamjee.repositories.ContratRepository;
import me.medelhimar.elhimarmohammedexamjee.repositories.PaiementRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.stream.Stream;


@SpringBootApplication
public class ElhimarMohammedExamJeeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ElhimarMohammedExamJeeApplication.class, args);
    }

    @Bean
    CommandLineRunner start(
            ClientRepository clientRepository,
            ContratRepository contratRepository,
            PaiementRepository paiementRepository

    ) {
        return args -> {
            Stream.of("Mohammed", "Hassan", "Anass").forEach(
                    name -> {
                        Client client = new Client();
                        client.setName(name);
                        client.setEmail(name + "@gmail.com");
                        clientRepository.save(client);
                    }
            );

             clientRepository.findAll().forEach(
                     client -> {
                         for (int i = 0; i < 3; i++) {
                             ContratAutomobile contratAutomobile = new ContratAutomobile();
                             contratAutomobile.setClient(client);
                             contratAutomobile.setDateSouscription(new Date());
                             contratAutomobile.setStatus(ContractStatus.IN_PROGRESS);
                             contratAutomobile.setMontantCotisation(Math.random()*100);
                             contratAutomobile.setDuree(12);
                             contratAutomobile.setTauxCouverture(0.8);
                             contratAutomobile.setNumeroImmatriculation("AB-" + (int)(Math.random()*1000));
                             contratAutomobile.setMarqueVehicule("Dacia");
                             contratAutomobile.setModeleVehicule("Logan");
                             contratRepository.save(contratAutomobile);

                             ContratHabitation contratHabitation = new ContratHabitation();
                             contratHabitation.setClient(client);
                             contratHabitation.setDateSouscription(new Date());
                             contratHabitation.setStatus(ContractStatus.IN_PROGRESS);
                             contratHabitation.setMontantCotisation(Math.random()*100);
                             contratHabitation.setDuree(12);
                             contratHabitation.setTauxCouverture(0.8);
                             contratHabitation.setAdresseLogement("Bd Med V, Casablanca");
                             contratHabitation.setTypeLogement(TypeLogement.APPARTEMENT);
                             contratHabitation.setSuperficie(120);
                             contratRepository.save(contratHabitation);

                             ContratSante sante = new ContratSante();
                             sante.setClient(client);
                             sante.setDateSouscription(new Date());
                             sante.setStatus(ContractStatus.IN_PROGRESS);
                             sante.setMontantCotisation(Math.random() * 1000);
                             sante.setDuree(24);
                             sante.setTauxCouverture(0.9);
                             sante.setNiveauCouverture(NiveauCouverture.PREMIUM);
                             sante.setNbrPersonnesCouvertes(3);
                             contratRepository.save(sante);

                         }
                     }

             );

            contratRepository.findAll().forEach(contrat -> {
                for (int i = 0; i < 3; i++) {
                    Paiement p = new Paiement();
                    p.setContrat(contrat);
                    p.setDatePaiement(new Date());
                    p.setMontant(contrat.getMontantCotisation());
                    p.setTypePaiement(TypePaiement.values()[i % TypePaiement.values().length]);
                    paiementRepository.save(p);
                }
            });
        };
    }
}

package me.medelhimar.elhimarmohammedexamjee.web;

import me.medelhimar.elhimarmohammedexamjee.dtos.*;
import me.medelhimar.elhimarmohammedexamjee.enums.ContractStatus;
import me.medelhimar.elhimarmohammedexamjee.services.AssuranceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class AssuranceRestController {

    private final AssuranceService assuranceService;

    public AssuranceRestController(AssuranceService assuranceService) {
        this.assuranceService = assuranceService;
    }

    // Clients
    @PostMapping("/clients")
    public ClientDTO saveClient(@RequestBody ClientDTO clientDTO) {
        return assuranceService.saveClient(clientDTO);
    }

    @GetMapping("/clients/{id}")
    public ClientDTO getClient(@PathVariable Long id) {
        return assuranceService.getClient(id);
    }

    @PutMapping("/clients/{id}")
    public ClientDTO updateClient(@PathVariable Long id, @RequestBody ClientDTO clientDTO) {
        return assuranceService.updateClient(id, clientDTO);
    }

    @DeleteMapping("/clients/{id}")
    public void deleteClient(@PathVariable Long id) {
        assuranceService.deleteClient(id);
    }

    @GetMapping("/clients")
    public List<ClientDTO> getAllClients() {
        return assuranceService.getAllClients();
    }

    @GetMapping("/clients/{id}/contrats")
    public List<ContratDTO> getContratsByClient(@PathVariable Long id) {
        return assuranceService.getContratsByClient(id);
    }

    // Contrats
    @PostMapping("/clients/{clientId}/contrats/automobile")
    public ContratAutomobileDTO saveContratAutomobile(
            @PathVariable Long clientId,
            @RequestBody ContratAutomobileDTO dto
    ) {
        return assuranceService.saveContratAutomobile(clientId, dto);
    }

    @PostMapping("/clients/{clientId}/contrats/habitation")
    public ContratHabitationDTO saveContratHabitation(
            @PathVariable Long clientId,
            @RequestBody ContratHabitationDTO dto
    ) {
        return assuranceService.saveContratHabitation(clientId, dto);
    }

    @PostMapping("/clients/{clientId}/contrats/sante")
    public ContratSanteDTO saveContratSante(
            @PathVariable Long clientId,
            @RequestBody ContratSanteDTO dto
    ) {
        return assuranceService.saveContratSante(clientId, dto);
    }

    @GetMapping("/contrats/{id}")
    public ContratDTO getContrat(@PathVariable Long id) {
        return assuranceService.getContrat(id);
    }

    @GetMapping("/contrats")
    public List<ContratDTO> getAllContrats() {
        return assuranceService.getAllContrats();
    }

    @GetMapping("/contrats/status/{status}")
    public List<ContratDTO> getContratsByStatus(@PathVariable ContractStatus status) {
        return assuranceService.getContratsByStatus(status);
    }

    @PutMapping("/contrats/{id}/valider")
    public ContratDTO validerContrat(@PathVariable Long id) {
        return assuranceService.validerContrat(id);
    }

    @PutMapping("/contrats/{id}/resilier")
    public ContratDTO resilierContrat(@PathVariable Long id) {
        return assuranceService.resilierContrat(id);
    }

    @DeleteMapping("/contrats/{id}")
    public void deleteContrat(@PathVariable Long id) {
        assuranceService.deleteContrat(id);
    }

    // Paiements
    @PostMapping("/contrats/{contratId}/paiements")
    public PaiementDTO savePaiement(@PathVariable Long contratId, @RequestBody PaiementDTO dto) {
        return assuranceService.savePaiement(contratId, dto);
    }

    @GetMapping("/paiements/{id}")
    public PaiementDTO getPaiement(@PathVariable Long id) {
        return assuranceService.getPaiement(id);
    }

    @GetMapping("/contrats/{contratId}/paiements")
    public List<PaiementDTO> getPaiementsByContrat(@PathVariable Long contratId) {
        return assuranceService.getPaiementsByContrat(contratId);
    }

    @GetMapping("/contrats/{contratId}/paiements/total")
    public double getTotalPaiementsByContrat(@PathVariable Long contratId) {
        return assuranceService.getTotalPaiementsByContrat(contratId);
    }

    @DeleteMapping("/paiements/{id}")
    public void deletePaiement(@PathVariable Long id) {
        assuranceService.deletePaiement(id);
    }
}

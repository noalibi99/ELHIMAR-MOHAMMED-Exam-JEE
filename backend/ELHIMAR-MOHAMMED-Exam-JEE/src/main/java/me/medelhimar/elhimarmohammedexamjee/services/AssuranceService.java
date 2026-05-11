package me.medelhimar.elhimarmohammedexamjee.services;

import me.medelhimar.elhimarmohammedexamjee.dtos.*;
import me.medelhimar.elhimarmohammedexamjee.enums.ContractStatus;

import java.util.List;

public interface AssuranceService {

    // ─── Client ─────────────────────────────────────────────────────────────

    ClientDTO saveClient(ClientDTO clientDTO);
    ClientDTO getClient(Long id);
    ClientDTO updateClient(Long id, ClientDTO clientDTO);
    void deleteClient(Long id);
    List<ClientDTO> getAllClients();
    List<ContratDTO> getContratsByClient(Long clientId);

    // ─── Contrat ─────────────────────────────────────────────────────────────

    ContratAutomobileDTO saveContratAutomobile(Long clientId, ContratAutomobileDTO dto);
    ContratHabitationDTO saveContratHabitation(Long clientId, ContratHabitationDTO dto);
    ContratSanteDTO saveContratSante(Long clientId, ContratSanteDTO dto);

    ContratDTO getContrat(Long id);
    List<ContratDTO> getAllContrats();
    List<ContratDTO> getContratsByStatus(ContractStatus status);

    ContratDTO validerContrat(Long id);
    ContratDTO resilierContrat(Long id);

    void deleteContrat(Long id);

    // ─── Paiement ────────────────────────────────────────────────────────────

    PaiementDTO savePaiement(Long contratId, PaiementDTO dto);
    PaiementDTO getPaiement(Long id);
    List<PaiementDTO> getPaiementsByContrat(Long contratId);
    double getTotalPaiementsByContrat(Long contratId);
    void deletePaiement(Long id);
}

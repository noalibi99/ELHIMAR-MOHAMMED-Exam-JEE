package me.medelhimar.elhimarmohammedexamjee.services;

import me.medelhimar.elhimarmohammedexamjee.dtos.*;
import me.medelhimar.elhimarmohammedexamjee.enums.ContractStatus;

import java.util.List;

public class AssuranceServiceImpl implements AssuranceService {

    @Override
    public ClientDTO saveClient(ClientDTO clientDTO) {
        return null;
    }

    @Override
    public ClientDTO getClient(Long id) {
        return null;
    }

    @Override
    public ClientDTO updateClient(Long id, ClientDTO clientDTO) {
        return null;
    }

    @Override
    public void deleteClient(Long id) {

    }

    @Override
    public List<ClientDTO> getAllClients() {
        return List.of();
    }

    @Override
    public List<ContratDTO> getContratsByClient(Long clientId) {
        return List.of();
    }

    @Override
    public ContratAutomobileDTO saveContratAutomobile(Long clientId, ContratAutomobileDTO dto) {
        return null;
    }

    @Override
    public ContratHabitationDTO saveContratHabitation(Long clientId, ContratHabitationDTO dto) {
        return null;
    }

    @Override
    public ContratSanteDTO saveContratSante(Long clientId, ContratSanteDTO dto) {
        return null;
    }

    @Override
    public ContratDTO getContrat(Long id) {
        return null;
    }

    @Override
    public List<ContratDTO> getAllContrats() {
        return List.of();
    }

    @Override
    public List<ContratDTO> getContratsByStatus(ContractStatus status) {
        return List.of();
    }

    @Override
    public ContratDTO validerContrat(Long id) {
        return null;
    }

    @Override
    public ContratDTO resilierContrat(Long id) {
        return null;
    }

    @Override
    public void deleteContrat(Long id) {

    }

    @Override
    public PaiementDTO savePaiement(Long contratId, PaiementDTO dto) {
        return null;
    }

    @Override
    public PaiementDTO getPaiement(Long id) {
        return null;
    }

    @Override
    public List<PaiementDTO> getPaiementsByContrat(Long contratId) {
        return List.of();
    }

    @Override
    public double getTotalPaiementsByContrat(Long contratId) {
        return 0;
    }

    @Override
    public void deletePaiement(Long id) {

    }
}

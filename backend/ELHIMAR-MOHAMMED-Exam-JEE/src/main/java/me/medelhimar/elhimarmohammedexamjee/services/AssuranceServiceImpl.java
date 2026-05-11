package me.medelhimar.elhimarmohammedexamjee.services;

import me.medelhimar.elhimarmohammedexamjee.dtos.*;
import me.medelhimar.elhimarmohammedexamjee.entites.Client;
import me.medelhimar.elhimarmohammedexamjee.entites.Contrat;
import me.medelhimar.elhimarmohammedexamjee.entites.ContratAutomobile;
import me.medelhimar.elhimarmohammedexamjee.entites.ContratHabitation;
import me.medelhimar.elhimarmohammedexamjee.entites.ContratSante;
import me.medelhimar.elhimarmohammedexamjee.entites.Paiement;
import me.medelhimar.elhimarmohammedexamjee.enums.ContractStatus;
import me.medelhimar.elhimarmohammedexamjee.mappers.AssuranceMapperImpl;
import me.medelhimar.elhimarmohammedexamjee.repositories.ClientRepository;
import me.medelhimar.elhimarmohammedexamjee.repositories.ContratRepository;
import me.medelhimar.elhimarmohammedexamjee.repositories.PaiementRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class AssuranceServiceImpl implements AssuranceService {

    private final ClientRepository clientRepository;
    private final ContratRepository contratRepository;
    private final PaiementRepository paiementRepository;
    private final AssuranceMapperImpl mapper;

    public AssuranceServiceImpl(
            ClientRepository clientRepository,
            ContratRepository contratRepository,
            PaiementRepository paiementRepository,
            AssuranceMapperImpl mapper
    ) {
        this.clientRepository = clientRepository;
        this.contratRepository = contratRepository;
        this.paiementRepository = paiementRepository;
        this.mapper = mapper;
    }

    @Override
    public ClientDTO saveClient(ClientDTO clientDTO) {
        Client client = mapper.fromClientDTO(clientDTO);
        Client saved = clientRepository.save(client);
        return mapper.fromClient(saved);
    }

    @Override
    public ClientDTO getClient(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found"));
        return mapper.fromClient(client);
    }

    @Override
    public ClientDTO updateClient(Long id, ClientDTO clientDTO) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found"));
        client.setName(clientDTO.getName());
        client.setEmail(clientDTO.getEmail());
        Client saved = clientRepository.save(client);
        return mapper.fromClient(saved);
    }

    @Override
    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }

    @Override
    public List<ClientDTO> getAllClients() {
        return clientRepository.findAll().stream()
                .map(mapper::fromClient)
                .toList();
    }

    @Override
    public List<ContratDTO> getContratsByClient(Long clientId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found"));
        if (client.getContrats() == null) {
            return List.of();
        }
        return client.getContrats().stream()
                .map(mapper::fromContrat)
                .toList();
    }

    @Override
    public ContratAutomobileDTO saveContratAutomobile(Long clientId, ContratAutomobileDTO dto) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found"));
        var contrat = mapper.fromContratAutomobileDTO(dto);
        contrat.setClient(client);
        Contrat saved = contratRepository.save(contrat);
        return mapper.fromContratAutomobile((ContratAutomobile) saved);
    }

    @Override
    public ContratHabitationDTO saveContratHabitation(Long clientId, ContratHabitationDTO dto) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found"));
        var contrat = mapper.fromContratHabitationDTO(dto);
        contrat.setClient(client);
        Contrat saved = contratRepository.save(contrat);
        return mapper.fromContratHabitation((ContratHabitation) saved);
    }

    @Override
    public ContratSanteDTO saveContratSante(Long clientId, ContratSanteDTO dto) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found"));
        var contrat = mapper.fromContratSanteDTO(dto);
        contrat.setClient(client);
        Contrat saved = contratRepository.save(contrat);
        return mapper.fromContratSante((ContratSante) saved);
    }

    @Override
    public ContratDTO getContrat(Long id) {
        Contrat contrat = contratRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contrat not found"));
        return mapper.fromContrat(contrat);
    }

    @Override
    public List<ContratDTO> getAllContrats() {
        return contratRepository.findAll().stream()
                .map(mapper::fromContrat)
                .toList();
    }

    @Override
    public List<ContratDTO> getContratsByStatus(ContractStatus status) {
        return contratRepository.findAll().stream()
                .filter(contrat -> contrat.getStatus() == status)
                .map(mapper::fromContrat)
                .toList();
    }

    @Override
    public ContratDTO validerContrat(Long id) {
        Contrat contrat = contratRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contrat not found"));
        contrat.setStatus(ContractStatus.VALIDATED);
        contrat.setDateValidation(new Date());
        Contrat saved = contratRepository.save(contrat);
        return mapper.fromContrat(saved);
    }

    @Override
    public ContratDTO resilierContrat(Long id) {
        Contrat contrat = contratRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contrat not found"));
        contrat.setStatus(ContractStatus.CANCELED);
        Contrat saved = contratRepository.save(contrat);
        return mapper.fromContrat(saved);
    }

    @Override
    public void deleteContrat(Long id) {
        contratRepository.deleteById(id);
    }

    @Override
    public PaiementDTO savePaiement(Long contratId, PaiementDTO dto) {
        Contrat contrat = contratRepository.findById(contratId)
                .orElseThrow(() -> new RuntimeException("Contrat not found"));
        Paiement paiement = mapper.fromPaiementDTO(dto);
        paiement.setContrat(contrat);
        Paiement saved = paiementRepository.save(paiement);
        return mapper.fromPaiement(saved);
    }

    @Override
    public PaiementDTO getPaiement(Long id) {
        Paiement paiement = paiementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paiement not found"));
        return mapper.fromPaiement(paiement);
    }

    @Override
    public List<PaiementDTO> getPaiementsByContrat(Long contratId) {
        return paiementRepository.findAll().stream()
            .filter(paiement -> paiement.getContrat() != null
                && paiement.getContrat().getId() != null
                && paiement.getContrat().getId().equals(contratId))
            .map(mapper::fromPaiement)
            .toList();
    }

    @Override
    public double getTotalPaiementsByContrat(Long contratId) {
        return getPaiementsByContrat(contratId).stream()
                .mapToDouble(PaiementDTO::getMontant)
                .sum();
    }

    @Override
    public void deletePaiement(Long id) {
        paiementRepository.deleteById(id);
    }
}

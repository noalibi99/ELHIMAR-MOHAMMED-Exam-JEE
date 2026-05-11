package me.medelhimar.elhimarmohammedexamjee.mappers;

import me.medelhimar.elhimarmohammedexamjee.dtos.*;
import me.medelhimar.elhimarmohammedexamjee.entites.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class AssuranceMapperImpl {

    public ClientDTO fromClient(Client client) {
        ClientDTO clientDTO = new ClientDTO();
        BeanUtils.copyProperties(client, clientDTO);
        return clientDTO;
    }

    public Client fromClientDTO(ClientDTO clientDTO) {
        Client client = new Client();
        BeanUtils.copyProperties(clientDTO, client);
        return client;
    }


    public ContratAutomobileDTO fromContratAutomobile(ContratAutomobile contrat) {
        ContratAutomobileDTO dto = new ContratAutomobileDTO();
        BeanUtils.copyProperties(contrat, dto);
        if (contrat.getClient() != null) {
            dto.setClient(fromClient(contrat.getClient()));
        }
        dto.setType(contrat.getClass().getSimpleName());
        return dto;
    }

    public ContratAutomobile fromContratAutomobileDTO(ContratAutomobileDTO dto) {
        ContratAutomobile contrat = new ContratAutomobile();
        BeanUtils.copyProperties(dto, contrat, "client");
        if (dto.getClient() != null) {
            contrat.setClient(fromClientDTO(dto.getClient()));
        }
        return contrat;
    }


    public ContratHabitationDTO fromContratHabitation(ContratHabitation contrat) {
        ContratHabitationDTO dto = new ContratHabitationDTO();
        BeanUtils.copyProperties(contrat, dto);
        if (contrat.getClient() != null) {
            dto.setClient(fromClient(contrat.getClient()));
        }
        dto.setType(contrat.getClass().getSimpleName());
        return dto;
    }

    public ContratHabitation fromContratHabitationDTO(ContratHabitationDTO dto) {
        ContratHabitation contrat = new ContratHabitation();
        BeanUtils.copyProperties(dto, contrat, "client");
        if (dto.getClient() != null) {
            contrat.setClient(fromClientDTO(dto.getClient()));
        }
        return contrat;
    }

    public ContratSanteDTO fromContratSante(ContratSante contrat) {
        ContratSanteDTO dto = new ContratSanteDTO();
        BeanUtils.copyProperties(contrat, dto);
        if (contrat.getClient() != null) {
            dto.setClient(fromClient(contrat.getClient()));
        }
        dto.setType(contrat.getClass().getSimpleName());
        return dto;
    }

    public ContratSante fromContratSanteDTO(ContratSanteDTO dto) {
        ContratSante contrat = new ContratSante();
        BeanUtils.copyProperties(dto, contrat, "client");
        if (dto.getClient() != null) {
            contrat.setClient(fromClientDTO(dto.getClient()));
        }
        return contrat;
    }


    public ContratDTO fromContrat(Contrat contrat) {
        if (contrat instanceof ContratAutomobile a)
            return fromContratAutomobile(a);
        else if (contrat instanceof ContratHabitation h)
            return fromContratHabitation(h);
        else if (contrat instanceof ContratSante s)
            return fromContratSante(s);
        else
            throw new IllegalArgumentException("Unknown contrat type: " + contrat.getClass());
    }

    public Contrat fromContratDTO(ContratDTO dto) {
        if (dto instanceof ContratAutomobileDTO a)
            return fromContratAutomobileDTO(a);
        else if (dto instanceof ContratHabitationDTO h)
            return fromContratHabitationDTO(h);
        else if (dto instanceof ContratSanteDTO s)
            return fromContratSanteDTO(s);
        else
            throw new IllegalArgumentException("Unknown contrat DTO type: " + dto.getClass());
    }


    public PaiementDTO fromPaiement(Paiement paiement) {
        PaiementDTO dto = new PaiementDTO();
        BeanUtils.copyProperties(paiement, dto);
        if (paiement.getContrat() != null) {
            dto.setContratDTO(fromContrat(paiement.getContrat()));
        }
        return dto;
    }

    public Paiement fromPaiementDTO(PaiementDTO dto) {
        Paiement paiement = new Paiement();
        BeanUtils.copyProperties(dto, paiement);
        if (dto.getContratDTO() != null) {
            paiement.setContrat(fromContratDTO(dto.getContratDTO()));
        }
        return paiement;
    }
}

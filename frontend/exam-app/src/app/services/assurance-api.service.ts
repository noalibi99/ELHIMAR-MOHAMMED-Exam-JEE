import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

export type ContractStatus = 'IN_PROGRESS' | 'VALIDATED' | 'CANCELED';

export interface ClientDTO {
  id?: number;
  name?: string;
  email?: string;
}

export interface ContratAutomobileDTO extends ContratDTO {
  numeroImmatriculation?: string;
  marqueVehicule?: string;
  modeleVehicule?: string;
}

export interface ContratHabitationDTO extends ContratDTO {
  typeLogement?: 'APPARTEMENT' | 'MAISON' | 'LOCAL_COMMERCIAL';
  adresseLogement?: string;
  superficie?: number;
}

export interface ContratSanteDTO extends ContratDTO {
  niveauCouverture?: 'BASIQUE' | 'INTERMEDIAIRE' | 'PREMIUM';
  nbrPersonnesCouvertes?: number;
}

export interface ContratDTO {
  id?: number;
  dateSouscription?: string;
  status?: ContractStatus;
  dateValidation?: string;
  montantCotisation?: number;
  duree?: number;
  tauxCouverture?: number;
  type?: string;
  client?: ClientDTO;
}

export interface PaiementDTO {
  id?: number;
  datePaiement?: string;
  montant?: number;
  typePaiement?: string;
  contratDTO?: ContratDTO;
}

@Injectable({
  providedIn: 'root'
})
export class AssuranceApiService {
  private readonly baseUrl = 'http://localhost:8085/api';

  constructor(private http: HttpClient) {}

  getClients(): Observable<ClientDTO[]> {
    return this.http.get<ClientDTO[]>(`${this.baseUrl}/clients`);
  }

  createClient(payload: ClientDTO): Observable<ClientDTO> {
    return this.http.post<ClientDTO>(`${this.baseUrl}/clients`, payload);
  }

  getContrats(): Observable<ContratDTO[]> {
    return this.http.get<ContratDTO[]>(`${this.baseUrl}/contrats`);
  }

  getContratsByStatus(status: ContractStatus): Observable<ContratDTO[]> {
    return this.http.get<ContratDTO[]>(`${this.baseUrl}/contrats/status/${status}`);
  }

  createContratAutomobile(clientId: number, payload: ContratAutomobileDTO): Observable<ContratAutomobileDTO> {
    return this.http.post<ContratAutomobileDTO>(
      `${this.baseUrl}/clients/${clientId}/contrats/automobile`,
      payload
    );
  }

  createContratHabitation(clientId: number, payload: ContratHabitationDTO): Observable<ContratHabitationDTO> {
    return this.http.post<ContratHabitationDTO>(
      `${this.baseUrl}/clients/${clientId}/contrats/habitation`,
      payload
    );
  }

  createContratSante(clientId: number, payload: ContratSanteDTO): Observable<ContratSanteDTO> {
    return this.http.post<ContratSanteDTO>(
      `${this.baseUrl}/clients/${clientId}/contrats/sante`,
      payload
    );
  }

  validateContrat(id: number): Observable<ContratDTO> {
    return this.http.put<ContratDTO>(`${this.baseUrl}/contrats/${id}/valider`, {});
  }

  cancelContrat(id: number): Observable<ContratDTO> {
    return this.http.put<ContratDTO>(`${this.baseUrl}/contrats/${id}/resilier`, {});
  }

  getPaiementsByContrat(contratId: number): Observable<PaiementDTO[]> {
    return this.http.get<PaiementDTO[]>(`${this.baseUrl}/contrats/${contratId}/paiements`);
  }

  getTotalPaiementsByContrat(contratId: number): Observable<number> {
    return this.http.get<number>(`${this.baseUrl}/contrats/${contratId}/paiements/total`);
  }
}

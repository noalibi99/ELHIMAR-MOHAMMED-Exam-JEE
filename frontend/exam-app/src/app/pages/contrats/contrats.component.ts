import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import {
  AssuranceApiService,
  ContractStatus,
  ContratAutomobileDTO,
  ContratDTO,
  ContratHabitationDTO,
  ContratSanteDTO
} from '../../services/assurance-api.service';

@Component({
  selector: 'app-contrats',
  templateUrl: './contrats.component.html',
  styleUrl: './contrats.component.css',
  standalone: false
})
export class ContratsComponent implements OnInit {
  contrats: ContratDTO[] = [];
  loading = true;
  errorMessage = '';
  successMessage = '';
  selectedStatus: ContractStatus | 'ALL' = 'ALL';
  readonly statuses: ContractStatus[] = ['IN_PROGRESS', 'VALIDATED', 'CANCELED'];
  readonly contractTypes = ['AUTOMOBILE', 'HABITATION', 'SANTE'] as const;
  readonly logementTypes: ContratHabitationDTO['typeLogement'][] = ['APPARTEMENT', 'MAISON', 'LOCAL_COMMERCIAL'];
  readonly couvertureLevels: ContratSanteDTO['niveauCouverture'][] = ['BASIQUE', 'INTERMEDIAIRE', 'PREMIUM'];
  newContract: {
    clientId?: number;
    type: 'AUTOMOBILE' | 'HABITATION' | 'SANTE';
    montantCotisation?: number;
    duree?: number;
    tauxCouverture?: number;
    numeroImmatriculation?: string;
    marqueVehicule?: string;
    modeleVehicule?: string;
    typeLogement?: ContratHabitationDTO['typeLogement'];
    adresseLogement?: string;
    superficie?: number;
    niveauCouverture?: ContratSanteDTO['niveauCouverture'];
    nbrPersonnesCouvertes?: number;
  } = {
    type: 'AUTOMOBILE'
  };

  constructor(private api: AssuranceApiService) {}

  ngOnInit(): void {
    this.loadContrats();
  }

  loadContrats(): void {
    this.loading = true;
    this.errorMessage = '';
    const request = this.selectedStatus === 'ALL'
      ? this.api.getContrats()
      : this.api.getContratsByStatus(this.selectedStatus);

    request.subscribe({
      next: (contrats) => {
        this.contrats = contrats;
        this.loading = false;
      },
      error: () => {
        this.errorMessage = 'Unable to load contracts.';
        this.loading = false;
      }
    });
  }

  submitContract(): void {
    this.errorMessage = '';
    this.successMessage = '';
    if (!this.newContract.clientId) {
      this.errorMessage = 'Client ID is required.';
      return;
    }

    const basePayload = {
      montantCotisation: this.newContract.montantCotisation,
      duree: this.newContract.duree,
      tauxCouverture: this.newContract.tauxCouverture
    };

    let request: Observable<unknown>;
    if (this.newContract.type === 'AUTOMOBILE') {
      const payload: ContratAutomobileDTO = {
        ...basePayload,
        numeroImmatriculation: this.newContract.numeroImmatriculation,
        marqueVehicule: this.newContract.marqueVehicule,
        modeleVehicule: this.newContract.modeleVehicule
      };
      request = this.api.createContratAutomobile(this.newContract.clientId, payload);
    } else if (this.newContract.type === 'HABITATION') {
      const payload: ContratHabitationDTO = {
        ...basePayload,
        typeLogement: this.newContract.typeLogement,
        adresseLogement: this.newContract.adresseLogement,
        superficie: this.newContract.superficie
      };
      request = this.api.createContratHabitation(this.newContract.clientId, payload);
    } else {
      const payload: ContratSanteDTO = {
        ...basePayload,
        niveauCouverture: this.newContract.niveauCouverture,
        nbrPersonnesCouvertes: this.newContract.nbrPersonnesCouvertes
      };
      request = this.api.createContratSante(this.newContract.clientId, payload);
    }

    request.subscribe({
      next: () => {
        this.successMessage = 'Contract created successfully.';
        this.resetContractForm();
        this.loadContrats();
      },
      error: () => {
        this.errorMessage = 'Unable to create contract.';
      }
    });
  }

  private resetContractForm(): void {
    this.newContract = {
      type: 'AUTOMOBILE'
    };
  }

  updateStatusFilter(): void {
    this.loadContrats();
  }

  validateContrat(id?: number): void {
    if (!id) {
      return;
    }
    this.api.validateContrat(id).subscribe({
      next: () => this.loadContrats(),
      error: () => {
        this.errorMessage = 'Unable to validate contract.';
      }
    });
  }

  cancelContrat(id?: number): void {
    if (!id) {
      return;
    }
    this.api.cancelContrat(id).subscribe({
      next: () => this.loadContrats(),
      error: () => {
        this.errorMessage = 'Unable to cancel contract.';
      }
    });
  }

  statusBadge(status?: ContractStatus): string {
    switch (status) {
      case 'VALIDATED':
        return 'text-bg-success';
      case 'CANCELED':
        return 'text-bg-danger';
      default:
        return 'text-bg-info';
    }
  }
}

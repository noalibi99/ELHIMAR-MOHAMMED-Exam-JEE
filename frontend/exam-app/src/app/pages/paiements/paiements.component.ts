import { Component } from '@angular/core';
import { AssuranceApiService, PaiementDTO } from '../../services/assurance-api.service';

@Component({
  selector: 'app-paiements',
  templateUrl: './paiements.component.html',
  styleUrl: './paiements.component.css',
  standalone: false
})
export class PaiementsComponent {
  contratId?: number;
  paiements: PaiementDTO[] = [];
  total?: number;
  loading = false;
  errorMessage = '';

  constructor(private api: AssuranceApiService) {}

  loadPaiements(): void {
    if (!this.contratId) {
      this.errorMessage = 'Please enter a contract ID.';
      return;
    }

    this.errorMessage = '';
    this.loading = true;
    this.api.getPaiementsByContrat(this.contratId).subscribe({
      next: (paiements) => {
        this.paiements = paiements;
        this.loading = false;
      },
      error: () => {
        this.errorMessage = 'Unable to load payments.';
        this.loading = false;
      }
    });

    this.api.getTotalPaiementsByContrat(this.contratId).subscribe({
      next: (total) => {
        this.total = total;
      },
      error: () => {
        this.total = undefined;
      }
    });
  }
}

import { Component, OnInit } from '@angular/core';
import { forkJoin } from 'rxjs';
import { AssuranceApiService, ContractStatus, ContratDTO } from '../../services/assurance-api.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css',
  standalone: false
})
export class DashboardComponent implements OnInit {
  clientsCount = 0;
  contratsCount = 0;
  statusCounts: Record<ContractStatus, number> = {
    IN_PROGRESS: 0,
    VALIDATED: 0,
    CANCELED: 0
  };
  loading = true;
  errorMessage = '';

  constructor(private api: AssuranceApiService) {}

  ngOnInit(): void {
    this.loading = true;
    forkJoin({
      clients: this.api.getClients(),
      contrats: this.api.getContrats()
    }).subscribe({
      next: ({ clients, contrats }) => {
        this.clientsCount = clients.length;
        this.contratsCount = contrats.length;
        this.statusCounts = this.buildStatusCounts(contrats);
        this.loading = false;
      },
      error: () => {
        this.errorMessage = 'Unable to load dashboard data.';
        this.loading = false;
      }
    });
  }

  private buildStatusCounts(contrats: ContratDTO[]): Record<ContractStatus, number> {
    return contrats.reduce((acc, contrat) => {
      if (contrat.status) {
        acc[contrat.status] = (acc[contrat.status] || 0) + 1;
      }
      return acc;
    }, { IN_PROGRESS: 0, VALIDATED: 0, CANCELED: 0 } as Record<ContractStatus, number>);
  }
}

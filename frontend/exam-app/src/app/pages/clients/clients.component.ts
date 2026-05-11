import { Component, OnInit } from '@angular/core';
import { AssuranceApiService, ClientDTO } from '../../services/assurance-api.service';

@Component({
  selector: 'app-clients',
  templateUrl: './clients.component.html',
  styleUrl: './clients.component.css',
  standalone: false
})
export class ClientsComponent implements OnInit {
  clients: ClientDTO[] = [];
  loading = true;
  errorMessage = '';
  successMessage = '';
  newClient: ClientDTO = {
    name: '',
    email: ''
  };

  constructor(private api: AssuranceApiService) {}

  ngOnInit(): void {
    this.loadClients();
  }

  trackById(_: number, client: ClientDTO): number | undefined {
    return client.id;
  }

  submitClient(): void {
    this.errorMessage = '';
    this.successMessage = '';
    if (!this.newClient.name || !this.newClient.email) {
      this.errorMessage = 'Name and email are required.';
      return;
    }

    this.api.createClient(this.newClient).subscribe({
      next: () => {
        this.successMessage = 'Client created successfully.';
        this.newClient = { name: '', email: '' };
        this.loadClients();
      },
      error: () => {
        this.errorMessage = 'Unable to create client.';
      }
    });
  }

  private loadClients(): void {
    this.loading = true;
    this.errorMessage = '';
    this.api.getClients().subscribe({
      next: (clients) => {
        this.clients = clients;
        this.loading = false;
      },
      error: () => {
        this.errorMessage = 'Unable to load clients.';
        this.loading = false;
      }
    });
  }
}

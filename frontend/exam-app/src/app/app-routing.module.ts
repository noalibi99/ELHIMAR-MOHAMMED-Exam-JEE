import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ClientsComponent } from './pages/clients/clients.component';
import { ContratsComponent } from './pages/contrats/contrats.component';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { PaiementsComponent } from './pages/paiements/paiements.component';

const routes: Routes = [
  { path: '', redirectTo: 'dashboard', pathMatch: 'full' },
  { path: 'dashboard', component: DashboardComponent },
  { path: 'clients', component: ClientsComponent },
  { path: 'contrats', component: ContratsComponent },
  { path: 'paiements', component: PaiementsComponent },
  { path: '**', redirectTo: 'dashboard' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

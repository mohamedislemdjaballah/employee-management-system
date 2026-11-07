import { Routes } from '@angular/router';
import { EmployeeListComponent } from './components/employee-list/employee-list.component';
import { EmployeeFormComponent } from './components/employee-form/employee-form.component';
import { LoginDemoComponent } from './components/login-demo/login-demo.component';

/**
 * Définition des routes de l'application Angular.
 *
 * Équivalent Laravel : routes/web.php
 * Notez que le même composant (EmployeeFormComponent) est réutilisé
 * pour la création (/employees/new) et la modification (/employees/:id/edit).
 */
export const routes: Routes = [
  { path: '', redirectTo: 'employees', pathMatch: 'full' },
  { path: 'employees', component: EmployeeListComponent, title: 'Liste des Agents - SIT' },
  { path: 'employees/new', component: EmployeeFormComponent, title: 'Nouvel Agent - SIT' },
  { path: 'employees/:id/edit', component: EmployeeFormComponent, title: 'Modifier Agent - SIT' },
  { path: 'login-demo', component: LoginDemoComponent, title: 'Atelier Sécurité (Jour 5) - SIT' },
  { path: '**', redirectTo: 'employees' }
];

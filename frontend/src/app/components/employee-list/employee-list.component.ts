import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';

import { Employee, EmployeeStatus } from '../../models/employee.model';
import { Department } from '../../models/department.model';
import { EmployeeService } from '../../services/employee.service';
import { DepartmentService } from '../../services/department.service';

/**
 * Composant principal : Liste, Recherche, Filtrage et Suppression des Agents.
 *
 * Concepts Angular enseignés au Jour 3 & Jour 4 :
 * 1. Cycle de vie ngOnInit() : déclenché automatiquement à l'affichage du composant.
 * 2. Injection de dépendances avec inject(EmployeeService).
 * 3. Liaison de données bidirectionnelle [(ngModel)] sur les champs de filtre.
 * 4. Abonnement asynchrone .subscribe({ next, error }) sur les appels HTTP.
 */
@Component({
  selector: 'app-employee-list',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterLink],
  templateUrl: './employee-list.component.html',
  styleUrl: './employee-list.component.css'
})
export class EmployeeListComponent implements OnInit {
  readonly employeeService = inject(EmployeeService);
  private readonly departmentService = inject(DepartmentService);

  employees: Employee[] = [];
  departments: Department[] = [];

  // Critères de recherche et filtrage
  searchTerm = '';
  selectedDepartmentId: number | null = null;
  selectedStatus: EmployeeStatus | '' = '';

  // États de chargement et d'erreur
  isLoading = false;
  errorMessage = '';

  ngOnInit(): void {
    this.loadDepartments();
    this.loadEmployees();
  }

  loadDepartments(): void {
    this.departmentService.getAllDepartments().subscribe({
      next: (data) => {
        this.departments = data;
      }
    });
  }

  loadEmployees(): void {
    this.isLoading = true;
    this.errorMessage = '';

    this.employeeService
      .getEmployees({
        departmentId: this.selectedDepartmentId,
        status: this.selectedStatus,
        search: this.searchTerm
      })
      .subscribe({
        next: (data) => {
          this.employees = data;
          this.isLoading = false;
        },
        error: () => {
          this.errorMessage =
            "Impossible de récupérer la liste des agents. Vérifiez que l'API Spring Boot est démarrée sur le port 8080.";
          this.isLoading = false;
        }
      });
  }

  onFilterChange(): void {
    this.loadEmployees();
  }

  resetFilters(): void {
    this.searchTerm = '';
    this.selectedDepartmentId = null;
    this.selectedStatus = '';
    this.loadEmployees();
  }

  confirmDelete(employee: Employee): void {
    const confirmed = window.confirm(
      `Confirmez-vous la suppression de l'agent ${employee.firstName} ${employee.lastName} (${employee.matricule}) ?`
    );
    if (!confirmed) {
      return;
    }

    this.employeeService.deleteEmployee(employee.id).subscribe({
      next: () => {
        this.loadEmployees();
      },
      error: () => {
        this.errorMessage = `Erreur lors de la suppression de l'agent ${employee.matricule}.`;
      }
    });
  }

  // Indicateurs KPI calculés dynamiquement à partir de la liste affichée
  get totalEmployees(): number {
    return this.employees.length;
  }

  get activeEmployeesCount(): number {
    return this.employees.filter((e) => e.status === 'ACTIVE').length;
  }

  get onLeaveEmployeesCount(): number {
    return this.employees.filter((e) => e.status === 'ON_LEAVE').length;
  }

  get totalMonthlyPayroll(): number {
    return this.employees.reduce((sum, emp) => sum + Number(emp.salary || 0), 0);
  }

  getStatusLabel(status: EmployeeStatus): string {
    switch (status) {
      case 'ACTIVE':
        return 'En Activité';
      case 'ON_LEAVE':
        return 'En Congé';
      case 'INACTIVE':
        return 'Inactif';
    }
  }

  getStatusBadgeClass(status: EmployeeStatus): string {
    switch (status) {
      case 'ACTIVE':
        return 'badge badge-active';
      case 'ON_LEAVE':
        return 'badge badge-leave';
      case 'INACTIVE':
        return 'badge badge-inactive';
    }
  }
}

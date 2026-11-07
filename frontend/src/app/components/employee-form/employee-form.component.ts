import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';

import { Department } from '../../models/department.model';
import { EmployeeRequest } from '../../models/employee.model';
import { DepartmentService } from '../../services/department.service';
import { EmployeeService } from '../../services/employee.service';

/**
 * Composant Formulaire Réactif (Reactive Form) réutilisable pour :
 * - La création d'un agent (/employees/new)
 * - La modification d'un agent (/employees/:id/edit)
 *
 * Équivalent Laravel :
 * - FormBuilder / Validators -> FormRequest rules()
 * - ActivatedRoute           -> Route Parameter ($id) dans le contrôleur
 * - Router.navigate()        -> redirect()->route('employees.index')
 */
@Component({
  selector: 'app-employee-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterLink],
  templateUrl: './employee-form.component.html',
  styleUrl: './employee-form.component.css'
})
export class EmployeeFormComponent implements OnInit {
  private readonly fb = inject(FormBuilder);
  private readonly employeeService = inject(EmployeeService);
  private readonly departmentService = inject(DepartmentService);
  private readonly route = inject(ActivatedRoute);
  private readonly router = inject(Router);

  employeeForm!: FormGroup;
  departments: Department[] = [];

  isEditMode = false;
  employeeId: number | null = null;
  isSubmitting = false;
  serverErrorMessage = '';

  ngOnInit(): void {
    this.initForm();
    this.loadDepartments();

    // Vérifier si l'URL contient un paramètre :id (Mode Édition)
    const idParam = this.route.snapshot.paramMap.get('id');
    if (idParam) {
      this.isEditMode = true;
      this.employeeId = Number(idParam);
      this.loadEmployeeForEdit(this.employeeId);
    }
  }

  private initForm(): void {
    const today = new Date().toISOString().split('T')[0];

    this.employeeForm = this.fb.group({
      matricule: ['FIN-2026-007', [Validators.required, Validators.maxLength(30)]],
      firstName: ['', [Validators.required, Validators.maxLength(80)]],
      lastName: ['', [Validators.required, Validators.maxLength(80)]],
      email: ['', [Validators.required, Validators.email]],
      phone: [''],
      jobTitle: ['', [Validators.required, Validators.maxLength(120)]],
      salary: [95000, [Validators.required, Validators.min(0)]],
      hireDate: [today, [Validators.required]],
      status: ['ACTIVE', [Validators.required]],
      departmentId: [null, [Validators.required]]
    });
  }

  private loadDepartments(): void {
    this.departmentService.getAllDepartments().subscribe({
      next: (data) => {
        this.departments = data;
        // Sélectionner la première direction par défaut en mode création
        if (!this.isEditMode && data.length > 0 && !this.employeeForm.get('departmentId')?.value) {
          this.employeeForm.patchValue({ departmentId: data[0].id });
        }
      }
    });
  }

  private loadEmployeeForEdit(id: number): void {
    this.employeeService.getEmployeeById(id).subscribe({
      next: (emp) => {
        this.employeeForm.patchValue({
          matricule: emp.matricule,
          firstName: emp.firstName,
          lastName: emp.lastName,
          email: emp.email,
          phone: emp.phone,
          jobTitle: emp.jobTitle,
          salary: emp.salary,
          hireDate: emp.hireDate,
          status: emp.status,
          departmentId: emp.departmentId
        });
      },
      error: () => {
        this.serverErrorMessage = `Agent introuvable avec l'identifiant #${id}.`;
      }
    });
  }

  /**
   * Méthode utilitaire pour vérifier si un champ est invalide après avoir été touché.
   */
  isFieldInvalid(fieldName: string): boolean {
    const control = this.employeeForm.get(fieldName);
    return !!(control && control.invalid && (control.dirty || control.touched));
  }

  onSubmit(): void {
    if (this.employeeForm.invalid) {
      this.employeeForm.markAllAsTouched();
      return;
    }

    this.isSubmitting = true;
    this.serverErrorMessage = '';

    const raw = this.employeeForm.value;
    const payload: EmployeeRequest = {
      matricule: raw.matricule,
      firstName: raw.firstName,
      lastName: raw.lastName,
      email: raw.email,
      phone: raw.phone ?? '',
      jobTitle: raw.jobTitle,
      salary: Number(raw.salary),
      hireDate: raw.hireDate,
      status: raw.status,
      departmentId: Number(raw.departmentId)
    };

    const request$ =
      this.isEditMode && this.employeeId
        ? this.employeeService.updateEmployee(this.employeeId, payload)
        : this.employeeService.createEmployee(payload);

    request$.subscribe({
      next: () => {
        this.isSubmitting = false;
        this.router.navigate(['/employees']);
      },
      error: (err) => {
        this.isSubmitting = false;
        this.serverErrorMessage =
          err?.error?.message ||
          "Une erreur est survenue lors de l'enregistrement. Vérifiez l'unicité du matricule et de l'email.";
      }
    });
  }
}

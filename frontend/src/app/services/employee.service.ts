import { Injectable, inject } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable, catchError, of, tap, throwError } from 'rxjs';
import { environment } from '../../environments/environment';
import { Employee, EmployeeRequest, EmployeeStatus } from '../models/employee.model';
import { DepartmentService } from './department.service';

/**
 * Service Angular gérant toutes les opérations CRUD sur les Employés via l'API REST Spring Boot.
 *
 * Points pédagogiques pour les stagiaires :
 * 1. Un composant UI ne doit JAMAIS appeler HttpClient directement : il passe par un @Injectable Service.
 * 2. Les méthodes retournent un Observable<T> (flux asynchrone). Le composant s'y abonne avec .subscribe().
 * 3. Astuce pédagogique : Si le serveur Spring Boot (port 8080) est arrêté pendant la préparation Frontend,
 *    ce service bascule automatiquement sur un jeu de données en mémoire et affiche un bandeau d'information.
 */
@Injectable({
  providedIn: 'root'
})
export class EmployeeService {
  private readonly http = inject(HttpClient);
  private readonly departmentService = inject(DepartmentService);
  private readonly apiUrl = `${environment.apiUrl}/employees`;

  // Indique à l'interface si les données proviennent de Spring Boot (false) ou du mode démo local (true)
  public isOfflineFallbackMode = false;

  private mockEmployees: Employee[] = [
    {
      id: 1,
      matricule: 'FIN-2026-001',
      firstName: 'Karim',
      lastName: 'BENALI',
      email: 'k.benali@finances.gov',
      phone: '0550 12 34 56',
      jobTitle: 'Architecte Logiciel Principal',
      salary: 145000,
      hireDate: '2018-03-15',
      status: 'ACTIVE',
      departmentId: 1,
      departmentCode: 'SIT',
      departmentName: "Système d'Information du Trésor"
    },
    {
      id: 2,
      matricule: 'FIN-2026-002',
      firstName: 'Amina',
      lastName: 'MANSOURI',
      email: 'a.mansouri@finances.gov',
      phone: '0551 23 45 67',
      jobTitle: "Ingénieur d'État en Informatique",
      salary: 118000,
      hireDate: '2020-09-01',
      status: 'ACTIVE',
      departmentId: 1,
      departmentCode: 'SIT',
      departmentName: "Système d'Information du Trésor"
    },
    {
      id: 3,
      matricule: 'FIN-2026-003',
      firstName: 'Yacine',
      lastName: 'TOUATI',
      email: 'y.touati@finances.gov',
      phone: '0552 34 56 78',
      jobTitle: 'Inspecteur Central du Trésor',
      salary: 132000,
      hireDate: '2016-01-10',
      status: 'ACTIVE',
      departmentId: 2,
      departmentCode: 'DGT',
      departmentName: 'Direction Générale du Trésor'
    },
    {
      id: 4,
      matricule: 'FIN-2026-004',
      firstName: 'Nadia',
      lastName: 'KHELIFI',
      email: 'n.khelifi@finances.gov',
      phone: '0553 45 67 89',
      jobTitle: 'Analyste Budgétaire Senior',
      salary: 112000,
      hireDate: '2019-06-20',
      status: 'ON_LEAVE',
      departmentId: 3,
      departmentCode: 'DB',
      departmentName: 'Direction du Budget'
    },
    {
      id: 5,
      matricule: 'FIN-2026-005',
      firstName: 'Mourad',
      lastName: 'HADJADJ',
      email: 'm.hadjadj@finances.gov',
      phone: '0554 56 78 90',
      jobTitle: 'Administrateur Base de Données PostgreSQL',
      salary: 125000,
      hireDate: '2021-02-14',
      status: 'ACTIVE',
      departmentId: 1,
      departmentCode: 'SIT',
      departmentName: "Système d'Information du Trésor"
    },
    {
      id: 6,
      matricule: 'FIN-2026-006',
      firstName: 'Samira',
      lastName: 'BOUZID',
      email: 's.bouzid@finances.gov',
      phone: '0555 67 89 01',
      jobTitle: 'Responsable Formation & Carrières',
      salary: 108000,
      hireDate: '2017-11-05',
      status: 'ACTIVE',
      departmentId: 4,
      departmentCode: 'DRH',
      departmentName: 'Direction des Ressources Humaines'
    }
  ];

  /**
   * Récupère la liste des employés avec filtres optionnels.
   * Appelle : GET http://localhost:8080/api/employees?departmentId=...&status=...&search=...
   */
  getEmployees(filters?: {
    departmentId?: number | null;
    status?: EmployeeStatus | '';
    search?: string;
  }): Observable<Employee[]> {
    let params = new HttpParams();
    if (filters?.departmentId) {
      params = params.set('departmentId', filters.departmentId.toString());
    }
    if (filters?.status) {
      params = params.set('status', filters.status);
    }
    if (filters?.search && filters.search.trim() !== '') {
      params = params.set('search', filters.search.trim());
    }

    return this.http.get<Employee[]>(this.apiUrl, { params }).pipe(
      tap(() => {
        this.isOfflineFallbackMode = false;
      }),
      catchError((err) => {
        if (err.status === 0) {
          this.isOfflineFallbackMode = true;
          return of(this.filterMockEmployees(filters));
        }
        return throwError(() => err);
      })
    );
  }

  /**
   * Récupère un employé par son ID.
   * Appelle : GET http://localhost:8080/api/employees/{id}
   */
  getEmployeeById(id: number): Observable<Employee> {
    return this.http.get<Employee>(`${this.apiUrl}/${id}`).pipe(
      catchError((err) => {
        if (err.status === 0) {
          this.isOfflineFallbackMode = true;
          const found = this.mockEmployees.find((e) => e.id === id);
          if (found) {
            return of({ ...found });
          }
        }
        return throwError(() => err);
      })
    );
  }

  /**
   * Crée un nouvel employé.
   * Appelle : POST http://localhost:8080/api/employees
   */
  createEmployee(payload: EmployeeRequest): Observable<Employee> {
    return this.http.post<Employee>(this.apiUrl, payload).pipe(
      catchError((err) => {
        if (err.status === 0) {
          this.isOfflineFallbackMode = true;
          const dept = this.departmentService
            .getFallbackDepartmentsSnapshot()
            .find((d) => d.id === Number(payload.departmentId));
          const created: Employee = {
            ...payload,
            id: Date.now(),
            matricule: payload.matricule.toUpperCase(),
            lastName: payload.lastName.toUpperCase(),
            departmentId: Number(payload.departmentId),
            departmentCode: dept?.code ?? 'SIT',
            departmentName: dept?.name ?? "Système d'Information du Trésor"
          };
          this.mockEmployees = [...this.mockEmployees, created];
          return of(created);
        }
        return throwError(() => err);
      })
    );
  }

  /**
   * Modifie un employé existant.
   * Appelle : PUT http://localhost:8080/api/employees/{id}
   */
  updateEmployee(id: number, payload: EmployeeRequest): Observable<Employee> {
    return this.http.put<Employee>(`${this.apiUrl}/${id}`, payload).pipe(
      catchError((err) => {
        if (err.status === 0) {
          this.isOfflineFallbackMode = true;
          const dept = this.departmentService
            .getFallbackDepartmentsSnapshot()
            .find((d) => d.id === Number(payload.departmentId));
          const updated: Employee = {
            ...payload,
            id,
            matricule: payload.matricule.toUpperCase(),
            lastName: payload.lastName.toUpperCase(),
            departmentId: Number(payload.departmentId),
            departmentCode: dept?.code ?? 'SIT',
            departmentName: dept?.name ?? "Système d'Information du Trésor"
          };
          this.mockEmployees = this.mockEmployees.map((e) => (e.id === id ? updated : e));
          return of(updated);
        }
        return throwError(() => err);
      })
    );
  }

  /**
   * Supprime un employé.
   * Appelle : DELETE http://localhost:8080/api/employees/{id}
   */
  deleteEmployee(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`).pipe(
      catchError((err) => {
        if (err.status === 0) {
          this.isOfflineFallbackMode = true;
          this.mockEmployees = this.mockEmployees.filter((e) => e.id !== id);
          return of(void 0);
        }
        return throwError(() => err);
      })
    );
  }

  private filterMockEmployees(filters?: {
    departmentId?: number | null;
    status?: EmployeeStatus | '';
    search?: string;
  }): Employee[] {
    return this.mockEmployees.filter((emp) => {
      if (filters?.departmentId && emp.departmentId !== Number(filters.departmentId)) {
        return false;
      }
      if (filters?.status && emp.status !== filters.status) {
        return false;
      }
      if (filters?.search && filters.search.trim() !== '') {
        const q = filters.search.trim().toLowerCase();
        const match =
          emp.firstName.toLowerCase().includes(q) ||
          emp.lastName.toLowerCase().includes(q) ||
          emp.matricule.toLowerCase().includes(q) ||
          emp.email.toLowerCase().includes(q) ||
          emp.jobTitle.toLowerCase().includes(q);
        if (!match) return false;
      }
      return true;
    });
  }
}

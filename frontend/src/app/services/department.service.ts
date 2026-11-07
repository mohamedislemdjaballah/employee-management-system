import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, catchError, of } from 'rxjs';
import { environment } from '../../environments/environment';
import { Department } from '../models/department.model';

/**
 * Service Angular chargé de récupérer la liste des Directions depuis Spring Boot.
 *
 * Équivalent Laravel : Une classe Service injectée dans un Controller, utilisant Http::get().
 * Le décorateur @Injectable({ providedIn: 'root' }) indique à Angular de créer
 * une instance unique (Singleton) partageable dans toute l'application.
 */
@Injectable({
  providedIn: 'root'
})
export class DepartmentService {
  private readonly http = inject(HttpClient);
  private readonly apiUrl = `${environment.apiUrl}/departments`;

  // Données de secours si le backend Spring Boot (:8080) n'est pas encore démarré
  private readonly fallbackDepartments: Department[] = [
    {
      id: 1,
      code: 'SIT',
      name: "Système d'Information du Trésor",
      location: 'Bâtiment A - 3ème Étage',
      description: 'Modernisation et développement des applications du Trésor'
    },
    {
      id: 2,
      code: 'DGT',
      name: 'Direction Générale du Trésor',
      location: 'Bâtiment Central - 1er Étage',
      description: 'Gestion de la trésorerie et comptabilité publique'
    },
    {
      id: 3,
      code: 'DB',
      name: 'Direction du Budget',
      location: 'Bâtiment B - 2ème Étage',
      description: "Préparation et suivi de l'exécution budgétaire"
    },
    {
      id: 4,
      code: 'DRH',
      name: 'Direction des Ressources Humaines',
      location: 'Bâtiment Central - RDC',
      description: 'Gestion des carrières, de la paie et de la formation'
    }
  ];

  getAllDepartments(): Observable<Department[]> {
    return this.http.get<Department[]>(this.apiUrl).pipe(
      catchError(() => of(this.fallbackDepartments))
    );
  }

  getFallbackDepartmentsSnapshot(): Department[] {
    return this.fallbackDepartments;
  }
}

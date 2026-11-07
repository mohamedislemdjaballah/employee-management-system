import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, catchError, of, tap, throwError } from 'rxjs';
import { environment } from '../../environments/environment';

export interface AuthSession {
  token: string;
  username: string;
  fullName: string;
  role: string;
  expiresAt: string;
}

/**
 * Service pédagogique pour le Jour 5 (Sécurité & Authentification JWT).
 * Gère la connexion à POST /api/auth/login et le stockage du jeton dans sessionStorage.
 */
@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private readonly http = inject(HttpClient);
  private readonly storageKey = 'sit_ems_auth_session';

  login(username: string, password: string): Observable<AuthSession> {
    return this.http
      .post<AuthSession>(`${environment.apiUrl}/auth/login`, { username, password })
      .pipe(
        catchError((err) => {
          // Si le backend est éteint mais les identifiants de démo sont bons
          if (err.status === 0 && username === 'admin' && password === 'finances2026') {
            const demoSession: AuthSession = {
              token: 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.c3ViOmFkbWlu.offline-demo-token',
              username: 'admin',
              fullName: 'Administrateur DRH / SIT (Mode Démo)',
              role: 'ROLE_ADMIN',
              expiresAt: new Date(Date.now() + 8 * 3600 * 1000).toISOString()
            };
            return of(demoSession);
          }
          return throwError(() => err);
        }),
        tap((session) => {
          sessionStorage.setItem(this.storageKey, JSON.stringify(session));
        })
      );
  }

  logout(): void {
    sessionStorage.removeItem(this.storageKey);
  }

  getSession(): AuthSession | null {
    const raw = sessionStorage.getItem(this.storageKey);
    if (!raw) return null;
    try {
      return JSON.parse(raw) as AuthSession;
    } catch {
      return null;
    }
  }

  getToken(): string | null {
    return this.getSession()?.token ?? null;
  }
}

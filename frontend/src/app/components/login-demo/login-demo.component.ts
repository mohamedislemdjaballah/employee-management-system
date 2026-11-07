import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { AuthService } from '../../services/auth.service';

/**
 * Composant pédagogique pour l'Atelier Sécurité du Jour 5.
 * Permet de montrer visuellement aux 7 ingénieurs comment fonctionne
 * l'authentification Stateless par jeton JWT entre Angular et Spring Boot.
 */
@Component({
  selector: 'app-login-demo',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterLink],
  template: `
    <div class="security-wrapper">
      <div class="card">
        <h1 class="sec-title">Atelier Jour 5 : Sécurité d'une Architecture Découplée (JWT)</h1>
        <p class="sec-subtitle">
          Dans une architecture découplée, le serveur Spring Boot ne garde pas de session PHP/JSP en mémoire
          (<strong>Stateless</strong>). À la connexion, Spring Boot délivre un <strong>Jeton (Token JWT)</strong>
          qu'Angular joint automatiquement à chaque requête HTTP via <code>auth.interceptor.ts</code>.
        </p>

        @if (errorMessage) {
          <div class="alert alert-error">{{ errorMessage }}</div>
        }

        @if (authService.getSession(); as session) {
          <div class="session-box">
            <div class="session-header">
              <span class="badge badge-active">Session JWT Active</span>
              <button type="button" class="btn btn-danger btn-sm" (click)="onLogout()">
                Se déconnecter (Supprimer le jeton)
              </button>
            </div>

            <div class="session-details">
              <div><strong>Utilisateur :</strong> {{ session.fullName }} (<code>{{ session.username }}</code>)</div>
              <div><strong>Rôle Spring Security :</strong> <code>{{ session.role }}</code></div>
              <div><strong>Expiration :</strong> {{ session.expiresAt }}</div>
            </div>

            <div class="token-display">
              <label>En-tête HTTP envoyé automatiquement par <code>authInterceptor</code> :</label>
              <pre>Authorization: Bearer {{ session.token }}</pre>
            </div>

            <div style="margin-top: 1rem;">
              <a routerLink="/employees" class="btn btn-primary">
                Tester un appel API avec ce jeton (Ouvrez F12 → Onglet Réseau / Network)
              </a>
            </div>
          </div>
        } @else {
          <form (ngSubmit)="onLogin()" class="login-form">
            <div class="form-group">
              <label for="username">Identifiant (Démo : <code>admin</code>)</label>
              <input
                id="username"
                type="text"
                class="form-control"
                [(ngModel)]="username"
                name="username"
                required
              />
            </div>

            <div class="form-group" style="margin-top: 1rem;">
              <label for="password">Mot de passe (Démo : <code>finances2026</code>)</label>
              <input
                id="password"
                type="password"
                class="form-control"
                [(ngModel)]="password"
                name="password"
                required
              />
            </div>

            <div style="margin-top: 1.25rem;">
              <button type="submit" class="btn btn-primary">
                Obtenir un Jeton JWT depuis POST /api/auth/login
              </button>
            </div>
          </form>
        }
      </div>
    </div>
  `,
  styles: [`
    .security-wrapper {
      max-width: 780px;
      margin: 0 auto;
    }
    .sec-title {
      font-size: 1.3rem;
      color: var(--primary-dark);
      margin-bottom: 0.4rem;
    }
    .sec-subtitle {
      font-size: 0.9rem;
      color: var(--text-muted);
      margin-bottom: 1.5rem;
    }
    .login-form {
      max-width: 420px;
      background: #f8fafc;
      padding: 1.25rem;
      border-radius: 8px;
      border: 1px solid #e2e8f0;
    }
    .form-group label {
      display: block;
      font-size: 0.85rem;
      font-weight: 600;
      margin-bottom: 0.35rem;
    }
    .session-box {
      background: #f8fafc;
      border: 1px solid #cbd5e1;
      border-radius: 8px;
      padding: 1.25rem;
    }
    .session-header {
      display: flex;
      justify-content: space-between;
      align-items: center;
      margin-bottom: 1rem;
    }
    .session-details {
      display: grid;
      gap: 0.4rem;
      font-size: 0.9rem;
      margin-bottom: 1rem;
    }
    .token-display label {
      display: block;
      font-size: 0.82rem;
      font-weight: 600;
      color: var(--text-muted);
      margin-bottom: 0.35rem;
    }
    .token-display pre {
      background: #0f172a;
      color: #38bdf8;
      padding: 0.85rem;
      border-radius: 6px;
      font-size: 0.8rem;
      overflow-x: auto;
      white-space: pre-wrap;
      word-break: break-all;
    }
  `]
})
export class LoginDemoComponent {
  readonly authService = inject(AuthService);

  username = 'admin';
  password = 'finances2026';
  errorMessage = '';

  onLogin(): void {
    this.errorMessage = '';
    this.authService.login(this.username, this.password).subscribe({
      error: (err) => {
        this.errorMessage =
          err?.error?.message || 'Identifiants invalides. Utilisez admin / finances2026.';
      }
    });
  }

  onLogout(): void {
    this.authService.logout();
  }
}

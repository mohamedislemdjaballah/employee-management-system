import { Component, inject } from '@angular/core';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { AuthService } from '../../services/auth.service';

/**
 * Barre de navigation principale du Portail RH (Ministère des Finances - SIT).
 *
 * Points pédagogiques :
 * - routerLink="/employees" remplace href="/employees" pour naviguer SANS recharger la page.
 * - routerLinkActive="active" applique automatiquement une classe CSS sur l'onglet actif.
 */
@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [RouterLink, RouterLinkActive],
  template: `
    <header class="navbar">
      <div class="navbar-inner">
        <a routerLink="/employees" class="brand">
          <span class="brand-badge">MF / SIT</span>
          <div>
            <div class="brand-title">Portail de Gestion des Agents</div>
            <div class="brand-subtitle">Ministère des Finances — Architecture Découplée Spring Boot & Angular</div>
          </div>
        </a>

        <nav class="nav-links">
          <a
            routerLink="/employees"
            routerLinkActive="active"
            [routerLinkActiveOptions]="{ exact: true }"
            class="nav-item"
          >
            Annuaire des Agents
          </a>
          <a
            routerLink="/employees/new"
            routerLinkActive="active"
            class="nav-item"
          >
            + Nouvel Agent
          </a>
          <a
            routerLink="/login-demo"
            routerLinkActive="active"
            class="nav-item nav-security"
          >
            Atelier Sécurité (Jour 5)
            @if (authService.getSession(); as session) {
              <span class="jwt-dot" title="Jeton JWT actif">● Connecté ({{ session.username }})</span>
            }
          </a>
        </nav>
      </div>
    </header>
  `,
  styles: [`
    .navbar {
      background: linear-gradient(90deg, #092847 0%, #0f3d68 100%);
      color: #ffffff;
      box-shadow: 0 2px 8px rgba(0, 0, 0, 0.15);
    }
    .navbar-inner {
      max-width: 1240px;
      margin: 0 auto;
      padding: 0.85rem 1.25rem;
      display: flex;
      align-items: center;
      justify-content: space-between;
      flex-wrap: wrap;
      gap: 1rem;
    }
    .brand {
      display: flex;
      align-items: center;
      gap: 0.85rem;
      color: #ffffff;
      text-decoration: none;
    }
    .brand-badge {
      background-color: #fbbf24;
      color: #092847;
      font-weight: 800;
      font-size: 0.85rem;
      padding: 0.45rem 0.65rem;
      border-radius: 6px;
      letter-spacing: 0.5px;
    }
    .brand-title {
      font-weight: 700;
      font-size: 1.05rem;
    }
    .brand-subtitle {
      font-size: 0.76rem;
      color: #cbd5e1;
    }
    .nav-links {
      display: flex;
      align-items: center;
      gap: 0.5rem;
      flex-wrap: wrap;
    }
    .nav-item {
      color: #e2e8f0;
      text-decoration: none;
      padding: 0.45rem 0.9rem;
      border-radius: 6px;
      font-size: 0.88rem;
      font-weight: 600;
      transition: background-color 0.15s;
    }
    .nav-item:hover {
      background-color: rgba(255, 255, 255, 0.12);
      color: #ffffff;
    }
    .nav-item.active {
      background-color: #ffffff;
      color: #092847;
    }
    .nav-security {
      border: 1px solid rgba(251, 191, 36, 0.45);
    }
    .jwt-dot {
      margin-left: 0.35rem;
      color: #4ade80;
      font-size: 0.78rem;
    }
  `]
})
export class NavbarComponent {
  readonly authService = inject(AuthService);
}

import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { NavbarComponent } from './components/navbar/navbar.component';

/**
 * Composant Racine de l'application (<app-root>).
 *
 * Équivalent Laravel : resources/views/layouts/app.blade.php
 * - <app-navbar> affiche la barre de navigation supérieure.
 * - <router-outlet> est l'emplacement dynamique où Angular injecte la page active
 *   (comme @yield('content') dans Blade).
 */
@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, NavbarComponent],
  template: `
    <app-navbar></app-navbar>
    <main class="container">
      <router-outlet></router-outlet>
    </main>
  `
})
export class AppComponent {}

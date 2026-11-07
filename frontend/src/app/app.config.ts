import { ApplicationConfig, provideZoneChangeDetection } from '@angular/core';
import { provideRouter } from '@angular/router';
import { provideHttpClient, withInterceptors } from '@angular/common/http';

import { routes } from './app.routes';
import { authInterceptor } from './interceptors/auth.interceptor';

/**
 * Configuration globale de l'application Angular (AppConfig).
 *
 * Équivalent Laravel : AppServiceProvider.php + bootstrap/app.php
 * Ici on active :
 *  1. provideRouter(routes)       -> Le routeur SPA (navigation sans recharger la page)
 *  2. provideHttpClient(...)      -> Le client HTTP pour appeler l'API REST Spring Boot
 *  3. withInterceptors([...])     -> L'intercepteur HTTP qui ajoute le jeton JWT (Atelier Jour 5)
 */
export const appConfig: ApplicationConfig = {
  providers: [
    provideZoneChangeDetection({ eventCoalescing: true }),
    provideRouter(routes),
    provideHttpClient(withInterceptors([authInterceptor]))
  ]
};

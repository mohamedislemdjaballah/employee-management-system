import { HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { AuthService } from '../services/auth.service';

/**
 * Intercepteur HTTP Fonctionnel (Atelier Sécurité Jour 5).
 *
 * Équivalent Laravel : Middleware HTTP attachant un en-tête à chaque requête.
 * Si un jeton JWT est présent dans AuthService, cet intercepteur clone la requête
 * sortante vers Spring Boot et ajoute automatiquement l'en-tête :
 *   Authorization: Bearer <token>
 */
export const authInterceptor: HttpInterceptorFn = (req, next) => {
  const authService = inject(AuthService);
  const token = authService.getToken();

  if (token) {
    const clonedRequest = req.clone({
      setHeaders: {
        Authorization: `Bearer ${token}`
      }
    });
    return next(clonedRequest);
  }

  return next(req);
};

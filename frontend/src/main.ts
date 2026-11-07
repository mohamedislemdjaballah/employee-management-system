import { bootstrapApplication } from '@angular/platform-browser';
import { appConfig } from './app/app.config';
import { AppComponent } from './app/app.component';

/**
 * Point de démarrage de l'application Angular (Standalone).
 * Monte le composant racine <app-root> (AppComponent) dans index.html.
 */
bootstrapApplication(AppComponent, appConfig)
  .catch((err) => console.error(err));

package com.finances.ems;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Point d'entrée principal de l'application Backend Spring Boot.
 *
 * Note pédagogique (pour les ingénieurs habitués au procédural) :
 * L'annotation @SpringBootApplication active :
 *  1. @Configuration : permet de déclarer des composants de configuration.
 *  2. @EnableAutoConfiguration : configure automatiquement Tomcat (port 8080), Hibernate et PostgreSQL.
 *  3. @ComponentScan : scanne le package "com.finances.ems" pour instancier automatiquement
 *     tous les @RestController, @Service et @Repository (Inversion de Contrôle / Injection de Dépendances).
 */
@SpringBootApplication
public class EmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmsApplication.class, args);
    }
}

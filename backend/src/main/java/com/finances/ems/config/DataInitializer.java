package com.finances.ems.config;

import com.finances.ems.entity.Department;
import com.finances.ems.entity.Employee;
import com.finances.ems.entity.EmployeeStatus;
import com.finances.ems.repository.DepartmentRepository;
import com.finances.ems.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * Initialisation automatique de données de démonstration au démarrage de Spring Boot
 * si la base PostgreSQL est vide.
 */
@Configuration
public class DataInitializer {

    private static final Logger log = LoggerFactory.getLogger(DataInitializer.class);

    @Bean
    public CommandLineRunner seedDatabase(
            DepartmentRepository departmentRepository,
            EmployeeRepository employeeRepository
    ) {
        return args -> {
            if (departmentRepository.count() > 0) {
                log.info("Base de données déjà initialisée ({} directions, {} agents).",
                        departmentRepository.count(), employeeRepository.count());
                return;
            }

            log.info("Initialisation des données de démonstration (Ministère des Finances / SIT)...");

            Department sit = departmentRepository.save(new Department(
                    "SIT",
                    "Système d'Information du Trésor",
                    "Bâtiment A - 3ème Étage",
                    "Modernisation et développement des applications du Trésor"
            ));

            Department dgt = departmentRepository.save(new Department(
                    "DGT",
                    "Direction Générale du Trésor",
                    "Bâtiment Central - 1er Étage",
                    "Gestion de la trésorerie et comptabilité publique"
            ));

            Department db = departmentRepository.save(new Department(
                    "DB",
                    "Direction du Budget",
                    "Bâtiment B - 2ème Étage",
                    "Préparation et suivi de l'exécution budgétaire"
            ));

            Department drh = departmentRepository.save(new Department(
                    "DRH",
                    "Direction des Ressources Humaines",
                    "Bâtiment Central - RDC",
                    "Gestion des carrières, de la paie et de la formation"
            ));

            List<Employee> sampleEmployees = List.of(
                    new Employee(
                            "FIN-2026-001",
                            "Karim",
                            "BENALI",
                            "k.benali@finances.gov",
                            "0550 12 34 56",
                            "Architecte Logiciel Principal",
                            new BigDecimal("145000.00"),
                            LocalDate.of(2018, 3, 15),
                            EmployeeStatus.ACTIVE,
                            sit
                    ),
                    new Employee(
                            "FIN-2026-002",
                            "Amina",
                            "MANSOURI",
                            "a.mansouri@finances.gov",
                            "0551 23 45 67",
                            "Ingénieur d'État en Informatique",
                            new BigDecimal("118000.00"),
                            LocalDate.of(2020, 9, 1),
                            EmployeeStatus.ACTIVE,
                            sit
                    ),
                    new Employee(
                            "FIN-2026-003",
                            "Yacine",
                            "TOUATI",
                            "y.touati@finances.gov",
                            "0552 34 56 78",
                            "Inspecteur Central du Trésor",
                            new BigDecimal("132000.00"),
                            LocalDate.of(2016, 1, 10),
                            EmployeeStatus.ACTIVE,
                            dgt
                    ),
                    new Employee(
                            "FIN-2026-004",
                            "Nadia",
                            "KHELIFI",
                            "n.khelifi@finances.gov",
                            "0553 45 67 89",
                            "Analyste Budgétaire Senior",
                            new BigDecimal("112000.00"),
                            LocalDate.of(2019, 6, 20),
                            EmployeeStatus.ON_LEAVE,
                            db
                    ),
                    new Employee(
                            "FIN-2026-005",
                            "Mourad",
                            "HADJADJ",
                            "m.hadjadj@finances.gov",
                            "0554 56 78 90",
                            "Administrateur Base de Données PostgreSQL",
                            new BigDecimal("125000.00"),
                            LocalDate.of(2021, 2, 14),
                            EmployeeStatus.ACTIVE,
                            sit
                    ),
                    new Employee(
                            "FIN-2026-006",
                            "Samira",
                            "BOUZID",
                            "s.bouzid@finances.gov",
                            "0555 67 89 01",
                            "Responsable Formation & Carrières",
                            new BigDecimal("108000.00"),
                            LocalDate.of(2017, 11, 5),
                            EmployeeStatus.ACTIVE,
                            drh
                    )
            );

            employeeRepository.saveAll(sampleEmployees);
            log.info("Données initialisées avec succès : 4 directions et 6 agents créés.");
        };
    }
}

package com.finances.ems;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Test d'intégration automatisé de l'API REST Spring Boot.
 *
 * Équivalent Laravel : Tests Feature ($this->getJson('/api/employees')->assertStatus(200))
 * Équivalent Django  : APITestCase (self.client.get('/api/employees'))
 *
 * Utilise le profil "h2" (base en mémoire) pour s'exécuter rapidement sans dépendre
 * d'un serveur PostgreSQL externe.
 */
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("h2")
class EmsApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnInitialDepartmentsAndEmployees() throws Exception {
        // 1. Vérifie que GET /api/departments renvoie les 4 directions initialisées
        mockMvc.perform(get("/api/departments"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(4)))
                .andExpect(jsonPath("$[0].code").exists());

        // 2. Vérifie que GET /api/employees renvoie au moins les 6 agents initiaux
        mockMvc.perform(get("/api/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(6))));
    }

    @Test
    void shouldRejectInvalidEmployeePayloadWith400BadRequest() throws Exception {
        String invalidJson = """
                {
                  "matricule": "",
                  "firstName": "",
                  "lastName": "TEST",
                  "email": "email-invalide",
                  "jobTitle": "Ingénieur",
                  "salary": -100,
                  "hireDate": "2026-11-15",
                  "status": "ACTIVE",
                  "departmentId": 1
                }
                """;

        mockMvc.perform(post("/api/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status", is(400)))
                .andExpect(jsonPath("$.fieldErrors.email").exists());
    }
}

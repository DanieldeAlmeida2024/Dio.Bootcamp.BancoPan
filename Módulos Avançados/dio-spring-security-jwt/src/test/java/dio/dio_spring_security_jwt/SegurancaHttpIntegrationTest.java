package dio.dio_spring_security_jwt;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import dio.dio_spring_security_jwt.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class SegurancaHttpIntegrationTest {
    @Autowired MockMvc mvc;
    @Autowired ObjectMapper mapper;

    @Test
    void cadastroPublicoNaoConcedeRoleManagerNemVazaHash() throws Exception {
        String usuario = "u" + UUID.randomUUID().toString().replace("-", "").substring(0, 10);
        String body = "{\"name\":\"Pessoa Teste\",\"username\":\"" + usuario +
                "\",\"password\":\"senha-comprida-123\",\"roles\":[\"MANAGERS\"]}";

        mvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON).content(body))
            .andExpect(status().isCreated());

        String loginBody = "{\"username\":\"" + usuario +
                "\",\"password\":\"senha-comprida-123\"}";
        String json = mvc.perform(post("/login")
                .contentType(MediaType.APPLICATION_JSON).content(loginBody))
            .andExpect(status().isOk())
            .andReturn().getResponse().getContentAsString();
        String token = mapper.readTree(json).get("token").asText();

        mvc.perform(get("/users").header("Authorization", token))
            .andExpect(status().isOk());
        mvc.perform(get("/managers").header("Authorization", token))
            .andExpect(status().isForbidden());
        mvc.perform(get("/users"))
            .andExpect(status().isUnauthorized());
        mvc.perform(get("/users").header("Authorization", "Bearer adulterado"))
            .andExpect(status().isUnauthorized());
    }

    @Test
    void passwordNaoEhSerializadaNemRolesDoJsonAceitas() throws Exception {
        User user = new User();
        user.setName("Pessoa Teste");
        user.setUsername("pessoa");
        user.setPassword("hash-de-teste");
        user.setRoles(List.of("USERS"));

        String json = mapper.writeValueAsString(user);
        assertFalse(json.contains("hash-de-teste"));
        assertFalse(json.contains("\"password\""));

        User input = mapper.readValue(
            "{\"name\":\"Pessoa\",\"username\":\"abc\",\"password\":\"senha-de-teste\",\"roles\":[\"MANAGERS\"]}",
            User.class);
        assertTrue(input.getRoles().isEmpty(), "O cliente não pode escolher papéis no JSON.");
        assertEquals("senha-de-teste", input.getPassword());
    }

    @Test
    void cadastroComSenhaCurtaRetorna400() throws Exception {
        mvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Pessoa Teste\",\"username\":\"exemplo\",\"password\":\"123\"}"))
            .andExpect(status().isBadRequest());
    }
}

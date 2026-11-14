package dio.dio_spring_security_jwt.controller;

import dio.dio_spring_security_jwt.dtos.Login;
import dio.dio_spring_security_jwt.dtos.Sessao;
import dio.dio_spring_security_jwt.model.User;
import dio.dio_spring_security_jwt.repository.UserRepository;
import dio.dio_spring_security_jwt.security.JWTCreator;
import dio.dio_spring_security_jwt.security.SecurityConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LoginControllerTest {
    private UserRepository repo;
    private PasswordEncoder encoder;
    private LoginController controller;

    @BeforeEach
    void configurar() {
        repo = mock(UserRepository.class);
        encoder = mock(PasswordEncoder.class);
        controller = new LoginController();
        ReflectionTestUtils.setField(controller, "repository", repo);
        ReflectionTestUtils.setField(controller, "encoder", encoder);
        SecurityConfig.KEY =
            "AQIDBAUGBwgJCgsMDQ4PEBESExQVFhcYGRobHB0eHyAhIiMkJSYnKCkqKywtLi8wMTIzNDU2Nzg5Ojs8PT4/QA==";
        SecurityConfig.PREFIX = "Bearer";
        SecurityConfig.EXPIRATION = 3600000L;
    }

    private Login login(String username, String pass) {
        Login login = new Login();
        login.setUsername(username);
        login.setPassword(pass);
        return login;
    }

    @Test
    void tokenAssinadoContemUsuarioEPermissao() {
        User user = new User();
        user.setUsername("pessoa");
        user.setPassword("hash");
        user.setRoles(List.of("USERS"));
        when(repo.findByUsername("pessoa")).thenReturn(user);
        when(encoder.matches("senha-valida", "hash")).thenReturn(true);

        Sessao result = controller.logar(login("pessoa", "senha-valida"));
        assertEquals("pessoa", result.getLogin());
        assertTrue(result.getToken().startsWith("Bearer "));
        assertEquals("pessoa", JWTCreator.create(result.getToken(), "Bearer").getSubject());
        assertEquals(List.of("ROLE_USERS"),
            JWTCreator.create(result.getToken(), "Bearer").getRoles());
    }

    @Test
    void falhaDeSenhaEUsoInexistenteTemMesmoStatus() {
        when(repo.findByUsername("pessoa")).thenReturn(null);
        ResponseStatusException missing = assertThrows(ResponseStatusException.class,
            () -> controller.logar(login("pessoa", "senha-valida")));
        assertEquals(HttpStatus.UNAUTHORIZED, missing.getStatusCode());

        User user = new User();
        user.setUsername("pessoa");
        user.setPassword("hash");
        when(repo.findByUsername("pessoa")).thenReturn(user);
        when(encoder.matches("errada", "hash")).thenReturn(false);
        ResponseStatusException invalid = assertThrows(ResponseStatusException.class,
            () -> controller.logar(login("pessoa", "errada")));
        assertEquals(HttpStatus.UNAUTHORIZED, invalid.getStatusCode());
        assertEquals(missing.getReason(), invalid.getReason());
    }
}

package dio.dio_spring_security_jwt.service;

import dio.dio_spring_security_jwt.model.User;
import dio.dio_spring_security_jwt.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {
    private UserRepository repo;
    private PasswordEncoder encoder;
    private UserService service;

    @BeforeEach
    void iniciar() {
        repo = mock(UserRepository.class);
        encoder = mock(PasswordEncoder.class);
        service = new UserService();
        ReflectionTestUtils.setField(service, "repository", repo);
        ReflectionTestUtils.setField(service, "encoder", encoder);
    }

    private User exemplo() {
        User user = new User();
        user.setId(999); // ID externo não deve ser aceito.
        user.setName("Pessoa Teste");
        user.setUsername("pessoa");
        user.setPassword("senha-de-teste-muito-longa");
        user.setRoles(List.of("MANAGERS"));
        return user;
    }

    @Test
    void cadastroIgnoraPapeisDeAdminEHashDeSenha() {
        User u = exemplo();
        when(encoder.encode("senha-de-teste-muito-longa")).thenReturn("hash-irreversivel");
        service.createUser(u);
        verify(repo).save(same(u));
        assertEquals(List.of("USERS"), u.getRoles());
        assertNull(u.getId());
        assertEquals("hash-irreversivel", u.getPassword());
    }

    @Test
    void usuarioDuplicadoNaoEhGravado() {
        User u = exemplo();
        when(repo.existsByUsername("pessoa")).thenReturn(true);
        ResponseStatusException ex = assertThrows(ResponseStatusException.class,
            () -> service.createUser(u));
        assertEquals(HttpStatus.CONFLICT, ex.getStatusCode());
        verify(repo, never()).save(any());
    }

    @Test
    void senhaCurtaNaoEhGravada() {
        User u = exemplo();
        u.setPassword("123");
        ResponseStatusException ex = assertThrows(ResponseStatusException.class,
            () -> service.createUser(u));
        assertEquals(HttpStatus.BAD_REQUEST, ex.getStatusCode());
        verify(repo, never()).save(any());
    }
}

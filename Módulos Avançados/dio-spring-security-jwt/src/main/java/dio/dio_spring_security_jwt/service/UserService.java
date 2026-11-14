package dio.dio_spring_security_jwt.service;

import dio.dio_spring_security_jwt.model.User;
import dio.dio_spring_security_jwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Transactional
public class UserService {
    @Autowired private UserRepository repository;
    @Autowired private PasswordEncoder encoder;

    public void createUser(User user) {
        if (user == null || user.getUsername() == null || user.getName() == null ||
                user.getPassword() == null ||
                user.getUsername().isBlank() || user.getName().isBlank() ||
                user.getPassword().length() < 12) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Informe nome, usuário e senha de ao menos 12 caracteres.");
        }
        String username = user.getUsername().trim();
        if (username.length() > 20 || user.getName().trim().length() > 50) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tamanho dos campos inválido.");
        }
        if (repository.existsByUsername(username)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Usuário já cadastrado.");
        }
        user.setId(null); // Não permitir id fornecido no cadastro.
        user.setName(user.getName().trim());
        user.setUsername(username);
        user.setRoles(List.of("USERS")); // Nunca aceitar MANAGERS da requisição pública.
        user.setPassword(encoder.encode(user.getPassword()));
        repository.save(user);
    }
}

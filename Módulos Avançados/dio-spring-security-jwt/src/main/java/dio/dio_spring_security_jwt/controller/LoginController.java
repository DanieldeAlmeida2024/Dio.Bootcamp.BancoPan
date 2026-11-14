package dio.dio_spring_security_jwt.controller;

import dio.dio_spring_security_jwt.dtos.Login;
import dio.dio_spring_security_jwt.dtos.Sessao;
import dio.dio_spring_security_jwt.model.User;
import dio.dio_spring_security_jwt.repository.UserRepository;
import dio.dio_spring_security_jwt.security.JWTCreator;
import dio.dio_spring_security_jwt.security.JWTObject;
import dio.dio_spring_security_jwt.security.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;

@RestController
public class LoginController {
    @Autowired private PasswordEncoder encoder;
    @Autowired private UserRepository repository;

    @PostMapping("/login")
    public Sessao logar(@RequestBody Login login) {
        if (login == null || login.getUsername() == null || login.getPassword() == null ||
                login.getUsername().isBlank() || login.getPassword().isBlank()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Credenciais inválidas.");
        }
        User user = repository.findByUsername(login.getUsername().trim());
        if (user == null || !encoder.matches(login.getPassword(), user.getPassword())) {
            // A mesma mensagem para usuário desconhecido e senha incorreta.
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Credenciais inválidas.");
        }

        long now = System.currentTimeMillis();
        JWTObject jwt = new JWTObject();
        jwt.setSubject(user.getUsername()); // Antes emitia token sem subject.
        jwt.setIssuedAt(new Date(now));
        jwt.setExpiration(new Date(now + SecurityConfig.EXPIRATION));
        jwt.setRoles(user.getRoles());

        Sessao sessao = new Sessao();
        sessao.setLogin(user.getUsername());
        sessao.setToken(JWTCreator.create(SecurityConfig.PREFIX, jwt));
        return sessao;
    }
}

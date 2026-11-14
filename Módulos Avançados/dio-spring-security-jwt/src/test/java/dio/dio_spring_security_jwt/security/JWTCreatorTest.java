package dio.dio_spring_security_jwt.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JWTCreatorTest {
    private static final String TEST_KEY =
        "AQIDBAUGBwgJCgsMDQ4PEBESExQVFhcYGRobHB0eHyAhIiMkJSYnKCkqKywtLi8wMTIzNDU2Nzg5Ojs8PT4/QA==";

    @BeforeEach
    void configurarChaveDeTeste() {
        SecurityConfig.KEY = TEST_KEY;
        SecurityConfig.PREFIX = "Bearer";
        SecurityConfig.EXPIRATION = 3600000L;
    }

    private JWTObject claims(String user, long issued, long expiration) {
        JWTObject obj = new JWTObject();
        obj.setSubject(user);
        obj.setIssuedAt(new Date(issued));
        obj.setExpiration(new Date(expiration));
        obj.setRoles(List.of("USERS", "ROLE_USERS"));
        return obj;
    }

    @Test
    void doisLoginsDiferentesContinuamValidosComMesmaChave() {
        long now = System.currentTimeMillis();
        String primeiro = JWTCreator.create("Bearer", claims("ana", now - 1000, now + 60000));
        String segundo = JWTCreator.create("Bearer", claims("bia", now - 1000, now + 60000));
        assertEquals("ana", JWTCreator.create(primeiro, "Bearer").getSubject());
        assertEquals("bia", JWTCreator.create(segundo, "Bearer").getSubject());
        assertEquals(List.of("ROLE_USERS"),
            JWTCreator.create(primeiro, "Bearer").getRoles());
    }

    @Test
    void rejeitaAssinaturaAdulteradaEPrefixoErrado() {
        long now = System.currentTimeMillis();
        String token = JWTCreator.create("Bearer", claims("ana", now - 1000, now + 60000));
        String[] parts = token.substring("Bearer ".length()).split("\\.");
        parts[1] = Base64.getUrlEncoder().withoutPadding()
            .encodeToString("{\"sub\":\"administrador\"}".getBytes(StandardCharsets.UTF_8));
        assertThrows(JwtException.class,
            () -> JWTCreator.create("Bearer " + String.join(".", parts), "Bearer"));
        assertThrows(JwtException.class,
            () -> JWTCreator.create("Basic " + token, "Bearer"));
    }

    @Test
    void rejeitaTokenExpirado() {
        long now = System.currentTimeMillis();
        String token = JWTCreator.create("Bearer",
            claims("ana", now - 120000, now - 60000));
        assertThrows(ExpiredJwtException.class,
            () -> JWTCreator.create(token, "Bearer"));
    }

    @Test
    void naoAceitaChaveCurtaNemChaveAusente() {
        SecurityConfig.KEY = Base64.getEncoder().encodeToString("curta".getBytes(StandardCharsets.UTF_8));
        assertThrows(IllegalStateException.class, JWTCreator::signingKey);
        SecurityConfig.KEY = "";
        assertThrows(IllegalStateException.class, JWTCreator::signingKey);
    }

    @Test
    void naoAssinaSemSubjectOuPeriodoValido() {
        long now = System.currentTimeMillis();
        assertThrows(IllegalArgumentException.class,
            () -> JWTCreator.create("Bearer", claims("", now, now + 60000)));
        assertThrows(IllegalArgumentException.class,
            () -> JWTCreator.create("Bearer", claims("ana", now, now - 1000)));
    }
}

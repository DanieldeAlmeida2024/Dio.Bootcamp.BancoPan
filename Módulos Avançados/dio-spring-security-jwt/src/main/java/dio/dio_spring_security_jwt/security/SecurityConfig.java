package dio.dio_spring_security_jwt.security;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "security.config")
public class SecurityConfig {
    public static String PREFIX;
    public static String KEY;
    public static Long EXPIRATION;

    public void setPrefix(String prefix) { PREFIX = prefix; }
    public void setKey(String key) { KEY = key; }
    public void setExpiration(Long expiration) { EXPIRATION = expiration; }

    @PostConstruct
    public void validarConfiguracao() {
        if (PREFIX == null || !PREFIX.equals("Bearer")) {
            throw new IllegalStateException("Prefixo JWT deve ser Bearer.");
        }
        if (EXPIRATION == null || EXPIRATION <= 0 || EXPIRATION > 86_400_000L) {
            throw new IllegalStateException("Expiração JWT deve estar entre 1ms e 24h.");
        }
        JWTCreator.signingKey();
    }
}

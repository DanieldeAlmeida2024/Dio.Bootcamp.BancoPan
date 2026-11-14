package dio.dio_spring_security_jwt.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/** Assinatura HS512 com chave estável injetada pelo ambiente; nunca logar tokens. */
public final class JWTCreator {
    public static final String HEADER_AUTHORIZATION = "Authorization";
    public static final String ROLES_AUTHORITIES = "authorities";

    private JWTCreator() { }

    static SecretKey signingKey() {
        String configured = SecurityConfig.KEY;
        if (configured == null || configured.isBlank()) {
            throw new IllegalStateException("JWT_SIGNING_KEY_BASE64 não configurada.");
        }
        byte[] raw;
        try {
            raw = Base64.getDecoder().decode(configured);
        } catch (IllegalArgumentException error) {
            throw new IllegalStateException("JWT_SIGNING_KEY_BASE64 não está em Base64 válido.", error);
        }
        if (raw.length < 64) {
            throw new IllegalStateException("Chave HS512 precisa conter no mínimo 64 bytes.");
        }
        return Keys.hmacShaKeyFor(raw);
    }

    public static String create(String prefix, JWTObject jwtObject) {
        Objects.requireNonNull(jwtObject, "JWTObject obrigatório.");
        if (jwtObject.getSubject() == null || jwtObject.getSubject().isBlank() ||
                jwtObject.getIssuedAt() == null || jwtObject.getExpiration() == null ||
                !jwtObject.getExpiration().after(jwtObject.getIssuedAt())) {
            throw new IllegalArgumentException("Subject, emissão e expiração válidos são obrigatórios.");
        }
        String token = Jwts.builder()
                .subject(jwtObject.getSubject())
                .issuedAt(jwtObject.getIssuedAt())
                .expiration(jwtObject.getExpiration())
                .claim(ROLES_AUTHORITIES, checkRoles(jwtObject.getRoles()))
                .signWith(signingKey(), Jwts.SIG.HS512)
                .compact();
        return prefix + " " + token;
    }

    public static JWTObject create(String authorization, String prefix) {
        if (authorization == null || prefix == null ||
                !authorization.startsWith(prefix + " ")) {
            throw new MalformedJwtException("Cabeçalho de autorização inválido.");
        }
        String rawToken = authorization.substring(prefix.length() + 1);
        if (rawToken.isBlank() || rawToken.length() > 8192 || rawToken.chars().anyMatch(Character::isWhitespace)) {
            throw new MalformedJwtException("Token JWT malformado.");
        }
        Claims claims = Jwts.parser()
                .verifyWith(signingKey())
                .build()
                .parseSignedClaims(rawToken)
                .getPayload();
        String subject = claims.getSubject();
        if (subject == null || subject.isBlank()) {
            throw new MalformedJwtException("Token sem sujeito.");
        }
        List<?> claimRoles = claims.get(ROLES_AUTHORITIES, List.class);
        if (claimRoles == null || claimRoles.stream().anyMatch(role -> !(role instanceof String))) {
            throw new MalformedJwtException("Token sem permissões válidas.");
        }
        JWTObject object = new JWTObject();
        object.setSubject(subject);
        object.setIssuedAt(claims.getIssuedAt());
        object.setExpiration(claims.getExpiration());
        object.setRoles(claimRoles.stream().map(String.class::cast).collect(Collectors.toList()));
        return object;
    }

    private static List<String> checkRoles(List<String> roles) {
        if (roles == null) return List.of();
        List<String> cleaned = new ArrayList<>();
        for (String role : roles) {
            if (role == null || !role.matches("(ROLE_)?[A-Z][A-Z0-9_]{0,40}")) {
                throw new IllegalArgumentException("Permissão inválida.");
            }
            String permission = role.startsWith("ROLE_") ? role : "ROLE_" + role;
            if (!cleaned.contains(permission)) cleaned.add(permission);
        }
        return cleaned;
    }
}

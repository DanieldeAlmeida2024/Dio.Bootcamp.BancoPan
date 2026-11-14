# Arquitetura — laboratório JWT

Versão JWT-LAB 1.1.0, 14/11/2026.

## Componentes reais

- `LoginController`: verifica usuário/senha em `UserRepository`/BCrypt e solicita JWT.
- `UserController` → `UserService`: cadastro com validação, senha BCrypt, papel fixo USERS e checagem de username duplicado.
- `JWTCreator`: assina e verifica HS512 com chave de ambiente; não mantém segredo efêmero nem escreve token no log.
- `SecurityConfig`: prefixo `Bearer`, expiração 1–86.400.000 ms e `JWT_SIGNING_KEY_BASE64`. `@PostConstruct` falha se a configuração é insegura.
- `JWTFilter`: interpreta header, valida assinatura/prazo e preenche `SecurityContextHolder` apenas na requisição corrente; limpa contexto ao terminar.
- `WebSecurityConfig`: 201 no cadastro público, login público e permissões `/users`/ `/managers`.
- `User`: entidade JPA com username único, lista de roles, hash BCrypt oculto na serialização e roles de input ignoradas.
- **H2**: armazenamento temporário dos exercícios. Não é um banco multiusuário persistente.

## Fluxo

Cadastro → validação → papéis USERS fixos → BCrypt → persistência. Login → busca por username → BCrypt.matches → `JWTObject(sub, iat, exp, roles)` → HS512 com chave estável → token Bearer. Cada requisição protegida → filtro JWT valida assinatura e expiração → roles em `SecurityContext` → Spring Security concede 200 ou nega 401/403.

## Modelo de confiança e limites

Não existe mecanismo de bootstrap administrativo no HTTP público. Papéis MANAGERS não podem ser concedidos por `POST /users`; qualquer concessão requer procedimento administrativo externo (ainda não implementado). A chave deve ser persistente e mantida em segredo; perda/rotação invalida tokens existentes.

JWT continua stateless, **não revogável individualmente**; expiração máxima configurada 24 h. Não há MFA, refresh tokens, proteção por IP, auditoria formal, migração de tabela ou gestão de sessão distribuída. Sem TLS na aplicação didática.

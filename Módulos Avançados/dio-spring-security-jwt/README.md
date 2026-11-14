# Laboratório Spring Security JWT — DIO / Banco PAN

Exemplo de autenticação e autorização com **Java 21**, Spring Boot **3.4.3**, Spring Security 6, JJWT 0.12.6 e H2. A proposta é estudar fluxo completo de registro, login, JWT e permissões. Não é um provedor de identidade de produção.

## O que foi corrigido

- **Assinatura estável:** tokens HS512 agora usam `JWT_SIGNING_KEY_BASE64` do ambiente. A aplicação rejeita chave ausente, Base64 inválido e chave menor que 64 bytes; não gera uma chave nova a cada login.
- **Sujeito no token:** o `sub` passa a conter o username. Tokens sem `sub`, expirados, adulterados ou com esquema errado são rejeitados.
- **Papéis:** cadastro público define somente `USERS`; pedidos com `roles: ["MANAGERS"]` não podem conceder privilégios. `/managers` exige role MANAGERS.
- **Senhas:** são codificadas com BCrypt antes de persistir e não aparecem na resposta JSON; tokens e permissões não são impressos em stdout.
- **HTTP:** `POST /users` responde 201, login inválido responde 401, token inválido responde 401, rota não autorizada responde 403.
- **H2:** console desativado, sem frame-options desativado e sem logs DEBUG de requisições.

## Configuração local

Requer **JDK 21** e Maven. Defina uma chave secreta HS512 **fora do repositório**. Exemplo em Linux:

```bash
export JWT_SIGNING_KEY_BASE64="$(openssl rand -base64 64 | tr -d '\n')"
mvn -f "Módulos Avançados/dio-spring-security-jwt/pom.xml" spring-boot:run
```

No Windows, gere 64 bytes aleatórios criptograficamente seguros em sua ferramenta de segredos e configure `JWT_SIGNING_KEY_BASE64` no ambiente do processo. Não coloque chaves no código, README ou em GitHub Actions secrets de saída de logs.

**A aplicação falha ao iniciar sem essa chave.** O H2 usa um banco temporário, e a aplicação se destina a testes locais. A fixture do perfil `test` é pública, somente para testes, e **nunca** deve ser usada para executar o servidor publicamente.

## Contratos

| Método/rota | Acesso | Resultado |
| --- | --- | --- |
| `POST /users` | Público | 201, usuário registrado sempre como USERS |
| `POST /login` | Público | 200, `{"login":"...","token":"Bearer ..."}` |
| `GET /users` | USERS ou MANAGERS | 200, texto de autorização |
| `GET /managers` | MANAGERS | 200, texto de autorização |
| Qualquer endpoint protegido | Sem token | 401 |
| `/managers` | Token USERS válido | 403 |

O header deve conter `Authorization: Bearer <JWT>`. O token contém `sub`, `iat`, `exp` e `authorities`.

## Executar testes

```bash
mvn -B -ntp -f "Módulos Avançados/dio-spring-security-jwt/pom.xml" verify
```

O CI `.github/workflows/security-jwt.yml` executa emissão/verificação de JWT, testes unitários de login/cadastro, testes HTTP com MockMvc, persistência H2 e restrições de acesso.

## Limitações importantes

Registro público sem confirmação de e-mail; banco H2 descartável; sem refresh tokens, revogação, proteção contra força bruta, MFA, auditoria nem migrações SQL. A chave de assinatura precisa estar em um cofre de segredos. **Não publicar como serviço de login em produção**.

[Arquitetura](docs/ARQUITETURA.md) · [Requisitos](docs/REQUISITOS.md) · [Plano de testes](docs/PLANO_TESTES.md) · [UML](docs/UML.puml) · [Versionamento](docs/VERSIONAMENTO.md).

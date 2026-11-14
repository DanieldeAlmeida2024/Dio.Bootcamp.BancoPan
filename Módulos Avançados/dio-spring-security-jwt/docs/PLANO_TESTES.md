# Plano de testes — JWT e autorização

Versão JWT-LAB 1.1.0, 14/11/2026. Execução:

```bash
mvn -B -ntp -f "Módulos Avançados/dio-spring-security-jwt/pom.xml" verify
```

| Caso | Componente | Verificação |
| --- | --- | --- |
| JWT-01 | JWTCreator | Dois tokens emitidos em sequência continuam válidos |
| JWT-02 | JWTCreator | Payload adulterado/assinatura inválida é recusado |
| JWT-03 | JWTCreator | Token expirado é recusado |
| JWT-04 | JWTCreator | Chave ausente ou curta é recusada |
| JWT-05 | JWTCreator | Sub e período inválidos impedem emissão |
| USER-01 | UserService | Cadastro ignora MANAGERS, usa USERS e hash BCrypt |
| USER-02 | UserService | Username duplicado retorna 409 |
| USER-03 | UserService | Senha curta retorna 400 |
| LOGIN-01 | LoginController | JWT inclui username e ROLE_USERS |
| LOGIN-02 | LoginController | Username ausente e senha errada retornam o mesmo 401 |
| HTTP-01 | MockMvc + H2 | Cadastro público, login e GET /users com Bearer funcionam |
| HTTP-02 | MockMvc + H2 | USERS não acessa /managers (403) |
| HTTP-03 | MockMvc + H2 | Requisição sem token ou com token inválido retorna 401 |
| HTTP-04 | Jackson | Hash não é exposto e roles de input são ignoradas |
| HTTP-05 | MockMvc + H2 | Cadastro de senha curta retorna 400 |
| CTX-01 | SpringBootTest | Inicialização usa perfil test e chave **não produtiva** |

O workflow valida o módulo de JWT **sem outros serviços externos**. O ambiente H2 de testes não comprova HTTPS, persistência entre reinícios, migração SQL, concorrência, ataque de força bruta ou segurança de produção. Ainda requer revisão de arquitetura de identidade se for evoluir além do tutorial.

# Requisitos — JWT do laboratório DIO

Versão JWT-LAB 1.1.0, 14/11/2026.

## Funcionais

- **RF01:** cadastrar usuário com nome, username e senha de no mínimo 12 caracteres.
- **RF02:** atribuir apenas `USERS` no cadastro público; rejeitar/ignorar qualquer role MANAGERS recebida.
- **RF03:** emitir JWT com `sub`, `iat`, `exp` e `authorities`; prefixo `Bearer`.
- **RF04:** verificar assinatura HS512 e expiração com mesma chave entre logins/instâncias.
- **RF05:** rejeitar token adulterado/expirado ou header errado (401).
- **RF06:** exigir USERS/MANAGERS em `GET /users` e MANAGERS em `GET /managers`, com 403 ao papel insuficiente.
- **RF07:** rejeitar login inválido com mensagem uniforme, sem informar se username existe.

## Não funcionais

- **RNF01:** configurar `JWT_SIGNING_KEY_BASE64` com entropia de pelo menos 64 bytes fora do Git.
- **RNF02:** falhar na inicialização se chave estiver ausente/fraca.
- **RNF03:** não registrar JWT ou senha em log; ocultar password em JSON de saída.
- **RNF04:** desativar console H2/DEBUG por padrão; manter frame-options ativado.
- **RNF05:** validar testes em Java 21 e Spring + H2 isolado.
- **RNF06:** documentar limites do exemplo e manter o conteúdo de estudo da DIO.

Não implementados: revogação, refresh, consentimento, RBAC administrativo dinâmico, MFA, observabilidade, restrição de tentativas e deploy HTTPS de produção.

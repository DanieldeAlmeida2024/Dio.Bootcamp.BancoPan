# Versionamento — laboratório Spring Security JWT

## JWT-LAB 1.1.0 — 14/11/2026

- Substituída chave de assinatura gerada a cada token por chave HS512 Base64 fornecida pelo ambiente.
- Corrigida emissão de `sub` e remoção de prints do token.
- Cadastro público não aceita privilégios enviados pelo usuário; papel inicial USERS fixo.
- Proteção de password via JSON e hashing BCrypt; username único.
- Tratamento explícito de JWT vencido/adulterado; 401/403.
- Console H2 e logs DEBUG desabilitados; teste com chave fictícia não produtiva.
- CI JDK 21 + JUnit, MockMvc, H2 e teste de contrato de papéis.

## JWT-LAB 1.0.0 — versão didática original

- Exemplo inicial Spring Security / JJWT do Bootcamp Banco PAN.
- Não incluía testes abrangentes de segurança e tinha chave gerada por emissão.

**Não houve deploy.** Retomada futura: migração para banco persistente, bootstrap seguro de MANAGERS, autenticação multifator, proteção contra abuso e rotação de chave com `kid`.

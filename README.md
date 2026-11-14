# Dio.Bootcamp.BancoPan

Coleção de exercícios e laboratórios Java do bootcamp Banco PAN da DIO, com módulos Spring Boot, segurança e persistência.

## Tecnologias e dependências

Java, Spring Boot, Maven; projetos independentes em subpastas.

JDK compatível com cada pom.xml; dependências de banco nos módulos apropriados.

## Como executar

```bash
cd "Módulos Avançados/Rest API/api-spring-boot"
./mvnw test
```

## Autenticação JWT e autorização

O módulo de Spring Security em `Módulos Avançados/dio-spring-security-jwt/` inclui tratamento de assinatura HS512, autorização RBAC, cadastro sem privilégio administrativo e testes integrados H2/MockMvc. Documentação específica: [README do módulo JWT](Módulos%20Avançados/dio-spring-security-jwt/README.md). O workflow `security-jwt.yml` testa somente esse módulo, não toda a coleção de exercícios.

## Mapa do projeto

- [Requisitos](docs/REQUISITOS.md)
- [Arquitetura](docs/ARQUITETURA.md)
- [UML](docs/UML.puml)
- [Testes](docs/PLANO_TESTES.md)
- [Orientações para ferramentas de IA](AGENTS.md)

**Condição:** projeto didático. Este repositório é uma coleção de exercícios: não há um único comando para executar todos os exemplos.

---

## Material do bootcamp Banco PAN

Exercícios, projetos de aula e anotações produzidos durante o bootcamp.

[BootCamp JAVA Banco Pan da DIO](https://web.dio.me/track/banco-pan-java-developer)

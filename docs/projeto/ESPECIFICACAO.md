# Trilha Java e Spring — repositório de laboratórios por módulo

**Versão 1.0.0** (11/11/2026), código `44735259d7923061cbbfbdc888f863b39ee2e3bf`. Auditoria estática dos módulos; testes de integração não executados nesta revisão.

## Requisitos de engenharia e comportamento observado
Este repositório é um **conjunto de exercícios**, não um projeto Java único. A árvore do Git divide-se em `Módulos Básicos`, `Módulos Intermediários` e `Módulos Avançados`, cada um com seus próprios exemplos e, nos projetos Spring, Maven Wrapper individual. Não existe um `pom.xml` na raiz que compile tudo em uma execução.

**Básicos:** primeiro programa/IntelliJ, fundamentos da linguagem, métodos e desafios. **Intermediários:** exceções, collections, POO, debugging, Maven e JUnit. **Avançados:** Spring Boot, configuração com properties, scopes, Inversão de Controle/injeção de dependências, Spring Security, JWT, Spring Data JPA e APIs REST. O subprojeto `Módulos Avançados/Rest API/api-spring-boot` define `UsuarioController @RequestMapping("/users")` com GET coleção, GET por username, POST, PUT e DELETE por id usando `UsuarioRepository`.

O módulo `dio-spring-security-jwt` contém `LoginController @PostMapping("/login")`, valida senha com PasswordEncoder.matches e emite um `Sessao` contendo JWT e papéis. Exemplos são independentes; autenticação do módulo JWT não protege automaticamente outras aplicações Spring do mesmo repositório. Não promover credenciais/configuração didática para produção sem revisão.

A documentação deve manter um índice de projetos em vez de propor UML único para classes não relacionadas. O UML acompanha o fluxo didático geral e um exemplo Spring que realmente existe. Reconhecer autoria/curso da DIO e contribuições educacionais.

## Como verificar um subprojeto
```bash
cd 'Módulos Avançados/Rest API/api-spring-boot' && ./mvnw test
```

Outros módulos têm comandos próprios; consultar seu `pom.xml` antes de executar. A matriz de cenários está em [testes](../testes/PLANO_DE_TESTES.md) e os diagramas em [UML](../uml/README.md).

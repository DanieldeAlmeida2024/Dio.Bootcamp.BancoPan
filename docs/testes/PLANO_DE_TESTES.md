# Testes — Trilha Java e Spring — repositório de laboratórios por módulo

**Versão 1.0.0** (11/11/2026), código `44735259d7923061cbbfbdc888f863b39ee2e3bf`. Auditoria estática dos módulos; testes de integração não executados nesta revisão.

| ID | Cenário | Critério de aceitação | Situação |
|---|---|---|---|
| DIO-01 | Compilar projeto primeiro programa Java | Classe Main executa com JDK apropriado | Ainda sem execução neste PR |
| DIO-02 | Executar laboratório de collections | Ordem/tipo conforme implementação | Ainda sem execução neste PR |
| DIO-03 | Módulo JUnit | Testes nativos executam | Ainda sem execução neste PR |
| DIO-04 | API Spring /users GET | Retorna lista conforme repository | Ainda sem execução neste PR |
| DIO-05 | API Spring /users/{username} | Retorno existente/não encontrado | Ainda sem execução neste PR |
| DIO-06 | API Spring POST/PUT/DELETE | CRUD sem inconsistências | Ainda sem execução neste PR |
| DIO-07 | Spring Security JWT /login válido | Retorna Sessao com token e roles | Ainda sem execução neste PR |
| DIO-08 | JWT senha incorreta | Não emite token e não vaza senha | Ainda sem execução neste PR |
| DIO-09 | JWT expirado/alterado | Rejeição no filtro adequado | Ainda sem execução neste PR |
| DIO-10 | JPA em banco de teste | Criação/repositório sem alterar banco real | Ainda sem execução neste PR |
| DIO-11 | Separação dos subprojetos Maven | Cada pom.xml compila isoladamente | Ainda sem execução neste PR |
| DIO-12 | Segredos em application.properties | Não publicar senhas reais | Ainda sem execução neste PR |

## Camadas
Testes unitários de modelos/serviços em Java, testes REST/Servlet com mocks e integração com banco efêmero. Cada módulo Maven deve ser executado em seu diretório; a execução de um não garante o outro. Registrar SHA, runtime Java, configuração sanitizada, estado e evidência por caso. Não usar credenciais locais em CI.

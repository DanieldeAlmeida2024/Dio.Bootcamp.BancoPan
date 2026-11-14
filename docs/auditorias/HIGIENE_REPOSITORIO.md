# Higiene do repositório — laboratório Banco PAN

## Objetivo
Manter o histórico didático e os projetos Java intactos, sem rastrear saídas de compilação, caches Gradle ou configurações pessoais da IDE.

## Revisão de 14/11/2026
- Encontrados **128 arquivos gerados ou específicos de ambiente** no índice Git antes da correção.
- Removidos apenas do controle de versão. O código-fonte, os POMs, testes e wrappers oficiais são preservados.
- Adicionadas exclusões recursivas em `.gitignore` para `target`, `build`, `out`, `.gradle`, `.idea`, etc.
- `tools/check_repository_hygiene.py` passa a verificar o índice no GitHub Actions; o pipeline do módulo JWT continua executando Maven `verify`.

## Verificação
```bash
python3 tools/check_repository_hygiene.py
mvn -B -ntp -f "Módulos Avançados/dio-spring-security-jwt/pom.xml" verify
```

Limite: o pipeline seleciona o módulo de segurança JWT, e **não certifica todos os projetos de curso**. Não houve reescrita de commits antigos; remover objetos já presentes no histórico requer auditoria específica, especialmente se surgirem segredos.

## Política para novos projetos
Arquivos gerados não devem integrar commits, e exemplos de credenciais didáticas devem ficar nitidamente isolados do uso de produção. As versões do build precisam ser reproduzíveis a partir de arquivos-fonte e manifests.

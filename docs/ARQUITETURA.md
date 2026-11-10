# Arquitetura

Coleção de exercícios e laboratórios Java do bootcamp Banco PAN da DIO, com módulos Spring Boot, segurança e persistência.

**Stack:** Java, Spring Boot, Maven; projetos independentes em subpastas.

**Camadas conceituais:** apresentação / entrada; serviços / casos de uso; domínio / entidades; adaptadores de infraestrutura (quando existem no módulo). A disposição real é a das classes do repositório, e o diagrama não presume pacotes novos.

## Decisões
- Preservar os exercícios de origem e rastrear adaptações realizadas.
- Empregar DTOs e validação em fronteiras HTTP, quando presentes.
- Em Java, preferir nomes de domínio coerentes, testes JUnit e inversão de dependências.
- Evitar alteração de framework sem testes de compatibilidade.

## Limitações
Este repositório é uma coleção de exercícios: não há um único comando para executar todos os exemplos.

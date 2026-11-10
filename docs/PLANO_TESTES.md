# Plano de testes

## Casos
- T01: instanciação e execução com dados válidos.
- T02: tratamento de valor inválido.
- T03: validação de comportamento do domínio.
- T04: documentação, escopo e arquitetura legíveis.

```bash
cd "Módulos Avançados/Rest API/api-spring-boot"
./mvnw test
python -m unittest discover -s tests -p test_documentacao.py -v
```

**Importante:** os testes documentais não substituem execução do Maven, JUnit, banco de dados ou integração.

Limite: Este repositório é uma coleção de exercícios: não há um único comando para executar todos os exemplos.

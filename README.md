# Poker API - Teste de Desenvolvedor Back-End

![GitHub repo size](https://img.shields.io/github/repo-size/VinciPy/teste.pocker.suprema?style=for-the-badge)
![GitHub language count](https://img.shields.io/github/languages/count/VinciPy/teste.pocker.suprema?style=for-the-badge)
![GitHub forks](https://img.shields.io/github/forks/VinciPy/teste.pocker.suprema?style=for-the-badge)


Bem-vindo ao repositório da Poker API! Este projeto foi desenvolvido como parte de um teste para a vaga de Desenvolvedor Back-End. A API simula funcionalidades básicas de um jogo de poker, incluindo autenticação de usuários, criação de mesas, adição de jogadores e simulação de um vencedor.

## 🚀 Visão Geral

Este projeto é uma API desenvolvida em Java utilizando o framework Quarkus. Ele inclui autenticação JWT, persistência de dados, e é totalmente containerizado com Docker Compose. A documentação da API foi gerada com Swagger, e todos os endpoints estão devidamente protegidos e testados.

## Bad Smells

- **Rota de criação de usuario desprotegida**: Para evitar o acesso para criacão do primeiro usuario para autenticação.

- **Chaves de autenticação no repositorio online**: Para evitar a criação toda vez no clone.
Tudo isso foi feito pensando na **praticidade** para garantir que o código pudesse ser facilmente rodado em sua máquina, permitindo que você se concentrasse no que realmente importa para a avaliação, sem perder tempo com a configuração ou ajustes do ambiente.

Agradeço pela compreensão!

---
*Esta é uma versão simplificada para facilitar a execução. Certamente, em um ambiente de produção ou quando mais tempo for disponível, seguiria melhores práticas de engenharia de software e de arquitetura.*

## 📋 Requisitos

- **Quarkus**: Framework utilizado para desenvolver a API.
- **Persistência de Dados**: Utilização de um banco de dados nativo do Quarkus.
- **Docker Compose**: Para subir a aplicação e o banco de dados.
- **Autenticação JWT**: Proteção dos endpoints da API.
- **Swagger**: Documentação da API.
- **Testes Unitários**: Implementação de testes para garantir a qualidade do código.

## Endpoints da API

## Regras de Negócio

- Um usuário não pode ser adicionado duas vezes à mesma mesa.
- Uma mesa deve ter no mínimo 3 jogadores para que um ganhador seja simulado.
- Validação básica de dados como CPF e telefone.

## Como Executar o Projeto

### Pré-requisitos

- Docker
- Docker Compose

### 🔧 Passos para Execução

1. Clone o repositório:
   ```bash
   git clone https://github.com/VinciPy/poker-api.git
   ```
2. Navegue ate o diretorio do projeto 
    ```bash
   cd poker-api
   ```
   
3. Suba os containers com o comando: 
    ```bash
   docker-compose up --build 
   ```
4. Acesse a documentação da api:
   http://localhost:8080/swagger-ui

## ✒️ Autores

- **Vinicius Santana** - _Trabalho Inicial_ - (https://github.com/VinciPy)

   
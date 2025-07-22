# Sistema Bancário em Java

Um projeto simples de sistema bancário desenvolvido em Java para a disciplina de Programação Orientada a Objetos na Universidade Federal de Uberlândia (UFU).

## 📋 Descrição

Este projeto simula as operações básicas de um sistema bancário, incluindo:
- Cadastro de bancos e clientes
- Abertura de contas correntes (básicas e especiais)
- Operações bancárias (depósito, saque e transferência)
- Gerenciamento de movimentações financeiras
- Emissão de extratos

## 🛠️ Funcionalidades

- **Menu interativo** no terminal
- **CRUD** de bancos e clientes
- **Operações financeiras**:
    - Depósitos
    - Saques
    - Transferências entre contas
- **Relatórios**:
    - Listagem de bancos e clientes
    - Extrato bancário
    - Saldo atual

## 🚀 Como Executar

1. Clone o repositório:
   ```bash
   git clone [URL_DO_REPOSITORIO]
   ```

2. Compile e execute o projeto:
   ```bash
   javac Main.java
   java Main
   ```

3. Siga as instruções no menu interativo.

## 📂 Estrutura do Projeto

- `bancosistema.interfaceusuario.Aplicacao.java`: Classe principal com o menu interativo
- `bancosistema.entidades.Banco.java`: Representa um banco e suas contas
- `bancosistema.entidades.Cliente.java`: Armazena dados dos clientes
- `bancosistema.entidades.ContaCorrente.java`: Implementa operações bancárias
- `bancosistema.entidades.Movimentacao.java`: Registra transações financeiras
- `Main.java`: Ponto de entrada do programa

## 📝 Observações

Este é um projeto em desenvolvimento como parte da disciplina de POO. Novas funcionalidades serão implementadas ao longo do semestre.

Desenvolvido por Vinícius Augusto de Souza - Ciência da Computação UFU
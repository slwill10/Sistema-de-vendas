# Sistema de Vendas Desktop

Este é um sistema de vendas desktop desenvolvido em Java com integração ao banco de dados MySQL. O sistema permite o salvamento de clientes no banco de dados e a consulta desses clientes.

## Requisitos

- Java Development Kit (JDK) instalado
- MySQL Server instalado e configurado

## Configuração do Banco de Dados

Antes de executar o sistema, é necessário configurar o banco de dados MySQL:
1. Crie um banco de dados chamado `bdvendas`;
2. Execute o script:
CREATE DATABASE BDVENDAS;
CREATE USER 'teste'@'%' IDENTIFIED BY '123';
GRANT ALL ON *.* TO 'teste'@'%' WITH GRANT OPTION;
flush privileges;
USE BDVENDAS;
/***** TABELA CLIENTES *****/
CREATE TABLE tb_clientes (
  id int auto_increment primary key,
  nome varchar(100),
  rg varchar (30),
  cpf varchar (20),
  email varchar(200),
  telefone varchar(30),
  celular varchar(30),
  cep varchar(100),
  endereco varchar (255),
  numero int,
  complemento varchar (200),
  bairro varchar (100),
  cidade varchar (100),
  estado varchar (2)
);
/*****************/
/***** TABELA FORNECEDORES *****/
CREATE TABLE tb_fornecedores (
  id int auto_increment primary key,
  nome varchar(100),
  cnpj varchar (100),
  email varchar(200),
  telefone varchar(30),
  celular varchar(30),
  cep varchar(100),
  endereco varchar (255),
  numero int,
  complemento varchar (200),
  bairro varchar (100),
  cidade varchar (100),
  estado varchar (2)
);
/*****************/
/***** TABELA FUNCIONARIOS *****/
CREATE TABLE tb_funcionarios (
  id int auto_increment primary key,
  nome varchar(100),
  rg varchar (30),
  cpf varchar (20),
  email varchar(200),
  senha varchar(10),
  cargo varchar(100),
  nivel_acesso varchar(50),
  telefone varchar(30),
  celular varchar(30),
  cep varchar(100),
  endereco varchar (255),
  numero int,
  complemento varchar (200),
  bairro varchar (100),
  cidade varchar (100),
  estado varchar (2)
);
/*****************/
/***** TABELA PRODUTOS *****/
CREATE TABLE tb_produtos (
  id int auto_increment primary key,
  descricao varchar(100),
  preco decimal (10,2),
  qtd_estoque int,
  for_id int,
  FOREIGN KEY (for_id) REFERENCES tb_fornecedores(id)
);
/*****************/
/***** TABELA VENDAS *****/
CREATE TABLE tb_vendas (
  id int auto_increment primary key,
  cliente_id int,
  data_venda datetime,
  total_venda decimal (10,2),
  observacoes text,
  FOREIGN KEY (cliente_id) REFERENCES tb_clientes(id)
);
/*****************/
/***** TABELA ITENS_VENDAS *****/
CREATE TABLE tb_itensvendas (
  id int auto_increment primary key,
  venda_id int,
  produto_id int,
  qtd int,
  subtotal decimal (10,2),

  FOREIGN KEY (venda_id) REFERENCES tb_vendas(id),
  FOREIGN KEY (produto_id) REFERENCES tb_produtos(id)
);
/*****************/
select * from tb_clientes where nome like 'a%';

## Execução do Sistema
Após configurar o banco de dados, siga estes passos para executar o sistema:
1. Clone este repositório: `git clone https://github.com/seu_usuario/sistema-vendas-desktop.git`
2. Abra o projeto em sua IDE Java preferida.
3. Compile e execute o projeto.

## Funcionalidades

Funcionalidades Principais:
## Cadastro de Usuário:
Permite a inserção de novos usuários no sistema.

## Consulta de Usuário:
Facilita a busca e visualização dos detalhes dos usuários cadastrados.

## Detalhes da Venda:
Oferece informações detalhadas sobre as vendas realizadas, incluindo itens vendidos e valores.

## Cadastro de Fornecedor:
Permite o cadastro de novos fornecedores para os produtos.

## Consulta de Fornecedor:
Permite visualizar os detalhes dos fornecedores cadastrados.

## Cadastro de Funcionário:
Permite a inserção de novos funcionários no sistema.

## Consulta de Funcionários:
Facilita a busca e visualização dos detalhes dos funcionários cadastrados.

## Histórico de Vendas:
Mantém um registro histórico de todas as vendas realizadas.

## Tela de Login:
Interface para autenticação de usuários antes de acessar o sistema.

## Menu:
Navegação intuitiva para acessar as diferentes funcionalidades do sistema.

## Tela para Pagamento:
Permite o registro e processamento de pagamentos das vendas.

## Cadastro de Venda:
Funcionalidade para registrar novas vendas no sistema.

## Total de Vendas no Mês:
Calcula e exibe o total de vendas realizadas em um determinado mês.

## Contribuição

Contribuições são bem-vindas! Sinta-se à vontade para abrir um pull request para adicionar novas funcionalidades, corrigir bugs ou melhorar a documentação.



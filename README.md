# 📌 AgroDB – API de Gestão Agrícola  
**Servidor para gerenciamento de clientes, produtores, produtos, pedidos e pagamentos em um ecossistema agrícola.**

Este projeto implementa uma API REST robusta, modular e segura, permitindo que produtores rurais cadastrem produtos com facilidade enquanto clientes encontram, solicitam pedidos e realizam pagamentos com diferentes métodos.  
Conta com persistência híbrida (MySQL + MongoDB), controle de acesso baseado em papéis e registro de logs de operações críticas.

---

## 📘 **Sumário**
1. [Visão Geral](#visão-geral)  
2. [Tecnologias Utilizadas](#tecnologias-utilizadas)  
3. [Arquitetura Geral](#arquitetura-geral)  
4. [Configuração do Ambiente](#configuração-do-ambiente)  
5. [Segurança e Controle de Acesso](#segurança-e-controle-de-acesso)  
6. [Endpoints Principais](#endpoints-principais)  
7. [Fluxos do Sistema](#fluxos-do-sistema)  
8. [Persistência NoSQL – Logs](#persistência-nosql--logs)  
9. [Erros e Respostas](#erros-e-respostas)  
10. [Como Rodar o Projeto](#como-rodar-o-projeto)

---

# 📖 Visão Geral

**AgroDB** é uma API criada para facilitar operações entre **clientes** e **produtores rurais**, permitindo:

- Cadastro de clientes  
- Cadastro de produtores e suas certificações  
- Cadastro de produtos  
- Associação Produtor–Produto  
- Realização de pedidos e seus itens  
- Registros de pagamentos (Pix, Cartão e Boleto)  
- Logs de operações sensíveis usando MongoDB

A API segue princípios modernos de arquitetura REST, boas práticas de separação de responsabilidades, validação, segurança e padronização de respostas.

---

# 🛠️ Tecnologias Utilizadas

### **Backend**
- **Java 21**
- **Spring Boot 3.5.7**
- Spring Security (BasicAuth)
- Spring Data JPA
- Spring Data MongoDB

### **Bancos de Dados**
- **MySQL** (Relacional – dados principais)
- **MongoDB** (NoSQL – logs operacionais)

### **Gerenciamento de Dependências**
- **Maven**

---

# 🏗️ Arquitetura Geral

A aplicação segue uma arquitetura em camadas:

```text
Controller → Service → Repository → Database (MySQL)
                           ↘→ LogService → MongoDB
```

Cada módulo possui isolamento próprio (Cliente, Produto, Produtor, Pedido, Pagamento etc.).

### Principais tabelas relacionais (MySQL)
- Cliente  
- Endereco  
- Produtor_rural  
- Certificacao  
- Produto  
- ProdutorProduto  
- Pedido  
- ItemPedido  
- Pagamento (abstrata)  
- Pix  
- Boleto  
- Cartao  

### Documento NoSQL (MongoDB)
- LogOperacao

---

# ⚙️ Configuração do Ambiente

### 🔧 `application.properties`

```properties
spring.application.name=back
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.data.mongodb.uri=mongodb://localhost:27017/agrodb_nosql

logging.level.org.hibernate.SQL=DEBUG
logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE

# Writer
app.datasource.writer.url=jdbc:mysql://localhost:3306/agrodb
app.datasource.writer.username=app_writer
app.datasource.writer.password=******
app.datasource.writer.driver-class-name=com.mysql.cj.jdbc.Driver

# Reader
app.datasource.reader.url=jdbc:mysql://localhost:3306/agrodb
app.datasource.reader.username=app_reader
app.datasource.reader.password=******
app.datasource.reader.driver-class-name=com.mysql.cj.jdbc.Driver

# Admin
app.datasource.admin.url=jdbc:mysql://localhost:3306/agrodb
app.datasource.admin.username=app_admin
app.datasource.admin.password=******
app.datasource.admin.driver-class-name=com.mysql.cj.jdbc.Driver
```

---

# 🔐 Segurança e Controle de Acesso

A API utiliza **Basic Auth** com controle de permissões via `@PreAuthorize`.

| Papel | Permissões |
|-------|------------|
| **READER** | GET |
| **WRITER** | POST / PUT |
| **ADMIN** | DELETE + Total |

Trecho de exemplo:

```java
@PreAuthorize("hasAnyRole('WRITER','ADMIN')")
@PostMapping
public ResponseEntity<Cliente> createCliente(@RequestBody CreateClienteDto dto) {
    ...
}
```

---

# 📡 Endpoints Principais

Abaixo os módulos e respectivas rotas resumidas.

## 👤 Cliente (`/cliente`)
- POST `/cliente`
- GET `/cliente/{id}`
- POST `/cliente/{id}`
- GET `/cliente`
- DELETE `/cliente/{id}`

## 📦 Produto (`/produto`)
- POST `/produto`
- GET `/produto/{id}`
- POST `/produto/{id}`
- GET `/produto`
- DELETE `/produto/{id}`

## 👨‍🌾 Produtor (`/produtor`)
- POST `/produtor`
- GET `/produtor/{id}`
- POST `/produtor/{id}`
- GET `/produtor`
- DELETE `/produtor/{id}`

## 🔗 Produtor–Produto (`/produtor-produto`)
- POST `/produtor-produto/{idProdutor}`

## 🧾 Pedido (`/pedido`)
- POST `/pedido`
- GET `/pedido/{id}`
- POST `/pedido/{id}`
- GET `/pedido`
- DELETE `/pedido/{id}`

## 📋 Item do Pedido (`/pedido`)
- POST `/pedido/{id}/item`
- GET `/pedido/item/{idPedido}`

## 💰 Pagamentos (`/pagamento`)
### Pix
- POST `/pagamento/pix`
- GET `/pagamento/pix/{id}`

### Cartão
- POST `/pagamento/cartao`
- GET `/pagamento/cartao/{id}`

### Boleto
- POST `/pagamento/boleto`
- GET `/pagamento/boleto/{id}`

---

# 🔄 Fluxos do Sistema

## **Fluxo 1 – Cliente fazendo pedido**

1. Criar cliente  
2. Criar pedido  
3. Adicionar itens ao pedido  
4. Registrar pagamento (Pix, Cartão ou Boleto)

Métodos envolvidos:
- `createCliente`
- `createPedido`
- `addItens`
- `criarPagamento*`

---

## **Fluxo 2 – Produtor disponibilizando produtos**

1. Criar produtor + certificação  
2. Criar produto  
3. Associar produto ao produtor  
4. Produto pode ser utilizado nos pedidos

Métodos envolvidos:
- `createProdutor`
- `createProduto`
- `vincularProdutorProduto`

---

# 🗃️ Persistência NoSQL – Logs

Toda operação sensível gera um documento MongoDB:

### Exemplo real:

```json
{
  "tipoOperacao": "CREATE",
  "entidade": "Cliente",
  "idEntidade": "3",
  "usuario": "app_admin",
  "dataHora": "2025-11-14T20:28:47.904Z",
  "_class": "com.agrodb.back.nosql.models.LogOperacao"
}
```

---

# ❗ Erros e Respostas

### **404 – Não encontrado**
```json
{
  "mensagem": "Cliente com id 10 não encontrado."
}
```

### **500 – Erro interno**
Retorna o padrão Spring Boot com stacktrace desabilitado em produção.

---

# ▶️ Como Rodar o Projeto

### 1. Clonar o repositório
```bash
git clone https://github.com/seuusuario/agrodb.git
```

### 2. Configurar MySQL e MongoDB  
Criar os bancos `agrodb` e `agrodb_nosql`.

### 3. Ajustar `application.properties`

### 4. Rodar pelo IntelliJ
Executar a classe principal:

```bash
BackApplication
```

### 5. Testar via Postman / Insomnia

---

# 🚀 Pronto para publicação

Este README foi estruturado para uso direto em portfólio (GitHub/GitLab/Bitbucket).  
Sinta-se à vontade para complementar com prints de requisições, diagramas UML ou coleções do Postman.

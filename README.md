-----

# Sistema de Gestão para Restaurante

Este é um projeto de um sistema para gestão de restaurantes, desenvolvido com foco em modernizar e otimizar as operações diárias, desde o gerenciamento de cardápios até o controle de acesso de usuários.

-----

### 💻 Tecnologias

  * **Java**: Linguagem principal do back-end.
  * **Spring Boot**: Framework para simplificar o desenvolvimento de aplicações Java.
  * **Maven**: Ferramenta de automação de construção e gerenciamento de dependências.
  * **Jakarta EE**: Conjunto de especificações para construir aplicações Java corporativas.
  * **Docker**: Para a criação e gerenciamento de contêineres do projeto.

-----

### 🚀 Funcionalidades Atuais

  * **Regras de Negócio e Controle de Acesso**: Implementação de regras para garantir que cada tipo de usuário (administrador, atendente, cliente) tenha acesso somente às funcionalidades permitidas.
  * **Cardápio Interativo**: Um front-end intuitivo para visualização do cardápio, permitindo que os clientes naveguem e façam pedidos de forma simples.
  * **Integração com QR Code**: Explorando a possibilidade de uma funcionalidade para acessar o cardápio e fazer pedidos usando QR Codes, baseada em estudos de projetos anteriores.
  * **Contêinerização com Docker**: Imagem Docker do projeto para facilitar a implantação em diferentes ambientes de desenvolvimento e produção.

-----

### ⚙️ Como Rodar o Projeto

1.  **Pré-requisitos**: Certifique-se de ter o **JDK 17** ou superior e o **Maven** instalados.
2.  **Clonar o repositório**:
    ```bash
    git clone [link-do-seu-repositorio]
    cd [nome-do-seu-projeto]
    ```
3.  **Compilar e Rodar**:
    ```bash
    ./mvnw spring-boot:run
    ```

-----

### 🐳 Docker

Para rodar o projeto usando Docker, você pode:

1.  **Construir a imagem**:
    ```bash
    docker build -t ricl_restaurante .
    ```
2.  **Executar o contêiner**:
    ```bash
    docker run -p 8080:8080 ricl_restaurante
    ```

-----

### 💡 Próximos Passos

  * [ ] Finalizar a implementação das regras de negócio.
  * [ ] Melhorar o front-end do cardápio.
  * [ ] Desenvolver a funcionalidade de QR Code para pedidos.
  * [ ] Otimizar a imagem Docker para produção.
  * [ ] Criar testes unitários e de integração.

-----

Se precisar de ajuda para detalhar alguma seção, como a parte de como rodar ou sobre a estrutura do projeto, é só me dizer\!

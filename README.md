# Servidor WEB em Java

Este projeto foi desenvolvido para a matéria Rede de Computadores II no 7ºsemestre do curso de Engenharia da Computação. É um exemplo de um servidor WEB simples desenvolvido em Java. Ele é capaz de servir arquivos estáticos (como HTML, CSS, JavaScript e imagens) aos clientes (Servidor Local) que se conectam a ele. O servidor funciona ouvindo em uma porta especificada (por padrão, a porta 8080) e aceita conexões de clientes.

## Como usar

1. Compile o código Java.
2. Execute o servidor. Por padrão, ele escuta na porta 8080.
3. Abra um navegador e acesse `http://localhost:8080/` para visualizar o conteúdo servido pelo servidor.

## Requisitos

- Java 22 ou superior.
- Maven para gerenciamento de dependências.

## Estrutura do projeto

- `src/main/java`: contém o código-fonte Java para o servidor.
- `src/main/resources/static`: contém os arquivos estáticos (HTML, CSS, JavaScript, imagens) que o servidor serve aos clientes.
- `pom.xml`: arquivo de configuração do Maven.
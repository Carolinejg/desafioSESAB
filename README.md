# Backend

Criação de um CRUD que atenda as seguintes especificações:  

***Tela de Pesquisa:*** Deverá conter os filtros de Nome, CPF e Período de cadastro; 

***Tela de Cadastro:*** Deverá conter os campos nome, email, cpf, perfil e endereços. 

**1.1) Critérios:**  

O CRUD deverá possuir a opção de alterar o cadastro; 

O CRUD deverá possuir a opção de excluir o cadastro; 

O CRUD deverá possuir a opção de detalhar; 

**1.2) Regras:**

Um usuário só pode ser vinculado a um perfil; 

Um perfil pode possuir um ou vários usuários; 

Um usuário pode ter mais de um endereço, e um mesmo endereço pode pertencer a mais de um usuário.  
  

### Requisitos obrigatórios

- MAVEN
- HIBERNATE
- No mínimo uma consulta com CRITERIA
- No mínimo uma consulta com JPQL
- JSF
- PRIMEFACES
- Arquitetura MVC

## Modelo Conceitual
![](https://github.com/Carolinejg/desafioSESAB/blob/main/src/main/webapp/resources/imagens/UMLSESAB.png)

## Layout web
### Tela principal
![](https://github.com/Carolinejg/desafioSESAB/blob/main/src/main/webapp/resources/imagens/telaprincipal.png)
A tela principal contém as seguintes funcionalidades: 
- Cadastro de um usuário 
- Cadastro dos endereços que serão associados ao usuário no momento do seu cadastro 

OBS: **Para que o endereço(s) sejam associados ao usuário no momento do seu cadastro o mesmo deverá informar os seus respectivos endereços e confirmar a adição deles na opção cadastrar endereço**


# Tree Test System #

Esta é uma aplicação que representa uma estrutura de arvore. Além de exibir a estrutura ela permiti adicionar, editar, excluir os nós da arvore e também fazer busca pela descrição do nó.

### Desenvolvimento do projeto ###

* Projeto Maven;
* Java 8;
* Hibernate;
* MySQL;
* Desenvolvido para Tomcat 9;
* Backend em camadas REST, SERVICE, DAO;
* Trafico de JSON entre back/front;
* AngularJS 1.6.2.

### Montando ambiente ###

* Check in no projeto;
* Importar como maven project;
* Acessar o arquivo 'persistence.xml' e alterar base e credenciais do MySQL;
* Montar o servidor:
** Servidor Tomcat 9;
** Adicionar deploy de artefato war exploded;
** Adicionar "/TreeTest" como contexto da aplicação.
# Sistema Controle Predial

O Sistema Controle Predial foi desenvolvido na disciplina Práticas de Programação Integrada no ano de 2016, o sistema feito utilizando
Java, permite o controle de acesso ao usuário no prédio através da atualização de um TXT criptografado que representa o armazenamento
interno da catraca, gerenciamento de empresas no prédio e seus respectivos conjuntos, assim como
a simulação do controle de temperatura de cada conjunto definindo os limites de funcionamento, além disso o sistema permite
gerenciamento de usuários com seus niveis de acesso(Síndico,Atendente e Funcionário), o síndico poderá consultar todos os acessos
que foram realizado no prédio. Entretanto, apenas enfatizando que o Sistema Controle Predial não inclui integração com nenhum tipo de automação
predial, algumas de suas funções são apenas simulações, mas pode sim ser usado como base para automação.

## Começando

O Sistema Controle Predial possui um arquivo .Jar executável na pasta /docs/executavel/SistemaPredia.jar, e também os arquivos necessários para sua
execução, siga as intruções abaixo.

### Pré Requisitos

O que você precisa para instalar o software.

* [Java SE Runtime Environment 8](https://www.oracle.com/technetwork/pt/java/javase/downloads/jre8-downloads-2133155.html)
* [MYSQL 8.0](https://dev.mysql.com/downloads/mysql/)


### Instalando

Para executar Sistema Predial você primeiramente deve fazer seguintes passos.

*1 - Configuração do Banco de Dados*
```
Considerando que você ja tenha MYSQL instalado, abra o MYSQL e execute os scripts que estão
na pasta /docs/banco_de_dados/SistemaPredial.sql
```
*2 - Configurando autenticação do banco de dados*
```
Após a execução dos scripts SQL, abra o arquivo config.txt que está em /docs/executavel/config.txt, nesse txt
você vai substituir respectivamente usuário,senha pela suas credenciais do seu banco de dados.

Exemplo: 

- Antes
#Configuração do Mysql - Usuário Senha
 usuario senha
#Fim Configuração

-Depois

#Configuração do Mysql - Usuário Senha
 admin 1234
#Fim Configuração

```
*3 - e para finalizar*

```
Acesse o sistema como sindico utilizando as credencias (Usuário: Admin , Senha:  padrao) e
clique em "Acessar Sistema", e após o Login acesse a opção "Enviar Acessos" no menu lateral, 
nessa opção o sistema irá atualizar os acessos da catraca, e por fim o Sistema Predial
estará pronto para ser utilizado.
```

## Algumas observações...

Na pasta /docs/credenciais estão os arquivos em formato xlsx que são alguns dados para testar o sistema.

## Construído com

* [Java Swing](https://docs.oracle.com/javase/8/docs/api/index.html?javax/swing/package-summary.html) - Construção da Interface Gráfica
* [Mysql 8](https://dev.mysql.com/doc/) - Banco de dados
* [Mysql Connector Java 8.0](https://mvnrepository.com/artifact/mysql/mysql-connector-java/8.0.15) - Ponte entre Java e Mysql.


## Status do Projeto

- Concluído, porém necessita de refatoração.

## Autores

* **Mateus Silva** - *Responsável pela criação do view, criptografia e outros...* - [Mateussilvasant](https://github.com/Mateussilvasant)
* **Douglas Miler** - *Responsável pela criação do model,dao e controller do projeto...* - [Douglas-Miler](https://github.com/Douglas-Miler)
* **Flávio Vaz** - *Responsável pela criação do model,dao e controller do projeto...* 


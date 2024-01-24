<h1 align="center">Sistema de Gestão Acadêmica para Docentes</h1>

![GitHub Org's stars](https://img.shields.io/github/license/Artur-Neves/Gerenciamento-escolar_java)
&nbsp;
![Badge Finalizado](http://img.shields.io/static/v1?label=STATUS&message=finalizado)

O Sistema de Gestão Acadêmica é uma aplicação desktop projetada para facilitar o trabalho dos docentes no gerenciamento de informações cruciais, como notas, faltas, presença e situação acadêmica de seus alunos.

# :hammer: Funcionalidades do projeto

- **`CRUD das Entidades`**: O sistema possui operações de criação, leitura, atualização e exclusão para cinco entidades (aluno, atividade, aula, matéria e turma). Todas essas entidades são devidamente mapeadas e persistidas no banco de dados.
- **`Diferentes Cálculos da Média do Discente`**: No projeto, há cinco formas de calcular a média do aluno, sendo estas: Soma Simples, Média Aritmética, Média Ponderada, Soma com Divisor Informado e Maior Nota. Cada uma dessas formas possui sua própria lógica para a geração da nota final do aluno.
- **`Percentual Referente à Presença`**: Com base no número total de aulas e na quantidade de faltas de um aluno específico, é calculado o percentual de presença desse aluno.
- **`Situação do Discente`**: Com base nos dados relativos à média do aluno, é possível determinar sua situação, que pode variar entre: "Aprovado(a) por recuperação", "Aprovado(a)", "Reprovado(a)" ou "Cursando".
- **`Relatório dos Alunos`**: É possível acessar as informações dos alunos por meio de suas respectivas turmas. Dessa forma, o docente pode acessar, a qualquer momento, informações importantes, como a situação do aluno, presença, número de faltas, entre outros.
- **`Geração de PDFs`**: Além de contar com um relatório abrangente, o sistema permite que o usuário faça o download do relatório em formato PDF e o salve no diretório de sua escolha.

## ✔️ Técnicas e tecnologias utilizadas

As técnicas e tecnologias utilizadas no projeto foram:

- **`Hibernate`**: Framework de mapeamento objeto-relacional (ORM) para Java.
- **`Arquitetura MVC (Model-View-Controller)`**: Projeto arquitetural que divide a aplicação em três camadas: Model, View e Controller.
- **`Java Swing`**: É um toolkit de widget GUI (Interface Gráfica do Usuário) para uso com o Java.
- **`POO (Programação Orientada a Objetos)`**: Paradigma que envolve o gerenciamento de classes, objetos, encapsulamento, herança e polimorfismo.
- **`JPQL (Java Persistence Query Language)`**: É uma linguagem de consulta de objeto semelhante ao SQL, mas que opera em objetos Java, não em tabelas de banco de dados diretamente.
- **`Itex`**: Biblioteca utilizada para a criação dod PDFs


# 🛠️ Abrir e rodar o projeto

**Após baixar o projeto, abra a IDE de sua preferência e siga os seguintes passos:**

-  Ache uma opção semelhante há **Open an Existing Project with Marven**.
-  Procure o local onde o projeto está e o selecione (Caso o projeto seja baixado via zip, é necessário extraí-lo antes de procurá-lo)
-  Crie um banco de dados usando o postgresql
-  redefina as informações deste banco de dados no arquivo "persistence.xml"
-  por fim execute o arquivo "Materia.java"

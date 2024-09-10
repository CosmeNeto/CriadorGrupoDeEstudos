<h1 align="center"> Criador de Grupo de Estudos </h1>

## *Descrição do Projeto*

O Criador de Grupo de Estudos é uma aplicação desenvolvida em Java que permite a criação e o gerenciamento de grupos de estudo. O sistema oferece funcionalidades para cadastro de usuários, criação de grupos, participação em grupos de estudo, e chat entre participantes, com persistência de dados.

## *Tecnologias Utilizadas*

Java 21: Linguagem principal utilizada no projeto.

Java Swing: Utilizado para a interface gráfica do sistema.

Persistência de dados: Utilização de arquivos `.txt` para armazenamento de dados de usuários, grupos e chats.

## *Funcionalidades*

### Cadastro de Usuários:
Permite o cadastro de Alunos e Professores, armazenando informações como nome, e-mail, senha, e dados específicos para cada tipo de usuário.

### Criação de Grupos de Estudo:
Qualquer usuário pode criar um grupo de estudo, informando nome do grupo, matéria, local, horário e gerando um código alfanumérico único.
Não é permitido criar grupos com o mesmo nome.

### Participação em Grupos de Estudo:
Usuários podem ingressar em grupos já existentes utilizando o código do grupo e ver informações detalhadas dos grupos que participam.

### Chat entre Participantes:
Cada grupo de estudo possui um chat persistente em arquivos de texto, onde os usuários podem trocar mensagens.

## *Estrutura do Arquivo*
O projeto utiliza o padrão de design MVC (Model-View-Controller) como base, onde cada camada é organizada de forma clara e separada para facilitar a manutenção e a escalabilidade do sistema. A estrutura foi definida da seguinte maneira:

- Model: Representado pela pasta `model`, contém as classes responsáveis pela lógica de negócio e manipulação de dados.

- View: Implementada na pasta `tela`, é responsável pela interface gráfica e interação com o usuário.

- Controller: Localizado na pasta `sistema`, gerencia a comunicação entre o Model e a View, orquestrando as operações do sistema.

- Banco de Dados: Localizado na pasta `database`, aonde os arquivos `.txt` ficam armazenados.

## *Configuração do Projeto*

- Certifique-se de que você tenha uma versão da JDK compatível instalada no seu sistema.

- Após garantir isso, abra o prompt de comando e execute o seguinte comando para iniciar o projeto:

`java -jar DiretorioDoArquivo\monitoria-main\out\artifacts\organizadorDeGruposDeEstudos_jar\organizadorDeGruposDeEstudos.jar`

Substitua `DiretorioDoArquivo` pelo diretório onde o arquivo `.jar` foi salvo.

## Iniciando o Projeto

### Tela De Login

Assim que o projeto for iniciado, ele abrirá diretamente na tela de login. Nessa tela, você poderá fazer login, caso já tenha uma conta, ou realizar um novo cadastro, caso ainda não seja registrado.

### Tela de Cadastramento

O processo de cadastro oferece a opção de se registrar como aluno ou professor, conforme sua função.

#### Tela de Cadastramento de Aluno

A tela de cadastro de aluno solicita as seguintes informações: nome completo, e-mail (deve ser o e-mail acadêmico com o domínio @academico.ifpb.edu.br), senha, matrícula (composta por 12 dígitos numéricos), curso, período, tipo de período (representado por semestre ou ano) e o CRE (Coeficiente de Rendimento Escolar, variando de 0 a 100).

#### Tela de Cadastramento de Professor

A tela de cadastro de professor solicita as seguintes informações: nome completo, e-mail (deve ser o e-mail acadêmico com o domínio @ifpb.edu.br), senha e as disciplinas que o professor leciona.

### Tela Principal

Após realizar o login, você será direcionado para a tela principal, que apresenta quatro opções: "Criar Grupo", "Entrar no Grupo", "Ver Grupos" e "Sair".

### Tela de Criação de Grupo

Na tela de criação de grupo, você poderá definir o nome do grupo, a matéria a ser estudada, o local do encontro, e selecionar os horários disponíveis entre 07h e 18h. Além disso, será gerado automaticamente um código exclusivo de 6 dígitos alfanuméricos para o grupo. Após preencher todas as informações, basta confirmar a criação do grupo, que ficará disponível para outros participantes se juntarem posteriormente.

### Tela Entrar No Grupo

Na tela de "Entrar no Grupo", você deverá inserir o código alfanumérico de 6 dígitos fornecido pelo criador do grupo para se juntar a ele. Após a validação do código, seu nome será adicionado à lista de participantes do grupo. Se o código for inválido ou o grupo não existir, uma mensagem de erro será exibida.

### Tela Ver Grupo

Na tela de "Ver Grupos", você poderá visualizar uma lista dos grupos dos quais está participando. Selecione um grupo da lista para abrir o chat associado ou para ver mais informações sobre o grupo. Caso não haja grupos disponíveis, uma mensagem informará que você não está participando de nenhum grupo no momento.

### Tela de Chat

Na tela de "Chat", você poderá interagir com os participantes do grupo selecionado. A interface exibe uma área de conversa onde todas as mensagens trocadas no grupo são exibidas em ordem cronológica. Abaixo da área de conversa, há um campo de texto para digitar novas mensagens e um botão para enviá-las. Todas as mensagens são salvas e persistidas para garantir que a conversa esteja disponível em futuras sessões. Além disso, a tela de chat mostra o nome dos participantes e permite visualizar as mensagens enviadas por cada um deles.

### Botão Sair

Ao clicar no botão "Sair", você será desconectado do sistema e retornará à tela de login. Esse processo encerra a sessão atual e garante que suas informações de login não permaneçam acessíveis até que você se autentique novamente. Certifique-se de salvar qualquer trabalho ou informação importante antes de sair para evitar a perda de dados.

# Projeto Arquétipo Maven RESTful Spring Boot

Este repositório contém um projeto arquétipo Maven para serviços RESTful usando Spring Boot.

## Descrição

Você já se viu na situação de ter que iniciar um projeto e não saber por onde começar? Ou você sabe como iniciar o projeto, mas já notou que precisa sempre criar projetos similares e acaba por criar cada projeto de uma forma? Uma forma elegante de resolver esse problema é utilizando Arquétipos do Maven.

## Como Usar

### 1. Instalando o Arquétipo

Antes de criar projetos usando o arquétipo, é necessário instalá-lo localmente em seu ambiente. Siga as etapas abaixo:

1. Abra o projeto `spring-boot-restful-archetype` em seu ambiente de desenvolvimento.

2. Execute o seguinte comando Maven para instalar o arquétipo no repositório local do Maven (`.m2`):

   ```shell
   mvn clean install
   ```

Isso garantirá que o arquétipo esteja disponível para uso posterior na criação de projetos.

### 2. Instalando o Arquétipo

Criando um Projeto Usando o Arquétipo
Agora que o arquétipo está instalado em seu ambiente, você pode criar projetos com base nele. Siga as etapas abaixo:

1. Escolha um novo diretório onde deseja criar o projeto.

2. Execute o seguinte comando Maven, substituindo as informações apropriadas, como Group, Artifact, Name, Description e Package Name:

  ```shell
   mvn archetype:generate -DarchetypeGroupId=br.com -DarchetypeArtifactId=servicorestfularchetype -DarchetypeVersion=0.0.1-SNAPSHOT -DgroupId=com.exemplo -DartifactId=lista-de-tarefas-spring-boot -Dversion=1.0.0-SNAPSHOT -Ddescription="Aplicativo de lista de tarefas simples" -Dpackage=com.exemplo.listadetarefasspringboot
   ```

  Após executar o comando acima, o Maven criará um novo projeto com base no arquétipo, usando as informações fornecidas. Você pode agora desenvolver as funcionalidades da sua aplicação seguindo o modelo de projeto gerado.

  Certifique-se de personalizar os valores de acordo com o seu projeto.

  Exemplo de Entrada de Comando
  Group: com.exemplo
  Artifact: lista-de-tarefas-spring-boot
  Name: Lista de Tarefas Spring Boot
  Description: Aplicativo de lista de tarefas simples
  Package Name: com.exemplo.listadetarefasspringboot

### 3. Imagens
Aqui estão algumas capturas de tela do projeto do arquétipo em ação:

1. **Disponibilizar Arquetípo no Maven .m2**

   ![Salvar na pasta .m2 o novo modelo de Arquetípo](/readme_images/salvar-modelo-arquetipo-m2.png)

2. **Usando Arquetípo Disponível no .m2 Criar Novo Projeto(PUT)**

   ![Gerar novo projeto com base no Arquetípo](/readme_images/projeto-arquetipo-gerado.png)

### 4. Estrutura do Projeto
A estrutura do projeto inclui os seguintes componentes:

- Arquivos de configuração do Maven.
- Código-fonte Java para serviços RESTful.
- Testes de exemplo.

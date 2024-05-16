# FollowMap

FollowMap é uma aplicação Android de Controle de Endereços, permitindo aos usuários gerenciar registros de usuários, cidades e endereços com o uso do banco de dados Room.

## Funcionalidades

- Registro de usuários
- Login de usuários cadastrados
- Registro de cidades após o login do usuário
- Registro de endereços após o login do usuário
- Operações CRUD (Criar, Ler, Atualizar, Excluir) em todas as tabelas (Usuário, Cidade, Endereço)
- Inserção de endereços com seleção de cidade a partir de uma lista existente
- Visualização de endereços em um mapa usando o Google Maps

## Estrutura do Banco de Dados

### Tabela `Usuario`

- `usuarioId` (chave primária)
- `nome`
- `email`
- `senha`

### Tabela `Cidade`

- `cidadeId` (chave primária)
- `cidade`
- `estado`

### Tabela `Endereco`

- `enderecoId` (chave primária)
- `descricao`
- `latitude`
- `longitude`
- `cidadeId` (chave estrangeira referenciando `Cidade`)

## Requisitos e Observações

- Validação de email e senha durante o login, com consulta ao banco de dados para verificação
- Verificação de campos obrigatórios e validação de formatos durante o registro e atualização de usuários
- Controle de integridade referencial ao excluir ou atualizar cidades (verificar se existem endereços associados)
- Listagem de usuários, cidades e endereços em list views para fácil visualização
- Integração com Google Maps para visualização de endereços em um mapa interativo

## Recursos Utilizados

- **Room Database:** Implementação do banco de dados local para persistência dos dados
- **Google Maps API:** Integração para visualização dos endereços em um mapa interativo

## Como Executar o Projeto

1. Clone o repositório do GitHub.
2. Abra o projeto no Android Studio.
3. Conecte um dispositivo Android ou inicie um emulador.
4. Execute o aplicativo no dispositivo/emulador.

## Contribuição

Sinta-se à vontade para contribuir com melhorias, correções ou novas funcionalidades para este projeto. Caso tenha dúvidas ou sugestões, abra uma issue para discussão.

---

**Nota:** Este projeto foi desenvolvido como parte de um trabalho acadêmico na disciplina de Programação para Dispositivos Móveis.

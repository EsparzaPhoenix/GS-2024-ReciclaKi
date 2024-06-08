## ReciclaKi

## Integrantes 

RM97887 - João Carlos França Figueiredo 

RM550200 - Leonardo Oliveira Esparza 

RM552421 - Flavio Sousa Vasconcelos 

RM552368 - Wellington de Oliveira Urcino Da Silva 

# Resumo
Nosso projeto desenvolve uma plataforma inovadora para facilitar a gestão de resíduos, conectando geradores de resíduos, catadores e pontos de coleta em uma rede eficiente e sustentável. Esta plataforma oferece uma maneira fácil e eficaz de gerenciar o descarte e a coleta de resíduos, promovendo a reciclagem e a sustentabilidade ambiental.

# Diagrama de Entidade e Relacionamento

![image](https://github.com/EsparzaPhoenix/GS-2024-ReciclaKi/assets/92988596/f747d4ec-7be1-41b7-ab9f-d8ce9e2baa81)


# Links dos Videos

### [Demonstração do Software](https://youtu.be/MxYeN_fcdho?si=oYneT0ZE9z2Y-s3N)

### [Vídeo Pitch](https://youtu.be/P3jglTtu3WU?si=lk3ihqUPG8avGlL1)


# Instrução de como rodar a aplicação
*    Clique no botão CODE destacado em verde e faça o download do arquivo ZIP da aplicação.
*    Descompacte o arquivo e abra o projeto em uma IDE, de preferência o Intellij.
*    As dependências serão automaticamente baixadas pelo Maven.
*    Inicie a aplicação clicando no botão play no topo da IDE.
*    Abra a aplicação de testes de API (Insomnia, Postman) disponível em seu computador.
*    Na barra de endereço, coloque a URL "http://localhost:8080/" e adicione o endpoint escolhido para teste, de acordo com a documentação.

# Rotas

CidadeController

    Cadastro de Cidade
        Método: POST
        Rota: /cidades
        
    Listar Cidades
        Método: GET
        Rota: /cidades
        
    Buscar Cidade por ID
        Método: GET
        Rota: /cidades/{id}
        
    Atualizar Cidade
        Método: PUT
        Rota: /cidades/{id}
        
    Remover Cidade
        Método: DELETE
        Rota: /cidades/{id}

CooperadoController

    Cadastro de Cooperado
        Método: POST
        Rota: /cooperados
        
    Listar Cooperados
        Método: GET
        Rota: /cooperados
        
    Buscar Cooperado por ID
        Método: GET
        Rota: /cooperados/{id}
        
    Atualizar Cooperado
        Método: PUT
        Rota: /cooperados/{id}
        
    Remover Cooperado
        Método: DELETE
        Rota: /cooperados/{id}

CooperativaController

    Cadastro de Cooperativa
        Método: POST
        Rota: /cooperativas
        
    Listar Cooperativas
        Método: GET
        Rota: /cooperativas
    Buscar Cooperativa por ID
        Método: GET
        Rota: /cooperativas/{id}
        
    Atualizar Cooperativa
        Método: PUT
        Rota: /cooperativas/{id}
        
    Remover Cooperativa
        Método: DELETE
        Rota: /cooperativas/{id}

EnderecoController

    Cadastro de Endereço
        Método: POST
        Rota: /enderecos
        
    Listar Endereços
        Método: GET
        Rota: /enderecos
    Buscar Endereço por ID
    
        Método: GET
        Rota: /enderecos/{id}
        
    Remover Endereço
        Método: DELETE
        Rota: /enderecos/{id}

PedidoController

    Cadastro de Pedido
        Método: POST
        Rota: /pedidos
        
    Listar Pedidos
        Método: GET
        Rota: /pedidos
        
    Buscar Pedido por ID
        Método: GET
        Rota: /pedidos/{id}
        
    Atualizar Pedido
        Método: PUT
        Rota: /pedidos/{id}
        
    Remover Pedido
        Método: DELETE
        Rota: /pedidos/{id}
        
    Buscar Pedidos por Peso
        Método: GET
        Rota: /pedidos/por-peso
        
    Buscar Pedidos por Quantidade
        Método: GET
        Rota: /pedidos/por-quantidade
        
    Buscar Pedidos por Data
        Método: GET
        Rota: /pedidos/por-data

ProdutoController

    Cadastro de Produto
        Método: POST
        Rota: /produtos
        
    Listar Produtos
        Método: GET
        Rota: /produtos
        
    Buscar Produto por ID
        Método: GET
        Rota: /produtos/{id}
        
    Atualizar Produto
        Método: PUT
        Rota: /produtos/{id}
        
    Remover Produto
        Método: DELETE
        Rota: /produtos/{id}
        
    Buscar Produtos por Material
        Método: GET
        Rota: /produtos/por-material

UsuarioController

    Cadastro de Usuário
        Método: POST
        Rota: /usuarios
        
    Listar Usuários
        Método: GET
        Rota: /usuarios
        
    Buscar Usuário por ID
        Método: GET
        Rota: /usuarios/{id}
        
    Atualizar Usuário
        Método: PUT
        Rota: /usuarios/{id}
        
    Remover Usuário
        Método: DELETE
        Rota: /usuarios/{id}
        
    Buscar Usuários por Nome
        Método: GET
        Rota: /usuarios/por-nome

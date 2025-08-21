-- Criação das tabelas
CREATE DATABASE LojaOnline;
USE LojaOnline;

CREATE TABLE Categorias (
    id_categoria INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL
);

CREATE TABLE Produtos (
    id_produto INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    preco DECIMAL(10,2) NOT NULL,
    id_categoria INT,
    FOREIGN KEY (id_categoria) REFERENCES Categorias(id_categoria)
);

CREATE TABLE Clientes (
    id_cliente INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    cidade VARCHAR(100),
    estado VARCHAR(50)
);

CREATE TABLE Pedidos (
    id_pedido INT AUTO_INCREMENT PRIMARY KEY,
    id_cliente INT,
    data_pedido DATE,
    FOREIGN KEY (id_cliente) REFERENCES Clientes(id_cliente)
);

CREATE TABLE ItensPedido (
    id_item INT AUTO_INCREMENT PRIMARY KEY,
    id_pedido INT,
    id_produto INT,
    quantidade INT,
    FOREIGN KEY (id_pedido) REFERENCES Pedidos(id_pedido),
    FOREIGN KEY (id_produto) REFERENCES Produtos(id_produto)
);

-- Categorias
INSERT INTO Categorias (nome) VALUES
('Eletrônicos'), ('Livros'), ('Roupas'), ('Alimentos');

-- Produtos
INSERT INTO Produtos (nome, preco, id_categoria) VALUES
('Notebook', 3500.00, 1),
('Smartphone', 2500.00, 1),
('Livro SQL', 80.00, 2),
('Livro Java', 90.00, 2),
('Camiseta', 50.00, 3),
('Calça Jeans', 120.00, 3),
('Arroz 5kg', 25.00, 4),
('Feijão 1kg', 10.00, 4);

-- Clientes
INSERT INTO Clientes (nome, cidade, estado) VALUES
('Ana', 'São Paulo', 'SP'),
('Bruno', 'Rio de Janeiro', 'RJ'),
('Carla', 'Belo Horizonte', 'MG'),
('Diego', 'São Paulo', 'SP'),
('Fernanda', 'Curitiba', 'PR');

-- Pedidos
INSERT INTO Pedidos (id_cliente, data_pedido) VALUES
(1, '2025-08-01'),
(2, '2025-08-02'),
(3, '2025-08-03'),
(1, '2025-08-04'),
(4, '2025-08-05');

-- Itens dos pedidos
INSERT INTO ItensPedido (id_pedido, id_produto, quantidade) VALUES
(1, 1, 1), -- Ana comprou 1 Notebook
(1, 3, 2), -- Ana comprou 2 Livros SQL
(2, 2, 1), -- Bruno comprou 1 Smartphone
(2, 5, 3), -- Bruno comprou 3 Camisetas
(3, 7, 2), -- Carla comprou 2 Arroz
(3, 8, 5), -- Carla comprou 5 Feijão
(4, 4, 1), -- Ana comprou 1 Livro Java
(4, 6, 2), -- Ana comprou 2 Calças Jeans
(5, 2, 1), -- Diego comprou 1 Smartphone
(5, 5, 1); -- Diego comprou 1 Camiseta

/*
Fáceis
	1.	Mostrar quantos pedidos cada cliente fez.
	2.	Mostrar quantos produtos existem em cada categoria.
	3.	Mostrar a soma das quantidades vendidas de cada produto.

Médios
	4.	Mostrar o total gasto por cada cliente em todos os pedidos.
	5.	Mostrar o faturamento total por categoria.
	6.	Mostrar a quantidade de pedidos feitos em cada cidade.

Difíceis
	7.	Mostrar o produto mais vendido (em quantidade).
	8.	Mostrar a média de valor dos pedidos de cada cliente.
	9.	Mostrar o cliente que mais gastou em toda a loja.

Desafiador (nível alto 🚀)
	10.	Mostrar o faturamento mensal por categoria, agrupando por mês/ano e categoria (mesmo que o banco ainda tenha dados de apenas um mês, a query deve ser genérica para vários).

Last: Faturamento mensal por categoria
Liste o faturamento total por categoria, agrupado por mês e ano.
*/

-- 1
SELECT COUNT(pedidos.id_pedido) AS 'Quantidade de pedidos', clientes.nome FROM clientes
JOIN pedidos ON clientes.id_cliente  = pedidos.id_cliente 
GROUP BY clientes.nome;
-- 2
SELECT COUNT(produtos.nome) AS 'Quantidade de produtos', categorias.nome
FROM categorias
JOIN produtos ON categorias.id_categoria = produtos.id_categoria
GROUP BY categorias.nome;
-- 3 
SELECT SUM(itenspedido.quantidade) AS 'Soma pedidos', produtos.nome 
FROM produtos 
JOIN itenspedido ON produtos.id_produto = itenspedido.id_produto
GROUP BY produtos.nome;
-- 4 
SELECT SUM(produtos.preco), pedidos.id_pedido, clientes.nome
FROM clientes
JOIN pedidos ON clientes.id_cliente = pedidos.id_cliente
JOIN itenspedido ON pedidos.id_pedido = itenspedido.id_pedido
JOIN produtos ON itenspedido.id_produto = produtos.id_produto
GROUP BY pedidos.id_pedido;
-- 5
SELECT categorias.nome AS categoria, SUM(produtos.preco * itenspedido.quantidade) AS faturamento_total
FROM categorias
JOIN produtos ON categorias.id_categoria = produtos.id_categoria
JOIN itenspedido ON produtos.id_produto = itenspedido.id_produto
JOIN pedidos ON itenspedido.id_pedido = pedidos.id_pedido
GROUP BY categorias.nome;
-- 6 
SELECT clientes.cidade, COUNT(DISTINCT pedidos.id_pedido) AS quantidade_pedidos
FROM pedidos
JOIN clientes ON pedidos.id_cliente = clientes.id_cliente
GROUP BY clientes.cidade;
-- 7 
SELECT produtos.nome, SUM(itenspedido.quantidade) AS total_vendido
FROM produtos
JOIN itenspedido ON produtos.id_produto = itenspedido.id_produto
GROUP BY produtos.nome
ORDER BY total_vendido DESC LIMIT 1;
-- 8 
WITH valor_pedidos AS (
	SELECT clientes.id_cliente, clientes.nome, SUM(produtos.preco * itenspedido.quantidade) AS valor_total
	FROM pedidos
    JOIN itenspedido ON pedidos.id_pedido = itenspedido.id_pedido
    JOIN produtos ON itenspedido.id_produto = produtos.id_produto
	GROUP BY pedidos.id_pedido, pedidos.id_cliente
)
SELECT clientes.nome AS cliente, AVG(valor_pedidos.valor_total) AS media_valor_pedidos
FROM valor_pedidos
JOIN clientes ON valor_pedidos.id_cliente = clientes.id_cliente
GROUP BY clientes.nome;
-- 9 
SELECT clientes.nome, SUM(produtos.preco * itenspedido.quantidade) AS valor_total
FROM pedidos
JOIN itenspedido ON pedidos.id_pedido = itenspedido.id_pedido
JOIN produtos ON itenspedido.id_produto = produtos.id_produto
JOIN clientes ON pedidos.id_cliente = clientes.id_cliente
GROUP BY clientes.nome
ORDER BY valor_total DESC LIMIT 1;
-- 10
SELECT MONTH (pedidos.data_pedido) AS mes, categorias.nome
FROM pedidos 
JOIN 

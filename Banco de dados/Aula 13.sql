-- Criação do banco de dados
CREATE DATABASE IF NOT EXISTS LojaMagica;
USE LojaMagica;

-- Tabela Endereço
CREATE TABLE Endereco (
    id INT AUTO_INCREMENT PRIMARY KEY,
    rua VARCHAR(100),
    numero VARCHAR(10),
    complemento VARCHAR(50),
    bairro VARCHAR(50),
    cidade VARCHAR(50),
    estado VARCHAR(2),
    cep VARCHAR(10)
);

-- Tabela Cliente
CREATE TABLE Cliente (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE,
    telefone VARCHAR(20),
    endereco_id INT,
    FOREIGN KEY (endereco_id) REFERENCES Endereco(id)
);

-- Tabela Categoria
CREATE TABLE Categoria (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    descricao TEXT
);

-- Tabela ProdutosMagicos
CREATE TABLE ProdutosMagicos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    descricao TEXT,
    preco DECIMAL(10,2) NOT NULL,
    estoque INT DEFAULT 0,
    categoria_id INT,
    FOREIGN KEY (categoria_id) REFERENCES Categoria(id)
);

-- Tabela Pedidos
CREATE TABLE Pedidos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cliente_id INT,
    data_pedido DATETIME DEFAULT CURRENT_TIMESTAMP,
    total DECIMAL(10,2),
    status ENUM('Pendente', 'Processando', 'Enviado', 'Entregue', 'Cancelado') DEFAULT 'Pendente',
    FOREIGN KEY (cliente_id) REFERENCES Cliente(id)
);

-- Tabela de associação: Itens do Pedido (Produtos em cada pedido)
CREATE TABLE Pedido_Produto (
    pedido_id INT,
    produto_id INT,
    quantidade INT NOT NULL,
    preco_unitario DECIMAL(10,2) NOT NULL,
    PRIMARY KEY (pedido_id, produto_id),
    FOREIGN KEY (pedido_id) REFERENCES Pedidos(id),
    FOREIGN KEY (produto_id) REFERENCES ProdutosMagicos(id)
);

INSERT INTO Endereco (rua, numero, complemento, bairro, cidade, estado, cep) VALUES
('Rua do Grifo', '101', '', 'Ninho dos Ventos', 'Mysticville', 'MG', '12345-005'),
('Av. das Poções', '202', '', 'Poente', 'Mysticville', 'MG', '12345-006'),
('Rua dos Magos', '303', 'Ap 2', 'Centro Mágico', 'Mysticville', 'MG', '12345-007'),
('Travessa das Runas', '404', '', 'Alto Feitiço', 'Mysticville', 'MG', '12345-008'),
('Caminho Estelar', '505', '', 'Estação Astral', 'Mysticville', 'MG', '12345-009'),
('Rua dos Feiticeiros', '606', '', 'Colina Mística', 'Mysticville', 'MG', '12345-010'),
('Alameda Oculta', '707', '', 'Beco Sombrio', 'Mysticville', 'MG', '12345-011'),
('Av. do Encantamento', '808', 'Casa 3', 'Bosque Esmeralda', 'Mysticville', 'MG', '12345-012'),
('Rua do Cristal', '909', '', 'Vale Claro', 'Mysticville', 'MG', '12345-013'),
('Rua da Fênix', '111', '', 'Renascença', 'Mysticville', 'MG', '12345-014');

INSERT INTO Cliente (nome, email, telefone, endereco_id) VALUES
('Elena Cristalina', 'elena@magia.com', '99999-0006', 6),
('Draco Nocturno', 'draco@magia.com', '99999-0007', 7),
('Celeste Astral', 'celeste@magia.com', '99999-0008', 8),
('Thorin Flamejante', 'thorin@magia.com', '99999-0009', 9),
('Aria da Luz', 'aria@magia.com', '99999-0010', 10),
('Balthazar Sombrio', 'balthazar@magia.com', '99999-0011', 11),
('Helga Verdejante', 'helga@magia.com', '99999-0012', 12),
('Igor Tempestuoso', 'igor@magia.com', '99999-0013', 13),
('Yara do Mar', 'yara@magia.com', '99999-0014', 14),
('Cedric Dourado', 'cedric@magia.com', '99999-0015', 15);

INSERT INTO Categoria (nome, descricao) VALUES
('Instrumentos', 'Itens usados em rituais'),
('Tônicos', 'Poções de uso contínuo'),
('Relíquias', 'Itens raríssimos e valiosos'),
('Invocação', 'Itens e livros sobre invocações'),
('Transfiguração', 'Itens relacionados à mudança de forma'),
('Ilusões', 'Itens que manipulam a percepção'),
('Necromancia', 'Itens ligados à morte e espíritos'),
('Elementais', 'Magias de fogo, terra, água, ar'),
('Tempo', 'Magias temporais'),
('Mentais', 'Magias da mente e psíquicas');

INSERT INTO ProdutosMagicos (nome, descricao, preco, estoque, categoria_id) VALUES
('Espelho da Verdade', 'Mostra o que é oculto', 250.00, 4, 6),
('Relógio do Tempo', 'Permite ver o futuro próximo', 800.00, 1, 9),
('Poção da Memória', 'Restaura lembranças perdidas', 70.00, 10, 2),
('Bastão Elemental', 'Conjura elementos da natureza', 450.00, 3, 8),
('Livro de Invocação Menor', 'Rituais de invocação básicos', 120.00, 5, 4),
('Amuleto da Alma', 'Protege contra possessão', 180.00, 2, 7),
('Máscara da Ilusão', 'Cria uma aparência falsa', 200.00, 7, 6),
('Poção Energética', 'Restaura mana rapidamente', 55.00, 25, 2),
('Grimório de Transfiguração', 'Transformações mágicas', 275.00, 3, 5),
('Tiara de Concentração', 'Aumenta o foco mental', 150.00, 6, 10);

INSERT INTO Pedidos (cliente_id, data_pedido, total, status) VALUES
(6, '2025-08-01 09:00:00', 250.00, 'Entregue'),
(7, '2025-08-02 10:15:00', 70.00, 'Processando'),
(8, '2025-08-03 13:20:00', 450.00, 'Enviado'),
(9, '2025-08-04 15:00:00', 275.00, 'Pendente'),
(10, '2025-08-05 17:45:00', 120.00, 'Cancelado'),
(6, '2025-08-06 08:30:00', 180.00, 'Entregue'),
(7, '2025-08-07 12:00:00', 200.00, 'Entregue'),
(8, '2025-08-08 14:10:00', 55.00, 'Enviado'),
(9, '2025-08-09 16:30:00', 150.00, 'Processando'),
(10, '2025-08-10 19:00:00', 800.00, 'Pendente');

INSERT INTO Pedido_Produto (pedido_id, produto_id, quantidade, preco_unitario) VALUES
(6, 11, 1, 250.00),  -- Espelho da Verdade
(7, 13, 1, 70.00),   -- Poção da Memória
(8, 14, 1, 450.00),  -- Bastão Elemental
(9, 19, 1, 275.00),  -- Grimório de Transfiguração
(10, 15, 1, 120.00), -- Livro de Invocação Menor
(11, 6, 2, 80.00),   -- Cristal da Luz
(11, 8, 1, 60.00),   -- Poção de Velocidade
(12, 17, 1, 200.00), -- Máscara da Ilusão
(13, 18, 2, 55.00),  -- Poção Energética
(14, 20, 1, 150.00), -- Tiara de Concentração
(15, 12, 1, 800.00), -- Relógio do Tempo
(6, 1, 1, 25.50),
(7, 5, 1, 150.00),
(8, 3, 1, 300.00),
(9, 10, 1, 220.00),
(10, 4, 1, 500.00),
(11, 2, 1, 120.00),
(12, 7, 1, 45.00),
(13, 9, 1, 35.00),
(14, 16, 1, 180.00); -- Amuleto da Alma

SELECT * FROM categoria;

-- 1
SELECT pedidos.cliente_id, cliente.nome 
FROM pedidos
JOIN cliente ON pedidos.cliente_id = cliente.id
JOIN endereco ON cliente.endereco_id = endereco.id
WHERE endereco.cidade = 'Guaramirealm';

-- 2
SELECT produtosmagicos.nome, categoria.nome 
FROM produtosmagicos
JOIN categoria ON produtosmagicos.categoria_id = categoria_id;

-- 3
SELECT preco, nome 
FROM produtosmagicos
ORDER BY preco DESC LIMIT 1;

-- 4
SELECT categoria.nome AS categoria, COUNT(produtosmagicos.id) AS quantidade_produtos
FROM produtosmagicos
JOIN categoria ON produtosmagicos.categoria_id = categoria.id
GROUP BY categoria.id, categoria.nome;

-- 5
SELECT cliente.nome, produtosmagicos.id AS produto_id, categoria.nome AS categoria_nome
FROM cliente
JOIN pedidos ON cliente.id = pedidos.cliente_id
JOIN pedido_produto ON pedidos.id = pedido_produto.pedido_id
JOIN produtosmagicos ON pedido_produto.produto_id = produtosmagicos.id
JOIN categoria ON produtosmagicos.categoria_id = categoria.id
WHERE categoria.nome = 'Instrumentos';

-- 6
SELECT endereco.bairro, cliente.nome, produtosmagicos.preco
FROM cliente
JOIN endereco ON cliente.endereco_id = endereco.id
JOIN pedidos ON cliente.id = pedidos.cliente_id
JOIN pedido_produto ON pedido_produto.pedido_id = pedidos.id
JOIN produtosmagicos ON pedido_produto.produto_id = produtosmagicos.id
WHERE pedido_produto.preco_unitario >= 100;

-- 7 
SELECT DISTINCT pedidos.*
FROM pedidos
JOIN pedido_produto ON pedido_produto.pedido_id = pedidos.id
JOIN produtosmagicos ON pedido_produto.produto_id = produtosmagicos.id
JOIN categoria ON produtosmagicos.categoria_id = categoria.id
WHERE categoria.nome = 'Ingredientes';

-- 8 
SELECT produtosmagicos.*
FROM produtosmagicos
LEFT JOIN pedido_produto ON produtosmagicos.id = pedido_produto.produto_id
WHERE pedido_produto.produto_id IS NULL;

-- 9 
SELECT pedidos.id AS pedido_id, produtosmagicos.nome AS produto_nome, categoria.nome AS categoria_nome
FROM pedidos
JOIN pedido_produto ON pedido_produto.pedido_id = pedidos.id
JOIN produtosmagicos ON pedido_produto.produto_id = produtosmagicos.id
JOIN categoria ON produtosmagicos.categoria_id = categoria.id;



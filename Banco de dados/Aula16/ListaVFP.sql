-- Banco de dados
CREATE DATABASE IF NOT EXISTS FabricaCarros;
USE FabricaCarros;

-- Tabela Fabricante
CREATE TABLE Fabricante (
    id_fabricante INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(50),
    pais VARCHAR(50),
    ano_fundacao INT
);

INSERT INTO Fabricante (nome, pais, ano_fundacao) VALUES
('AutoLux', 'Alemanha', 1980),
('CarMaster', 'Brasil', 1995),
('SpeedTech', 'EUA', 2005);

-- Tabela Carro
CREATE TABLE Carro (
    id_carro INT AUTO_INCREMENT PRIMARY KEY,
    modelo VARCHAR(50),
    ano INT,
    cor VARCHAR(30),
    preco DECIMAL(10,2),
    id_fabricante INT,
    quantidade_estoque INT,
    FOREIGN KEY (id_fabricante) REFERENCES Fabricante(id_fabricante)
);

INSERT INTO Carro (modelo, ano, cor, preco, id_fabricante, quantidade_estoque) VALUES
('Lux A1', 2023, 'Preto', 120000.00, 1, 15),
('Lux B2', 2022, 'Branco', 95000.00, 1, 8),
('Master X', 2023, 'Prata', 80000.00, 2, 20),
('Speed Z', 2024, 'Vermelho', 150000.00, 3, 5);

-- Tabela Funcionario
CREATE TABLE Funcionario (
    id_funcionario INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100),
    cargo VARCHAR(50),
    salario DECIMAL(10,2),
    data_admissao DATE
);

INSERT INTO Funcionario (nome, cargo, salario, data_admissao) VALUES
('João Silva', 'Operador', 3000.00, '2020-03-15'),
('Maria Souza', 'Engenheira', 8000.00, '2019-07-10'),
('Carlos Lima', 'Operador', 3200.00, '2021-05-01');

-- Tabela Venda
CREATE TABLE Venda (
    id_venda INT AUTO_INCREMENT PRIMARY KEY,
    id_carro INT,
    cliente_nome VARCHAR(100),
    cliente_cpf VARCHAR(20),
    data_venda DATE,
    quantidade INT,
    valor_total DECIMAL(10,2),
    FOREIGN KEY (id_carro) REFERENCES Carro(id_carro)
);

INSERT INTO Venda (id_carro, cliente_nome, cliente_cpf, data_venda, quantidade, valor_total) VALUES
(1, 'Lucas Pereira', '123.456.789-00', '2025-08-01', 1, 120000.00),
(3, 'Ana Costa', '987.654.321-00', '2025-08-02', 2, 160000.00);

-- Tabela Producao
CREATE TABLE Producao (
    id_producao INT AUTO_INCREMENT PRIMARY KEY,
    id_carro INT,
    id_funcionario INT,
    data_producao DATE,
    quantidade INT,
    FOREIGN KEY (id_carro) REFERENCES Carro(id_carro),
    FOREIGN KEY (id_funcionario) REFERENCES Funcionario(id_funcionario)
);

INSERT INTO Producao (id_carro, id_funcionario, data_producao, quantidade) VALUES
(1, 1, '2025-08-01', 5),
(2, 3, '2025-08-01', 3),
(3, 2, '2025-08-02', 4);


/*
1) Criar uma view que mostre todos os carros com estoque maior que 10 unidades e seu fabricante.

2) Criar uma view que mostre o total de vendas por carro, incluindo o nome do carro, modelo e quantidade vendida.

3) Criar uma function que retorne o valor médio de venda de um carro específico.

4) Criar uma function que receba um ID de funcionário e retorne a quantidade total de carros que ele produziu.

5) Criar uma procedure que insira uma nova venda e atualize automaticamente o estoque do carro correspondente.

6) Criar uma procedure que liste todos os funcionários que produziram mais de X carros em um determinado mês.

7) Criar uma procedure que atualize o preço de todos os carros de um determinado fabricante, aumentando em Y%.

8) Criar uma view que mostre os funcionários e o total de produção que cada um realizou por mês.

9) Criar uma procedure que retorne os carros que não foram vendidos nos últimos 6 meses.

EVENTOS

Criar um evento que a cada dia às 00:00 verifique os carros com estoque menor que 5 e insira uma notificação em uma tabela Alerta_Estoque.

Criar um evento que a cada semana calcule a média de vendas da semana anterior e registre em uma tabela Resumo_Semanal_Vendas.

Criar um evento que a cada mês gere um relatório automático de produção por funcionário e insira na tabela Resumo_Mensal_Producao.
*/


-- 1 Criar uma view que mostre todos os carros com estoque maior que 10 unidades e seu fabricante.

CREATE VIEW vw_estoque_maior10 AS
SELECT carro.quantidade_estoque, fabricante.nome
FROM carro
JOIN fabricante ON carro.id_fabricante = fabricante.id_fabricante
WHERE carro.quantidade_estoque >= 10;

SELECT * FROM vw_estoque_maior10;


-- 2 Criar uma view que mostre o total de vendas por carro, incluindo o nome do carro, modelo e quantidade vendida.

CREATE VIEW vw_total_vendas AS 
SELECT venda.quantidade AS 'Vendas', fabricante.nome AS 'Nome', carro.modelo AS 'Modelo'
FROM venda
JOIN carro ON venda.id_carro = carro.id_carro
JOIN fabricante ON carro.id_fabricante = fabricante.id_fabricante;

select * from vw_total_vendas;


-- 3 Criar uma function que retorne o valor médio de venda de um carro específico.

DELIMITER //

CREATE FUNCTION fn_valor_medio(id_carro INT)
RETURNS DOUBLE
DETERMINISTIC
BEGIN
	DECLARE media_venda DOUBLE;
    
    SELECT media_venda = ((venda.valor_total * venda.quantidade) * COUNT(vendas.id_carro)) INTO media_venda
	FROM venda
	GROUP BY venda.id_carro;
	
    RETURN (media_venda);
END;

//
DELIMITER ;

SELECT carro.modelo, fn_valor_medio(id_carro) AS 'Media venda'
FROM carro
JOIN venda ON carro.id_carro = venda.id_carro
WHERE id_carro LIKE 1;
	
    
-- 4 Criar uma function que receba um ID de funcionário e retorne a quantidade total de carros que ele produziu.

DELIMITER //

CREATE FUNCTION fn_quantidade_carros_func(id_funcionario INT)
RETURNS INT 
DETERMINISTIC
BEGIN
	DECLARE total_carros INT 
    
    SELECT funcionario.id_funcionario AS 'Funcionario', SUM(carro.id_carro) AS 'Quantidade de carros'
	FROM funcionario
    JOIN producao ON funcionario.id_funcionario =  producao.id_funcionario
    GROUP BY carro.id_carro;

	RETURN(total_carros);
END;

DELIMITER ;


-- 5 Criar uma procedure que insira uma nova venda e atualize automaticamente o estoque do carro correspondente.
/*
DELIMITER //

CREATE FUNCTION fn_nova_venda
RETURNS INT
DETERMINISTIC
BEGIN 


END; 

//
DELIMITER ;

*/
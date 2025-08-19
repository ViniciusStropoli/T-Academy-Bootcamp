/*use animais;

SELECT * FROM Animal;

SELECT * FROM Consulta;

SELECT * FROM Especie;

-- 1
SELECT nome, altura FROM Animal WHERE especie_id = 3;
SELECT a.nome, a.altura
FROM Animal a
JOIN Especie e ON a.especie_id = e.id
WHERE e.nomeEspecie = 'Pássaro';
-- 2
SELECT nome, idade FROM Animal WHERE nome = "Rex"; 

-- 3
SELECT AVG(idade) AS media_idade FROM animal WHERE especie_id = 1;  
/*SELECT AVG(a.idade) AS media_idade
FROM Animal a
JOIN Especie e ON a.especie_id = e.id
WHERE e.nomeEspecie = 'Cachorro';

-- 4
SELECT MAX(dataHora) AS ultima_consulta FROM consulta  
*/

/*SELECT MAX(c.dataHora) AS ultima_pesagem
FROM Consulta c
JOIN Animal a ON c.nomeAnimal_id = a.id
JOIN Especie e ON a.especie_id = e.id
WHERE e.nomeEspecie = 'Gato' AND a.raça = 'Persa';
*/
/*
INSERT INTO Especie (nomeEspecie) VALUES
('Cachorro'),
('Gato'),
('Pássaro'),
('Coelho'),
('Hamster');

INSERT INTO Animal (nome, altura, idade, descricao, raça, peso, especie_id) VALUES
('Rex', 0.6, 5, 'Cachorro dócil e brincalhão', 'Labrador', 30.5, 1),
('Mimi', 0.25, 3, 'Gata esperta e carinhosa', 'Siamês', 4.2, 2),
('Pipoca', 0.15, 2, 'Coelho branco com orelhas longas', 'Angorá', 2.3, 4),
('Bolinha', 0.1, 1, 'Hamster muito ativo à noite', 'Sírio', 0.2, 5),
('Tico', 0.12, 1, 'Pássaro amarelo que canta bastante', 'Canário', 0.05, 3),
('Thor', 0.7, 4, 'Cachorro protetor e forte', 'Pastor Alemão', 35.0, 1),
('Luna', 0.23, 2, 'Gata curiosa e independente', 'Persa', 3.8, 2);

INSERT INTO Consulta (dataHora, nomeAnimal_id, observações) VALUES
('2025-08-01 10:00:00', 1, 'Vacinação anual realizada com sucesso.'),
('2025-08-02 14:30:00', 2, 'Tratamento para pulgas iniciado.'),
('2025-08-03 09:00:00', 3, 'Exame clínico geral, tudo normal.'),
('2025-08-04 16:00:00', 4, 'Corte de unhas e revisão da dieta.'),
('2025-08-05 11:15:00', 5, 'Verificação de penas, sem anormalidades.'),
('2025-08-06 13:00:00', 6, 'Consulta ortopédica por lesão na pata.'),
('2025-08-07 15:45:00', 7, 'Limpeza de ouvido e vacinação antirrábica.');
*/

/*
CREATE TABLE Especie(
id INT PRIMARY KEY auto_increment,
nomeEspecie VARCHAR(255)
);

CREATE TABLE Animal(
id INT PRIMARY KEY auto_increment,
nome VARCHAR(50),
altura DOUBLE not null,
idade INT not null,
descricao VARCHAR(255),
raça VARCHAR(25) not null,
peso DOUBLE not null,
especie_id INT,

FOREIGN KEY (especie_id) REFERENCES especie(id)	
);

CREATE TABLE Consulta(
id INT PRIMARY KEY auto_increment,
dataHora DATETIME,
nomeAnimal_id INT,
observações VARCHAR(255),

FOREIGN KEY (nomeAnimal_id) REFERENCES Animal(id)
);
*/
/*
-- Tabela do professor		

-- Criar o banco de dados (opcional)
CREATE DATABASE IF NOT EXISTS clinica_veterinaria;
USE clinica_veterinaria;

-- 1. Tabela de espécies
CREATE TABLE especie (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(50) NOT NULL UNIQUE
);

-- 2. Tabela de donos
CREATE TABLE dono (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(100),
  telefone VARCHAR(20),
  email VARCHAR(100),
  endereco TEXT
);

-- 3. Tabela de animais
CREATE TABLE animal (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(50) NOT NULL,
  especie_id INT NOT NULL,
  dono_id INT NOT NULL,
  idade INT,
  peso DECIMAL(5,2),
  altura DECIMAL(5,2),
  observacoes TEXT,
  FOREIGN KEY (especie_id) REFERENCES especie(id),
  FOREIGN KEY (dono_id) REFERENCES dono(id)
);

-- 4. Tabela de consultas
CREATE TABLE consulta (
  id INT AUTO_INCREMENT PRIMARY KEY,
  animal_id INT NOT NULL,
  data_consulta DATE NOT NULL,
  peso_ultimo DECIMAL(5,2),
  observacoes TEXT,
  FOREIGN KEY (animal_id) REFERENCES animal(id)
);

INSERT INTO especie (nome) VALUES 
  ('Cão'),
  ('Gato'),
  ('Pássaro'),
  ('Cobra'),
  ('Iguana'),
  ('Coruja'),
  ('Furão'),
  ('Raposa'),
  ('Chinchila'),
  ('Porquinho-da-índia'),
  ('Peixe'),
  ('Polvo'),
  ('Hamster'),
  ('Corvo');

INSERT INTO dono (nome, telefone, email, endereco) VALUES
  ('João Silva', '11999998888', 'joao@email.com', 'Sítio Boa Esperança'),
  ('Maria Lopes', '21988887777', 'maria@email.com', 'Rua das Palmeiras, 32'),
  ('Carlos Neto', '31997776666', 'carlos@email.com', 'Estrada da Serra, km 7'),
  ('Fernanda Souza', '41996665555', 'fernanda@email.com', 'Fazenda Rio Claro'),
  ('Dr. Estranho', '11912345678', 'estranho@email.com', 'Sanctum Sanctorum, NY');

-- Exemplo com nomes criativos
INSERT INTO animal (nome, especie_id, dono_id, idade, peso, altura, observacoes) VALUES
  ('Rex', 1, 1, 5, 25.0, NULL, 'Latido rouco, tratado com antibiótico.'),
  ('Mittens', 2, 2, 3, 4.5, NULL, 'Perda de apetite; exames solicitados.'),
  ('Cthulhu', 12, 3, 3, 75.0, 1.60, 'Ficava encarando o vazio. Já está morto.'),
  ('Salém', 2, 1, 13, 5.0, NULL, 'Parece ronronar para o vazio.'),
  ('Beelzebark', 1, 4, 6, 2.1, NULL, 'Ansioso, late para espelhos.'),
  ('Glitch', 7, 3, 3, 1.0, NULL, 'Foi encontrado no freezer. Família nega.'),
  ('Balthazar', 14, 5, 9, 1.1, 0.50, 'Aprendeu a gritar "socorro". Veterinário assustado.'),
  ('Soraka', 2, 2, 6, 3.8, NULL, 'Problema nos olhos. Usando colírio.'),
  ('Aloy', 8, 4, 4, 8.2, NULL, 'Vocaliza à noite. Provável cio.'),
  ('Drizzt', 4, 1, 3, 7.1, NULL, 'Muda completa. Animal saudável.');

INSERT INTO consulta (animal_id, data_consulta, peso_ultimo, observacoes) VALUES
  (1, '2025-06-01', 25.0, 'Consulta de rotina.'),
  (2, '2025-07-15', 4.4, 'Febre baixa detectada.'),
  (3, '2025-08-01', 75.0, 'Morto. Encerrado cadastro.'),
  (4, '2025-07-05', 5.0, 'Comportamento estranho, exames normais.'),
  (5, '2025-07-28', 2.1, 'Alta ansiedade, prescrito calmante.'),
  (6, '2025-08-01', 1.0, 'Congelado acidentalmente. Está bem agora.'),
  (7, '2025-07-20', 1.1, 'Falando palavras humanas. Acompanhamento iniciado.'),
  (8, '2025-07-28', 3.8, 'Olhos vermelhos.'),
  (9, '2025-07-10', 8.2, 'Consulta noturna, sem anormalidades.'),
  (10, '2019-06-15', 7.1, 'Atendido entre maio-julho de 2019.');
  

  
  SELECT * FROM Consulta 
  JOIN animal ON consulta.animal_id = animal.id 
  WHERE altura IS NOT NULL;  
  --
  
  SELECT a.nome, e.nome AS 'especie' FROM animal a 
  JOIN especie e on a.especie_id = e.id 
  WHERE e.nome LIKE 'Gato';
  --
  
  SELECT a.nome, a.peso FROM animal a 
  JOIN dono ON a.dono_id = dono.id 
  WHERE dono.email LIKE '%gmail%';
  */
  /*
CREATE DATABASE IF NOT EXISTS Biblioteca;
USE Biblioteca;

CREATE TABLE Autor (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    nacionalidade VARCHAR(50)
);

CREATE TABLE Livro (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    ano_publicacao INT,
    genero VARCHAR(50),
    autor_id INT,

    FOREIGN KEY (autor_id) REFERENCES Autor(id)
);

CREATE TABLE Cliente (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100),
    telefone VARCHAR(20)
);

CREATE TABLE Reserva (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cliente_id INT,
    livro_id INT,
    data_reserva DATE,

    FOREIGN KEY (cliente_id) REFERENCES Cliente(id),
    FOREIGN KEY (livro_id) REFERENCES Livro(id)
);


INSERT INTO Autor (nome, nacionalidade) VALUES
('J.K. Rowling', 'Britânica'),
('George R.R. Martin', 'Americana'),
('Machado de Assis', 'Brasileira'),
('Agatha Christie', 'Britânica');

INSERT INTO Livro (titulo, ano_publicacao, genero, autor_id) VALUES
('Harry Potter e a Pedra Filosofal', 1997, 'Fantasia', 1),
('As Crônicas de Gelo e Fogo', 1996, 'Fantasia', 2),
('Dom Casmurro', 1899, 'Romance', 3),
('Assassinato no Expresso do Oriente', 1934, 'Mistério', 4);

INSERT INTO Cliente (nome, email, telefone) VALUES
('Ana Souza', 'ana@gmail.com', '11999999999'),
('Carlos Lima', 'carlos@yahoo.com', '21988888888'),
('Mariana Alves', 'mariana@outlook.com', '31977777777');

INSERT INTO Reserva (cliente_id, livro_id, data_reserva) VALUES
(1, 1, '2025-08-10'),
(2, 2, '2025-08-15'),
(3, 3, '2025-08-18'),
(1, 4, '2025-08-19');
*/

-- Exercicio 

/*
-- Criar o banco de dados
CREATE DATABASE IF NOT EXISTS Biblioteca;
USE Biblioteca;

-- Tabela: Autores
CREATE TABLE Autores (
    autor_id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    nacionalidade VARCHAR(50)
);

-- Tabela: Livros
CREATE TABLE Livros (
    livro_id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    genero VARCHAR(50),
    autor_id INT NOT NULL,
    FOREIGN KEY (autor_id) REFERENCES Autores(autor_id)
);

-- Tabela: Clientes
CREATE TABLE Clientes (
    cliente_id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    endereco VARCHAR(255),
    cidade VARCHAR(100)
);

-- Tabela: Reservas
CREATE TABLE Reservas (
    reserva_id INT AUTO_INCREMENT PRIMARY KEY,
    cliente_id INT NOT NULL,
    livro_id INT NOT NULL,
    data_reserva DATE,
    data_retirada DATE,
    data_devolucao DATE,
    FOREIGN KEY (cliente_id) REFERENCES Clientes(cliente_id),
    FOREIGN KEY (livro_id) REFERENCES Livros(livro_id)
);

-- Inserir autores
INSERT INTO Autores (nome, nacionalidade) VALUES
('J.K. Rowling', 'Britânica'),
('George R.R. Martin', 'Americana'),
('Machado de Assis', 'Brasileira'),
('Agatha Christie', 'Britânica');

-- Inserir livros
INSERT INTO Livros (titulo, genero, autor_id) VALUES
('Harry Potter e a Pedra Filosofal', 'Fantasia', 1),
('As Crônicas de Gelo e Fogo', 'Fantasia', 2),
('Dom Casmurro', 'Romance', 3),
('Assassinato no Expresso do Oriente', 'Mistério', 4);

-- Inserir clientes
INSERT INTO Clientes (nome, endereco, cidade) VALUES
('Ana Souza', 'Rua das Flores, 123', 'São Paulo'),
('Carlos Lima', 'Av. Central, 456', 'Rio de Janeiro'),
('Mariana Alves', 'Rua das Palmeiras, 789', 'Belo Horizonte');

-- Inserir reservas
INSERT INTO Reservas (cliente_id, livro_id, data_reserva, data_retirada, data_devolucao) VALUES
(1, 1, '2025-08-10', '2025-08-11', '2025-08-18'),
(2, 2, '2025-08-15', '2025-08-16', '2025-08-23'),
(3, 3, '2025-08-18', '2025-08-19', '2025-08-26'),
(1, 4, '2025-08-19', '2025-08-20', NULL); -- Livro ainda não devolvido
*/

SELECT l.titulo, a.nome AS 'autor' FROM livros l
JOIN autores a ON livros.autor_id = autor.id;

SELECT * FROM cliente 
JOIN reservas ON 


CREATE DATABASE escolaDB;
USE escolaDB;

CREATE TABLE Aluno (
    id_aluno INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    idade INT,
    curso VARCHAR(50)
);


CREATE TABLE Professor (
    id_prof INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    disciplina VARCHAR(50)
);


CREATE TABLE Disciplina (
    id_disc INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    carga_horaria INT
);


CREATE TABLE Nota (
    id_nota INT AUTO_INCREMENT PRIMARY KEY,
    id_aluno INT,
    id_disc INT,
    nota DECIMAL(5,2),
    FOREIGN KEY (id_aluno) REFERENCES Aluno(id_aluno),
    FOREIGN KEY (id_disc) REFERENCES Disciplina(id_disc)
);


INSERT INTO Aluno (nome, idade, curso) VALUES
('Maria', 20, 'Informática'),
('João', 22, 'Administração'),
('Ana', 19, 'Informática');

INSERT INTO Professor (nome, disciplina) VALUES
('Carlos', 'Banco de Dados'),
('Fernanda', 'Programação');

INSERT INTO Disciplina (nome, carga_horaria) VALUES
('Banco de Dados', 60),
('Programação', 80);

INSERT INTO Nota (id_aluno, id_disc, nota) VALUES
(1, 1, 8.5),
(2, 2, 7.0),
(3, 1, 9.0);

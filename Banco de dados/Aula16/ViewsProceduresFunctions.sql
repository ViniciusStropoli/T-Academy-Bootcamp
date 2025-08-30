/*Procedure não precisa retornar valor, e pode fazer comando dml
Function nao altera a tabela em si, precisa retornar valor

View criar uma tabela derivada de outras tabelas, como um select, porém que pode ser manipulado por selects */

-- views
-- 1
CREATE VIEW vw_notas_alunos AS
SELECT nota.nota, aluno.nome FROM nota
JOIN aluno ON nota.id_aluno = aluno.id_aluno;

SELECT * FROM vw_notas_alunos;

-- 2
CREATE VIEW vw_media_geral_aluno AS
SELECT AVG(nota.nota), aluno.nome FROM nota
JOIN aluno ON nota.id_aluno = aluno.id_aluno
GROUP BY aluno.nome;

SELECT * FROM vw_media_geral_aluno;

-- Function 

-- 1
DELIMITER //

CREATE FUNCTION fn_status_aluno(n DECIMAL(5,2))
RETURNS VARCHAR(20)
DETERMINISTIC
BEGIN
	RETURN (CASE WHEN n >= 7 THEN 'Aprovado' ELSE 'Reprovado' END);
END;

//
DELIMITER ;

SELECT nome,nota, fn_status_aluno(nota) AS 'Status'
FROM nota n
JOIN aluno a ON n.id_aluno = a.id_aluno;


-- Procedure

-- 1
DELIMITER //

CREATE PROCEDURE sp_alunos_curso(IN cursoNome VARCHAR(50))
BEGIN 
	SELECT * FROM Aluno WHERE curso = cursoNome;
END;
//
DELIMITER ;

CALL sp_alunos_curso('Informática');

-- 2
DELIMITER //

CREATE PROCEDURE sp_InserirAluno(IN p_nome VARCHAR(100), IN p_idade INT, IN p_curso VARCHAR(30))
BEGIN
	INSERT INTO Aluno(nome, idade, curso) VALUES (p_nome, p_idade, p_curso);
END;

//
DELIMITER ; 

CALL sp_InserirAluno("Rannyer",26,"Java básico");









-- 3

DELIMITER //

CREATE PROCEDURE sp_media_disc(IN aluno_id INT)
BEGIN
	SELECT AVG(n.nota) AS 'Media notas', aluno.nome, disciplina.nome AS 'Disciplina' FROM nota n
	JOIN aluno ON n.id_aluno = aluno.id_aluno
    JOIN disciplina ON n.id_disc = disciplina.id_disc
	GROUP BY aluno.nome;
END;

//
DELIMITER ; 

CALL sp_media_disc(1);







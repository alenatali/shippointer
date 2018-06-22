--MSSQLServer script
CREATE DATABASE shippointer
GO
USE shippointer

CREATE TABLE curso (
codigo   INT NOT NULL IDENTITY,
sigla    VARCHAR(10) NOT NULL UNIQUE,
nome     VARCHAR(50) NOT NULL UNIQUE,
periodo  CHAR(1)     NOT NULL CHECK(periodo = 'm' OR periodo = 't' OR periodo = 'n')

PRIMARY KEY (codigo)
)

CREATE TABLE aluno (
codigo   INT NOT NULL UNIQUE IDENTITY,
cpf      VARCHAR(13) NOT NULL,
nome     VARCHAR(50) NOT NULL,
dt_nasc  DATE NOT NULL CHECK( dt_nasc < GETDATE() )

PRIMARY KEY (cpf)
)

CREATE TABLE matricula (
codigo     INT NOT NULL IDENTITY,
ra         VARCHAR(13) NOT NULL,
curso_cod  INT NOT NULL,
aluno_cpf  VARCHAR(13) NOT NULL,
sstatus    CHAR(1) NOT NULL,
dt_inicio  DATE NOT NULL CHECK( dt_inicio <= GETDATE() ),
dt_termino DATE

PRIMARY KEY (ra)
FOREIGN KEY (curso_cod) REFERENCES curso(codigo),
FOREIGN KEY (aluno_cpf) REFERENCES aluno(cpf)
)

CREATE TABLE empresa (
codigo         INT NOT NULL UNIQUE IDENTITY,
cnpj           VARCHAR(14) NOT NULL UNIQUE,
nm_fantasea    VARCHAR(50) NOT NULL,
rz_social      VARCHAR(50) NOT NULL,
insc_estadual  VARCHAR(12) NOT NULL

PRIMARY KEY (cnpj)
)

CREATE TABLE supervisor (
codigo    INT NOT NULL IDENTITY,
nome      VARCHAR(50) NOT NULL,
telefone  VARCHAR(11),
email     VARCHAR(30)

PRIMARY KEY (codigo)
)

CREATE TABLE gestor (
codigo             INT NOT NULL IDENTITY,
supervisor_codigo  INT NOT NULL,
empresa_cnpj       VARCHAR(14) NOT NULL

PRIMARY KEY (codigo)
FOREIGN KEY (supervisor_codigo) REFERENCES supervisor(codigo),
FOREIGN KEY (empresa_cnpj) REFERENCES empresa(cnpj),

--CONSTRAINT chk_uniq_pair CHECK( how can I ask if 2 lines combined are unique )
)

CREATE TABLE contrato (
codigo         INT NOT NULL,
matricula_ra   VARCHAR(13) NOT NULL,
empresa_cnpj   VARCHAR(14) NOT NULL,
n_contrato     VARCHAR(20) NOT NULL,
dt_inicio      DATE NOT NULL CHECK( dt_inicio <= GETDATE() ),
dt_termino     DATE,
tipo_contrato  CHAR(2) NOT NULL,
cargo          VARCHAR(20) NOT NULL,
profsupervisor_codigo  INT NOT NULL,
gestor_codigo        INT NOT NULL

PRIMARY KEY (codigo)
FOREIGN KEY (matricula_ra) REFERENCES matricula(ra),
FOREIGN KEY (profsupervisor_codigo) REFERENCES supervisor(codigo),
FOREIGN KEY (gestor_codigo) REFERENCES gestor(codigo)

/*CONSTRAINT chk_dtterm_greaterthan_dtini
case
WHEN dt_termino IS NOT NULL THEN
	CHECK(dt_termino > dt_inicio)
END
*/
)

CREATE TABLE contato (
codigo      INT NOT NULL IDENTITY,
valor       VARCHAR(30) NOT NULL,
descricao   VARCHAR(20) NOT NULL

PRIMARY KEY (codigo)
)

CREATE TABLE contato_aluno (
codigo          INT NOT NULL IDENTITY,
aluno_cpf       VARCHAR(13) NOT NULL,
contato_codigo  INT NOT NULL

PRIMARY KEY (codigo)
FOREIGN KEY (aluno_cpf) REFERENCES aluno(cpf),
FOREIGN KEY (contato_codigo) REFERENCES contato(codigo)
)

CREATE TABLE contato_supervisor (
codigo             INT NOT NULL IDENTITY,
supervisor_codigo  INT NOT NULL,
contato_codigo     INT NOT NULL

PRIMARY KEY (codigo)
FOREIGN KEY (supervisor_codigo) REFERENCES supervisor(codigo),
FOREIGN KEY (contato_codigo) REFERENCES contato(codigo)
)

CREATE TABLE endereco (
codigo       INT NOT NULL IDENTITY,
tipo         CHAR(2) NOT NULL,
cep          CHAR(8) NULL,
logradouro   VARCHAR(50) NOT NULL,
n            VARCHAR(7) NOT NULL,
complemento  VARCHAR(50) NULL,
uf           CHAR(2) NOT NULL,
cidade       VARCHAR(50) NOT NULL

PRIMARY KEY (codigo)
)

CREATE TABLE aluno_endereco (
aluno_cpf        VARCHAR(13) NOT NULL,
endereco_codigo  INT NOT NULL

PRIMARY KEY (aluno_cpf, endereco_codigo)
FOREIGN KEY (aluno_cpf) REFERENCES aluno(cpf),
FOREIGN KEY (endereco_codigo) REFERENCES endereco(codigo)
)

CREATE TABLE empresa_endereco (
empresa_cnpj     VARCHAR(14) NOT NULL,
endereco_codigo  INT NOT NULL

PRIMARY KEY (empresa_cnpj, endereco_codigo)
FOREIGN KEY (empresa_cnpj) REFERENCES empresa(cnpj),
FOREIGN KEY (endereco_codigo) REFERENCES endereco(codigo)
)

CREATE TABLE usuario (
codigo  INT NOT NULL IDENTITY,
usuario VARCHAR(200) NOT NULL,
senha   VARCHAR(200) NOT NULL,
dica    VARCHAR(20) NULL,
perfil  CHAR(2) NULL

PRIMARY KEY (usuario, senha)
)

TRUNCATE TABLE usuario

------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------
----------------------------------**Dados**

INSERT INTO curso (sigla,nome,periodo) VALUES
('COMX', 'Comércio exterior', 't'),
('POLI', 'Polimeros', 'n'),
('ADS', 'Análise e Desenvolvimento de Software', 'n')

INSERT INTO aluno (cpf,nome,dt_nasc) VALUES
('1', 'Lucas Montes', '1998-04-20 00:00:00.000'),
('81057866806', 'Luciana Maria', '1990-08-12 00:00:00.000'),
('84263543866', 'Carol Fitch', '1993-01-02 00:00:00.000'),
('39624581827', 'Marcos Tilo', '1999-02-26 00:00:00.000'),
('75278270845', 'Johanna Lee', '1986-12-17 00:00:00.000')

INSERT INTO matricula (ra,curso_cod,aluno_cpf,sstatus,dt_inicio,dt_termino) VALUES
('00001', 2, '1', 't', '2013-06-12 00:00:00.000', '2016-06-20 00:00:00.000'),
('00002', 3, '1', 'a', '2016-07-01 00:00:00.000', NULL),
('00003', 3, '39624581827', 'a', '2016-07-01 00:00:00.000', NULL),
('00004', 3, '75278270845', 't', '2013-06-12 00:00:00.000', '2016-06-20 00:00:00.000'),
('00005', 1, '81057866806', 'a', '2016-07-01 00:00:00.000', NULL),
('00006', 2, '84263543866', 'a', '2016-07-01 00:00:00.000', NULL)

INSERT INTO contato (valor,descricao) VALUES
('bla@bla.com', 'email'),
('988888888', 'celular'),
('88888888', 'casa'),
('eee@eee.com', 'email comercial'),
('988888888', 'celular'),
('988888888', 'celular'),
('aaa.com', 'site'),
('@blublu', 'twitter'),
('aaaaasasasas', 'linkdin'),
('988888888', 'celular')

INSERT INTO contato_aluno (aluno_cpf,contato_codigo) VALUES
('1', 8),
('1', 4),
('1', 3),
('39624581827', 1),
('39624581827', 2),
('75278270845', 5),
('81057866806', 6),
('81057866806', 7),
('84263543866', 9),
('84263543866', 10)

INSERT INTO endereco (tipo,logradouro,n,complemento,uf,cidade) VALUES
('av', 'Carlota Brunns', '122', '', 'sp', 'São Paulo'),
('tr', 'Claudio Torres', '11', 'Casa A', 'sp', 'São Paulo'),
('ru', 'Lucio Germano', '34', 'casa b', 'sp', 'São Paulo'),
('ru', 'Lucio Germano', '34', '', 'sp', 'São Paulo'),
('tr', 'Potinguá', '12', '', 'sp', 'São Paulo')

INSERT INTO aluno_endereco (aluno_cpf, endereco_codigo) VALUES
('1', 2),
('39624581827', 1),
('75278270845', 3),
('81057866806', 4),
('84263543866', 5)

INSERT INTO usuario (usuario,senha,dica,perfil) VALUES
('adm', '¬gBóá\vá¥âUðg•6#È³ˆ´Ežùx×ÈFô', 'adm1234', 'ad'),
('ope', '¬gBóá\vá¥âUðg•6#È³ˆ´Ežùx×ÈFô', 'ope1234', 'op')

UPDATE usuario SET senha =  '¬gBóá\vá¥âUðg•6#È³ˆ´Ežùx×ÈFô'

select * from usuario

------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------
----------------------------------**Queries**

--*********************************Curso
SELECT * FROM curso

INSERT INTO	curso (sigla, nome, periodo) VALUES
('ADS', 'Análise e Desenvolvimento de Sistemas', 'n'),
('COMEX', 'Comércio Exterior', 'm'),
('LOG', 'Logistica', 't'),
('POLI', 'Polimeros', 'n'),
('GES', 'Gestão Empresarial', 'm')

UPDATE curso SET
sigla = 'ADS',
nome = 'Análise e Desenvolvimento de Sistemas',
periodo = 't'
WHERE codigo = 1

DELETE FROM curso WHERE codigo = 6


--*********************************Aluno
INSERT INTO aluno (cpf, nome, dt_nasc) VALUES
('15205322452', 'Ayla Fitch',  '1996-10-02 00:00:00.000'),
('15201677332', 'Dan Hsohv',  '1999-12-12 00:00:00.000')

SELECT * FROM aluno WHERE cpf = '15205322452'
UPDATE aluno SET
cpf = '1'
WHERE  cpf = '15201677332'

SELECT * FROM aluno

SELECT * FROM aluno WHERE cpf = ?

SELECT * FROM aluno al WHERE al.nome = '%nome%'

SELECT DATEDIFF(YEAR, ?, GETDATE()) AS idade

UPDATE aluno SET
cpf = ?, nome = ?, dt_nasc = ?
WHERE  cpf = ?

DELETE FROM aluno WHERE cpf = ?   


--*********************************Contato
INSERT INTO contato (valor, descricao) 
OUTPUT INSERTED.codigo AS codigo VALUES ('323433', 'emDdl')

SELECT co.descricao, co. valor
FROM contato co LEFT JOIN contato_aluno ac
ON ac.contato_codigo = co.codigo
WHERE ac.aluno_cpf = '1'
ORDER BY co.codigo

UPDATE contato SET
valor = ?, descricao = ?
WHERE codigo = ?

DELETE FROM contato_aluno WHERE contato_codigo = ? AND aluno_cpf = ?
DELETE FROM contato WHERE codigo = ?



--*********************************Endereco
INSERT INTO endereco (tipo, logradouro, n, complemento, uf, cidade) VALUES (?, ?, ?, ?, ?, ?)
INSERT INTO aluno_endereco (aluno_cpf, endereco_codigo) VALUES (?, ?)

DELETE FROM aluno_endereco WHERE endereco_codigo = ? AND aluno_cpf = ?
DELETE FROM endereco WHERE codigo = ?

SELECT ed.codigo, ed.tipo, ed.logradouro, ed.complemento, ed.n, ed.uf, ed.cidade
FROM endereco ed LEFT JOIN aluno_enderco ale
ON ale.endereco_codigo = ed.codigo
WHERE ale.aluno_cpf = '1'
ORDER BY ed.codigo

UPDATE endereco SET
tipo=?, logradouro=?, complemento=?, n=?, uf=?, cidade=?,
WHERE codigo-?



--*********************************Matricula

CREATE TABLE matricula (
codigo     INT NOT NULL IDENTITY,
ra         VARCHAR(13) NOT NULL,
curso_cod  INT NOT NULL,
aluno_cpf  VARCHAR(13) NOT NULL,
sstatus    CHAR(1) NOT NULL,
dt_inicio  DATE NOT NULL CHECK( dt_inicio <= GETDATE() ),
dt_termino DATE


INSERT INTO matricula (ra, curso_cod, aluno_cpf, sstatus, dt_inicio, dt_termino)
OUTPUT INSERTED.codigo AS codigo
VALUES (?,?,?,?,?,?)

SELECT * FROM matricula WHERE aluno_cpf = ?

UPDATE matricula SET
curso_cod=?, aluno_cpf=?, sstatus=?, dt_inicio=?, dt_termino=?
WHERE ra=?


DELETE FROM matricula WHERE ra = ?


--*********************************Usuário

SELECT * FROM usuario WHERE usuario=? AND senha=?
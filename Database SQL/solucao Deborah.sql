CREATE DATABASE LocacaoCar;

use LocacaoCar;

/*CRIAR AS TABELAS*/

CREATE TABLE tbModelo(
codigo int,
descricao varchar(50),
primary key (codigo));

INSERT INTO tbModelo
VALUES
(1, 'PARATI'),
(2, 'FIT');

CREATE TABLE tbCor(
codigo int,
descricao varchar(50),
primary key (codigo));

INSERT INTO tbCor
VALUES
(1,'FUCSIA'),
(2, 'BORDO'),
(3,'COLORIDO'),
(4, 'INVISIVEL');

CREATE TABLE tbTipo(
codigo int,
descricao varchar(50),
primary key (codigo));

INSERT INTO tbTipo
VALUES
(1, 'CAMINHONETA'),
(2, 'SEDAN'),
(3, 'CONVERSIVEL');

CREATE TABLE tbAcessorio(
codigo int,
descricao varchar(50),
primary key (codigo));

INSERT INTO tbAcessorio
VALUES
(1, 'Ar Condicionado'),
(2, 'Acessibilidade');

CREATE TABLE tbCambio(
codigo int,
descricao varchar(50),
primary key (codigo));

INSERT INTO tbCambio
VALUES
(1, 'MAUAL'),
(2, 'AUTOMATICO');
  
CREATE TABLE tbCarro (
  chassi char(17),
  Modelo int,
  nmPlaca varchar(8),
  nrAno int,
  Cor int,
  Cambio int,
  nrPortas int,
  Tipo int,
  PRIMARY KEY (chassi),
  FOREIGN KEY (modelo)
    REFERENCES tbModelo(codigo),
  FOREIGN KEY (cor)
    REFERENCES tbCor(codigo),
  FOREIGN KEY (Tipo)
    REFERENCES tbTipo(codigo),
  FOREIGN KEY (Cambio)
    REFERENCES tbCambio(codigo)
    );

INSERT INTO tbCarro
VALUES
('111AAA', 1, 'ACJW2623', 2020, 1, 1, 3, 1),
('222BBB', 1, 'ACJW2323', 2020, 2, 1, 4, 1),
('333CCC', 2, 'XCJW2323', 2020, 3, 1, 4, 1),
('444DDD', 2, 'RCJW2323', 2020, 3, 1, 4, 1),
('555EEE', 1, 'TCJW2323', 2020, 4, 1, 4, 1);

CREATE TABLE tbCarro_Acessorio(
   chassi char(17),
   Acessorio int,
   primary key (chassi, Acessorio),
   FOREIGN KEY (chassi)
    REFERENCES tbCarro(chassi),
   FOREIGN KEY (Acessorio)
    REFERENCES tbAcessorio(codigo));

INSERT INTO tbCarro_Acessorio
VALUES
('111AAA', 1),
('222BBB', 1),
('333CCC', 2),
('333CCC', 1);

CREATE TABLE tbUF(
codigo int,
descricao varchar(50),
primary key (codigo));

INSERT INTO tbUF
VALUES
(1, 'Parana'),
(2, 'Santa Catarina');

CREATE TABLE tbMunicipio(
codigo int,
Nome varchar(50),
UF int,
primary key (codigo),
FOREIGN KEY (UF)
    REFERENCES tbUF(codigo));

INSERT INTO tbMunicipio
VALUES
(1, 'Curitiba',1),
(2, 'Londrina',1);

CREATE TABLE tbCliente (
  email varchar(250),
  nmCliente varchar(250),
  nrTelefone_DDD char(3),
  nrTelefone char(9),
  nrCelular_DDD char(3),
  nrCelular char(9),
  nmEndereco_rua varchar(250),
  nmEndereco_numero varchar(10),
  nmEndereco_complemento varchar(250),
  nmEndereco_municipio int,
  nmEndereco_CEP char(8),
  dtNascimento date,
  nrTelefone_Contato_DDD char(3),
  nrTelefone_Contato char(9),
  PRIMARY KEY (email),
  FOREIGN KEY (nmEndereco_municipio)
    REFERENCES tbMunicipio(codigo));
 
INSERT INTO tbCliente
VALUES
('1@1.com', 'Joao', '041', '23442', '041', '445544', 'longe', '123', '12551',1,'80000000', '2001-01-01', '041', '334747'),
('1@2.com', 'Maria', '041', '23442', '041', '445544', 'longe', '123', '12551',1,'80000000', '2001-01-01', '041', '334747'),
('1@3.com', 'Joao', '041', '23442', '041', '445544', 'longe', '123', '12551',1,'80000000', '2001-01-01', '041', '334747');

-- quantos carros modelo Parati estao cadastrados
SELECT count(*)
FROM tbCarro, tbModelo
where tbModelo.descricao = 'PARATI' AND
      tbCarro.modelo = tbModelo.codigo;
      
-- quantos carros por modelo estao cadastrados
SELECT tbModelo.descricao, count(*)
FROM tbCarro, tbModelo
where tbCarro.modelo = tbModelo.codigo
group by tbModelo.descricao
order by tbModelo.descricao;
      
      
      

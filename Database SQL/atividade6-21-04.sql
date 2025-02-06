#drop database lojaJogos; 
create database lojaJogos;
use lojaJogos;

create table tbFuncionario
(PRIMARY KEY(CPF), CPF int, nome varchar(15), telefone int, 
pais varchar(12), estado varchar(12), cidade varchar(12), CEP int, 
rua varchar(25), numero int, complemento varchar(10)) ;

create table tbClientePessoaFisica
(PRIMARY KEY(CPF), CPF int, nome varchar(15), telefone int, 
pais varchar(12), estado varchar(12), cidade varchar(12), CEP int, 
rua varchar(25), numero int, complemento varchar(10)) ;

create table tbClientePessoaJuridica
(PRIMARY KEY(CNPJ), CNPJ int, nome varchar(15), telefone int, 
pais varchar(12), estado varchar(12), cidade varchar(12), CEP int, 
rua varchar(25), numero int, complemento varchar(10)) ;

create table tbJogos
(PRIMARY KEY(titulo), titulo varchar(15), classificacao int,
genero varchar(10), preco int) ;

create table tbServicos
(PRIMARY KEY(codigo), codigo INT, descricao varchar(50), preco int);
    
create table tbCompraClienteFisico
(titulo varchar(15), FOREIGN KEY(titulo) references tbJogos(titulo),
CPF int, FOREIGN KEY(CPF) references tbClientePessoaFisica(CPF));

create table tbCompraClienteJuridico
(titulo varchar(15), FOREIGN KEY(titulo) references tbJogos(titulo),
CNPJ int, FOREIGN KEY(CNPJ) references tbClientePessoaJuridica(CNPJ));

create table tbRequisicaoClienteFisico
(codigo int, FOREIGN KEY(codigo) references tbServicos(codigo),
CPF int, FOREIGN KEY(CPF) references tbClientePessoaFisica(CPF));

create table tbRequisicaoClienteJuridico
(codigo int, FOREIGN KEY(codigo) references tbServicos(codigo),
CNPJ int, FOREIGN KEY(CNPJ) references tbClientePessoaJuridica(CNPJ));

create table tbExecucaoServico
(codigo int, FOREIGN KEY(codigo) references tbServicos(codigo),
CPF int, FOREIGN KEY(CPF) references tbFuncionario(CPF));


insert into tbFuncionario values (123, 'Leandro', 99, 'Brasil', 'Paraná', 'Curitiba', 810, 'Rua Alameda', 420, 'Sobrado 10');
insert into tbFuncionario values (456, 'Albert', 98, 'Brasil', 'Paraná', 'Curitiba', 811, 'Rua Uvas', 100, 'Sobrado 3');
insert into tbFuncionario values (789, 'Carla', 97, 'Brasil', 'Paraná', 'Curitiba', 812, 'Rua Moranguete', 66, 'AP 6');

insert into tbClientePessoaFisica values (111, 'Claudio', 96, 'Brasil', 'Paraná', 'Curitiba', 813, 'Rua Lambreta', 101, 'AP 2');
insert into tbClientePessoaFisica values (222, 'Joao', 95, 'Brasil', 'Paraná', 'Curitiba', 814, 'Rua Ceu', 20, 'AP 22');
insert into tbClientePessoaFisica values (333, 'Joanete', 94, 'Brasil', 'Paraná', 'Ponta Grossa', 815, 'Rua Bicicleta', 77, 'Casa 3');

insert into tbClientePessoaJuridica values (151, 'Next Store', 9, 'Brasil', 'Paraná', 'Curitiba', 816, 'Rua Marmelada', 62, 'Ed Roose');
insert into tbClientePessoaJuridica values (141, 'Request Store', 8, 'Brasil', 'Paraná', 'Curitiba', 817, 'Rua do Rock', 6, 'Ed Velvet');
insert into tbClientePessoaJuridica values (131, 'Giga Games', 7, 'Brasil', 'Paraná', 'Curitiba', 818, 'Rua Patria', 12, 'Ed Red');

insert into tbJogos values ('Tentacao', 18, 'Drama', 89);
insert into tbJogos values ('Death', 18, 'Acao', 129);
insert into tbJogos values ('Mother', 18, 'Suspense', 59);
insert into tbJogos values ('Labirinto', 18, 'Terror', 49);
insert into tbJogos values ('Chicken', 12, 'Aventura', 49);
insert into tbJogos values ('Lovely', 16, 'Novel', 69);
insert into tbJogos values ('Camp', 14, 'Esportes', 79);
insert into tbJogos values ('AWP Forever', 18, 'FPS', 149);

insert into tbServicos values (01, 'Limpeza e polimento de discos para conservacao', 40);
insert into tbServicos values (02, 'Recuperacao de discos riscados', 60);
insert into tbServicos values (03, 'Troca de case', 50);
insert into tbServicos values (04, 'Mosaico com discos quebrados', 120);

insert into tbCompraClienteFisico values ('Mother', 111);
insert into tbCompraClienteFisico values ('Chicken', 111);
insert into tbCompraClienteFisico values ('Death', 222);
insert into tbCompraClienteFisico values ('Camp', 222);
insert into tbCompraClienteFisico values ('AWP Forever', 333);

insert into tbCompraClienteJuridico values ('Labirinto', 141);
insert into tbCompraClienteJuridico values ('Camp', 151);

insert into tbRequisicaoClienteFisico values (04, 111);
insert into tbRequisicaoClienteFisico values (02, 222);
insert into tbRequisicaoClienteFisico values (01, 333);

insert into tbRequisicaoClienteJuridico values (04, 151);
insert into tbRequisicaoClienteJuridico values (04, 141);
insert into tbRequisicaoClienteJuridico values (04, 131);

insert into tbExecucaoServico values (01, 456);
insert into tbExecucaoServico values (02, 456);
insert into tbExecucaoServico values (03, 789);
insert into tbExecucaoServico values (04, 123);

SELECT tbClientePessoaFisica.nome
FROM TbClientePessoaFisica
WHERE cidade = 'Curitiba';

SELECT tbClientePessoaJuridica.nome
FROM TbClientePessoaJuridica
WHERE cidade = 'Curitiba';

SELECT tbJogos.preco
FROM tbJogos
WHERE classificacao = 18;

SELECT tbJogos.titulo
FROM tbJogos
WHERE preco < 100;

SELECT tbClientePessoaJuridica.nome, tbRequisicaoClienteJuridico.CNPJ
FROM tbClientePessoaJuridica, tbRequisicaoClienteJuridico
WHERE tbRequisicaoClienteJuridico.codigo = 04 
											and tbClientePessoaJuridica.CNPJ = tbRequisicaoClienteJuridico.CNPJ;
					

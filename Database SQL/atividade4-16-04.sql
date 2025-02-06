
drop database teste;
create database teste;

use teste;

create table TBEscola(cod int,
	nome varchar (100),
	primary key (cod));

create table TBCurso(cod int,
		nome varchar(100),
        cod_escola int,
        foreign key(cod_escola) references TBEscola(cod),
        primary key (cod));
        
create table TBEstudante(mat int,
		nome varchar(100),
        data_nasc date,
        cod_curso int,
        primary key(mat),
        foreign key (cod_curso) references TBCurso(cod));

insert into TBEscola values (1, 'Escola da Esquina');
insert into TBEscola values (2, 'Escola Moderna');
insert into TBEscola values (3, 'Colegio dos Astros');
insert into TBEscola values (4, 'Escola 1234');
insert into TBEscola values (5, 'Colegio Mandraque');
insert into TBEscola values (6, 'Escola Grande Morro');
insert into TBEscola values (7, 'Escola Atualizada');
insert into TBEscola values (8, 'Colegio Star');
insert into TBEscola values (9, 'Escola Numeros');
insert into TBEscola values (10, 'Colegio Inovador');
insert into TBEscola values (11, 'Escola da Pidrada');
insert into TBEscola values (12, 'Escola Broski');
insert into TBEscola values (13, 'Colegio Graska');
insert into TBEscola values (14, 'Escola Mítica');
insert into TBEscola values (15, 'Colegio Lendário');

insert into TBCurso values (101, 'Criando Abelhas',5);
insert into TBCurso values (102, 'Buscando Alegria',4);
insert into TBCurso values (103, 'Formigas aprontando',1);
insert into TBCurso values (104, 'Criando Rebeldes',5);
insert into TBCurso values (105, 'Armando Bagunca',2);
insert into TBCurso values (106, 'Como fazer bolos',15);
insert into TBCurso values (107, 'Corte de peixes',14);
insert into TBCurso values (108, 'Básicas da fermentação',11);
insert into TBCurso values (109, 'Boas práticas na computação',9);
insert into TBCurso values (110, 'Profissional 2.0',8);
insert into TBCurso values (111, 'Criando Bebidas',7);
insert into TBCurso values (112, 'Buscando Wally',6);
insert into TBCurso values (113, 'Literatura dos Memes',13);
insert into TBCurso values (114, 'Criando Sensatez',5);
insert into TBCurso values (115, 'Como cuidar e limpar seu computador',15);

insert into TBEstudante values (501, 'Joao das Couves','2001-06-06', 105);
insert into TBEstudante values (502, 'Maria Silveira','2001-05-01', 104);
insert into TBEstudante values (503, 'Beatriz Silva','2001-10-01', 104);
insert into TBEstudante values (504, 'Carlos Roberto','2001-03-01', 104);
insert into TBEstudante values (505, 'Joao Sikka','2001-08-01', 101);
insert into TBEstudante values (506, 'Maria Junqueira','2001-05-10', 101);
insert into TBEstudante values (507, 'Antonio Silva','2001-07-12', 101);
insert into TBEstudante values (508, 'Berenice Souza','2001-02-11', 115);
insert into TBEstudante values (509, 'Joao Souza','2001-01-01', 103);
insert into TBEstudante values (510, 'Jose das Couves','2001-07-01', 103);
insert into TBEstudante values (511, 'Carlos Martins','2001-08-17', 103);
insert into TBEstudante values (512, 'Marcos das Couves','2001-09-07', 105);
insert into TBEstudante values (513, 'Antonio Baixo','2001-09-11', 105);
insert into TBEstudante values (514, 'Joao das Aboboras','2001-04-06', 102);
insert into TBEstudante values (515, 'Maria Silver','2001-04-01', 101);
insert into TBEstudante values (516, 'Beatriz Ribeiro','2001-12-01', 113);
insert into TBEstudante values (517, 'Carlos Robert','2001-04-01', 114);
insert into TBEstudante values (518, 'Joao Sicko','2001-04-01', 110);
insert into TBEstudante values (519, 'Maria Junque','2001-04-10', 101);
insert into TBEstudante values (520, 'Antonio Bandeiras','2001-04-12', 111);
insert into TBEstudante values (521, 'Kendice Berenice','2001-04-11', 112);
insert into TBEstudante values (522, 'Joao Alberto','2001-02-01', 114);
insert into TBEstudante values (523, 'Jose das Covas','2001-03-01', 115);

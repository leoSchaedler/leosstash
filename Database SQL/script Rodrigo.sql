/*
guigo.judo@hotmail.com
Entidades
Relacionamentos Cardinalidade 
                                1,n   criação de um atributo (n) ->FK
                                N,m   criacao de uma nova tabela
                                1,1    fusao ou criacaode um atributo ->FK

TBPROFESSOR( Matricula, nome, telefone
TBDISCIPLINA(código, nome, matricula_prof
           Matricula_prof REFER TBPROFESSOR
TBALUNO(Matricula, nome, data_nas
TBALUNODISCIPLINA(MATRICULA_ALUNO, COD_DISC)
	Matricula_referencia TBALUNO
              COD_DISC referencia TBDISCIPLINA
*/

create database rodrigo;

use rodrigo;

create table TBPROFESSOR (
matricula int,
nome varchar(100),
fone char(12),
primary key (matricula));

create table TBDISCIPLINA (
cod int,
nome varchar(100),
matricula_prof int,
foreign key (matricula_prof) references TBPROFESSOR(matricula),
primary key (cod));

create table TBALUNO (
matricula int,
nome varchar(100),
data_nas date,
primary key (matricula));

create table TBALUNO_DISCIPLINA (
matricula int,
cod int,
primary key (matricula,cod),
foreign key (matricula) references TBALUNO(matricula),
foreign key (cod) references TBDISCIPLINA(cod)
);
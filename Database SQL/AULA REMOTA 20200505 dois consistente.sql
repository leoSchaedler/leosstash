create database teste3;

use teste3;

create table TBCidade(cod int,
	nome varchar (100),
    primary key (cod));
 
 insert into TBCidade values(1, "Curitiba");   
 insert into TBCidade values(2, "Paranagua");   
 insert into TBCidade values(3, "Piraquara");   
 
create table TBEscola(cod int,
	nome varchar (100),
    cidade int,
	primary key (cod),
    foreign key(cidade) references TBCidade(cod));
    
insert into TBEScola values(1,"escola 1", 1);
insert into TBEScola values(2,"escola 2", 1);
insert into TBEScola values(3,"escola 3", 1);
insert into TBEScola values(4,"escola 4", 1);
insert into TBEScola values(5,"escola 5", 1);

select count(*)
	from TBEscola, TBCidade
	where TBCidade.nome = "Curitiba"
    and TBEscola.cidade = TBCidade.cod;
    
    
    
create table TBCurso(cod int,
		nome varchar(100),
        cod_escola int,
        foreign key(cod_escola) references TBEscola(cod),
        primary key (cod));
        
create table TBEstudante(mat int,
		nome varchar(100),
        data_nasc date,
        cod_curso int,
        foreign key (cod_curso) references TBCurso(cod));
        
    
    
    
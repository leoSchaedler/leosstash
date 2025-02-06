create database teste2;

use teste2;

create table TBEscola(cod int,
	nome varchar (100),
    cidade varchar(100),
	primary key (cod));
    
insert into TBEScola values(1,"escola 1", "Curitiba");
insert into TBEScola values(2,"escola 2", "CWB");
insert into TBEScola values(3,"escola 3", "Curittiba");
insert into TBEScola values(4,"escola 4", "Curitiba");
insert into TBEScola values(5,"escola 5", "Curitiba");

select count(*)
	from TBEscola
	where cidade = "Curitiba";
    
    
    
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
        
    
    
    
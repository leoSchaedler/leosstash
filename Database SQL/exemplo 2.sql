/*
PARABENS
*/

create database hospital;

use hospital;

create table paciente(
	numero_paciente int,
    nome varchar (30),
    RG char(9),
    CPF char(11),
    rua varchar(30),
    numero char(12),
    complemento varchar(30),
    CEP char(8),
    primary key (numero_paciente)
);

create table telefones(
	numero_paciente int,
    numero int,
    foreign key (numero_paciente) references paciente(numero_paciente)
);

create table especialidade(
	cod_especialidade int,
    desc_especialidade varchar(50),
    primary key (cod_especialidade)
);

create table medico(
	CRM char(10),
    UF char(2),
    nome varchar(30),
    RG char(9),
    CPF char(11),
    cod_especialidade int,
    primary key (CRM,UF)
);

create table convenio(
	cod_convenio int,
    nome varchar(30),
    primary key (cod_convenio)
);

create table medico_convenio(
	cod_convenio int,
    CRM char(10),
    UF char (2),
    foreign key (CRM,UF) references medico(CRM,UF),
    foreign key (cod_convenio) references convenio(cod_convenio)
);

create table medicamento(
	cod_medicamento int,
    descricao varchar(50),
    primary key (cod_medicamento)
);

create table consulta(
	cod_consulta int,
    numero_paciente int,
    CRM char(10),
    UF char(2),
    data_hora datetime,
    primary key (cod_consulta),
    foreign key (numero_paciente) references paciente(numero_paciente),
    foreign key (CRM,UF) references medico(CRM,UF)
);

create table consulta_exames(
	cod_cons_ex int,
    cod_consulta int,
    codigo_exame int,
    descricao_exame varchar(50),
    primary key (cod_cons_ex),
    foreign key (cod_consulta) references consulta(cod_consulta)
);

insert into paciente values(1,'Fernando','123456789','11122233344','XV de setembro',320,'casa',68549723);

insert into especialidade values(1,'cardiologista');
insert into especialidade values(2,'Oftamologista');

insert into medico values('2351648156','PR','Fernanda','987654321','11122233344',1);
insert into medico values('8974512305','PR','Gabriel','456789123','99988877766',2);

insert into convenio values(1,'unimed');
insert into convenio values(2,'amil');

insert into medico_convenio values(1,'2351648156','PR');
insert into medico_convenio values(2,'8974512305','PR');

insert into consulta values(1,1,'2351648156','PR','2019-11-29 9:25:35');

insert into consulta_exames values(1,1,1,'raio-x');

select medico.nome 
from medico,convenio,medico_convenio 
where medico_convenio.cod_convenio = convenio.cod_convenio
and medico_convenio.CRM = medico.CRM
and medico_convenio.UF = medico.UF
and (convenio.nome = 'amil' or convenio.nome = 'unimed');

select descricao_exame
from consulta_exames, consulta
where consulta_exames.cod_consulta = consulta.cod_consulta
and consulta.data_hora > '2019-11-29' and consulta.data_hora < '2019-11-29 23:59:59';
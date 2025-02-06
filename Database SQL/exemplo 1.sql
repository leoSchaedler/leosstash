create database hospital;

use hospital;

create table paciente(
	numero_paciente int,
    nome varchar (30),
    RG char(9),
    CPF char(11),
    rua varchar(30),
    numero varchar(12),
    complemento varchar(30),
    CEP char(8),
    primary key (numero_paciente)
);

create table telefones(
	numero_paciente int,
    numero char(12),
    primary key (numero),
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
    primary key (CRM,UF),
    foreign key (cod_especialidade) references especialidade(cod_especialidade)
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
    primary key (cod_convenio,CRM,UF),
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

create table exame(
	cod_exame int,
    descricao varchar(50),
    primary key (cod_exame));
    
create table consulta_exames(
	cod_consulta int,
    codigo_exame int,
    primary key (cod_consulta,codigo_exame),
    foreign key (cod_consulta) references consulta(cod_consulta),
    foreign key (codigo_exame) references exame(cod_exame)
);

select distinct medico.nome 
	from medico,convenio,medico_convenio 
	where medico_convenio.cod_convenio = convenio.cod_convenio
			and medico_convenio.CRM = medico.CRM
			and medico_convenio.UF = medico.UF
            and convenio.cod_convenio = medico_convenio.cod_convenio
			and (convenio.nome = 'amil' or convenio.nome = 'unimed');

select exame.descricao
	from consulta_exames, consulta, exame
	where consulta_exames.cod_consulta = consulta.cod_consulta
        and exame.cod_exame = consulta_exames.codigo_exame
		and consulta.data_hora > '2019-11-29' 
        and consulta.data_hora < '2019-11-29 23:59:59';
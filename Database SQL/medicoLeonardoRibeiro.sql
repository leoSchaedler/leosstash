#drop database medico;
create database medico;
use medico;

create table tbPaciente
	(primary key(numeroPaciente), 
    numeroPaciente varchar(7),
    nome varchar(70),
    RG char(9),
    CPF char(11),
    rua varchar(30),
    numero varchar(5),
    complemento varchar(25),
    CEP char(8));
    
create table tbTelefonePaciente
	(telefone char(9),
    numeroPaciente varchar(7),
    foreign key(numeroPaciente) references tbPaciente(numeroPaciente));
    
create table tbMedico
	(primary key(CRM, UF), 
    CRM char(6),
	UF char(2), 
    nome varchar(70),
    RG char(9),
    CPF char(11)) ;

create table tbEspecializacao
	(primary key(codEspecialidade),
    codEspecialidade char(4),
    descEspecialidade varchar(60));

create table tbEspecializacaoMedico
	(CRM char(6),
	UF char(2), 
    codEspecialidade char(4),
    foreign key(CRM, UF) references tbMedico(CRM, UF),
    foreign key(codEspecialidade) references tbEspecializacao(codEspecialidade));

create table tbConvenio
	(convenio varchar(12),
    CRM char(6),
	UF char(2),
    foreign key(CRM, UF) references tbMedico(CRM, UF));

create table tbMedicamento
	(primary key(codigoMedicamento), codigoMedicamento char(8),
    descricao varchar(45));

create table tbConsulta
	(primary key(dataHora), dataHora datetime,
    numeroPaciente varchar(7),
    CRM char(6),
	UF char(2),
    foreign key(CRM, UF) references tbMedico(CRM, UF),
    foreign key(numeroPaciente) references tbPaciente(numeroPaciente));

create table tbExames
	(primary key(codigoExame), codigoExame char(6),
    descricaoExame varchar(45));

create table tbConsultaExames
	(codigoExame char(6),
    numeroPaciente varchar(7),
    CRM char(6),
    UF char(2),
    dataHora datetime,
    foreign key(codigoExame) references tbExames(codigoExame),
    foreign key(numeroPaciente) references tbPaciente(numeroPaciente),
    foreign key(CRM, UF) references tbmedico(CRM, UF),
    foreign key(dataHora) references tbConsulta(dataHora));

insert into tbPaciente values ('1','Claudio', '123456789', '12345678901', 'Rua Lambreta', 101, 'Casa 5',  '81000000');
insert into tbPaciente values ('2','Mariana', '234567890', '23456789012', 'Rua Liberdade', 789, 'AP 22', 82000000);
insert into tbPaciente values ('3','Alberto', '345678901', '34567890123', 'Rua da Santa', 12, 'AP 7', 83000000);
insert into tbPaciente values ('4','Anastacia', '456789012', '45678901234', 'Rua da Vitoria', 199, 'AP 88', 84000000);
insert into tbPaciente values ('5','Manfred', '567890123', '56789012345', 'Rua Geleira', 42, 'Galpão 6', 85000000);
insert into tbPaciente values ('666','Will', '678901234', '67890123456', 'Rua Marionete', 33, 'Trailer 0', 86000000);

insert into tbTelefonePaciente values ('999999999', '1');
insert into tbTelefonePaciente values ('999999998', '2');
insert into tbTelefonePaciente values ('999999997', '3');
insert into tbTelefonePaciente values ('999999996', '4');
insert into tbTelefonePaciente values ('999999995', '5');
insert into tbTelefonePaciente values ('999999994', '666');

insert into tbMedico values ('123456', '12', 'Adalberto', '789012345', '78901234567');
insert into tbMedico values ('234567', '23', 'Natália', '890123456', '89012345678');
insert into tbMedico values ('345678', '34', 'Leonardo', '901234567', '90123456789');

insert into tbEspecializacao values ('0026', 'Ortopedia, cuida de ossos e musculos');
insert into tbEspecializacao values ('0072', 'Cardiologia, cuida do coracao');
insert into tbEspecializacao values ('0011', 'Otorrinolaringologista, nariz, garganta e ouvidos');

insert into tbEspecializacaoMedico values ('123456', '12', '0026');
insert into tbEspecializacaoMedico values ('234567', '23', '0072');
insert into tbEspecializacaoMedico values ('345678', '34', '0011');

insert into tbConvenio values ('Unimed', '123456', '12');
insert into tbConvenio  values ('Amil', '123456', '12');
insert into tbConvenio  values ('Unimed', '234567', '23');
insert into tbConvenio  values ('Amil', '345678', '34');
insert into tbConvenio  values ('Unimed', '345678', '34');

insert into tbMedicamento values ('00000001', 'Gelol, combate a dores musculares.');
insert into tbMedicamento values ('00000023', 'Nasonex, spray nasal.');
insert into tbMedicamento values ('00000111', 'CoraForte, ajuda o coracao.');

insert into tbConsulta values ('2019-05-13 13:50:00', '1', '123456', '12');
insert into tbConsulta values ('2019-06-11 13:00:00', '5', '123456', '12');
insert into tbConsulta values ('2020-07-13 16:50:00', '4', '234567', '23');
insert into tbConsulta values ('2020-01-13 18:00:00', '2', '234567', '23');
insert into tbConsulta values ('2019-11-29 10:00:00', '3', '123456', '12');
insert into tbConsulta values ('2020-11-09 11:00:00', '666', '345678', '34');

insert into tbExames values ('000001','Ecografia, exame de busca e checagem');
insert into tbExames values ('000002','Raio-X do peito, verificar tumores');
insert into tbExames values ('001001','Sangue, verificar por valores estranhos');
insert into tbExames values ('070001','Exame de Mama, procurar por nódulos');

insert into tbConsultaExames values ('001001', '1', '234567', '23', '2019-05-13 13:50:00');
insert into tbConsultaExames values ('070001', '2', '234567', '23', '2019-06-11 13:00:00');
insert into tbConsultaExames values ('001001', '3', '345678', '34', '2020-07-13 16:50:00');
insert into tbConsultaExames values ('001001', '5', '234567', '23', '2019-11-29 10:00:00');
insert into tbConsultaExames values ('001001', '666', '345678', '34', '2020-11-09 11:00:00');

SELECT tbConvenio.convenio, tbMedico.nome
FROM tbMedico, tbConvenio
WHERE tbConvenio.convenio = ('Unimed' or 'Amil')
	and tbConvenio.CRM = tbMedico.CRM;

SELECT *
FROM tbConsultaExames
WHERE date(dataHora) = '2019-11-29';
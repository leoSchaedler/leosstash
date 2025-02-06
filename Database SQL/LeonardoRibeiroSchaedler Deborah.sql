/*

nao atende a pelo menos um destes criterios

P1 - Mapear o modelo fisico de tal forma que nao permita inconsistencia

P2 - adequacao de tipo, magnitude e respeito a atributos compostos

*/

-- drop database hotel;
create database hotel;
use hotel;

create table tbApartamento
(PRIMARY KEY(numero), numero int, 
andar int, nCamas int, preco int) ;

create table tbCliente
(PRIMARY KEY(email), email varchar(20), nome varchar(15), 
estado varchar(12), cidade varchar(12), CEP char(8), 
rua varchar(25), numero int, complemento varchar(10));

create table tbTelefonesCliente
(FOREIGN KEY(email) references tbCliente(email), email varchar(20), numeroTelefone char(9));

create table tbClientePessoaFisica
(FOREIGN KEY(email) references tbCliente(email), email varchar(20), 
CPF char(11), RG char(9), Sexo char);

create table tbClientePessoaJuridica
(FOREIGN KEY(email) references tbCliente(email), email varchar(20), 
CNPJ char(14), razaoSocial varchar(45));

create table tbHospedagemCheckIn
(PRIMARY KEY(codigoDeReserva), codigoDeReserva int, 
FOREIGN KEY(email) references tbCliente(email), email varchar(20),
FOREIGN KEY(numero) references tbApartamento(numero), numero int,
dataCheckIn date, horarioCheckIn time, dataCheckOut date, horarioCheckOut time);

create table tbColaboradorAutorizado
(PRIMARY KEY(CPF), CPF char(11), 
nome varchar(15));

create table tbAluguelJuridico
(FOREIGN KEY(CPF) references tbColaboradorAutorizado(CPF), CPF char(11),
FOREIGN KEY(email) references tbCliente(email), email varchar(20));

create table tbServico
(PRIMARY KEY(codigo), codigo int, tipo varchar(15),
descricaoDoServico varchar(50), valor int);

create table tbConsumo
(FOREIGN KEY(codigo) references tbServico(codigo), codigo int,
FOREIGN KEY(codigoDeReserva) references tbHospedagemCheckIn(codigoDeReserva), codigoDeReserva int);

insert into tbApartamento values (01, 00, 2, 200);
insert into tbApartamento values (02, 00, 2, 200);
insert into tbApartamento values (03, 01, 2, 250);
insert into tbApartamento values (04, 01, 3, 300);
insert into tbApartamento values (05, 01, 3, 300);
insert into tbApartamento values (06, 02, 7, 1000);

insert into tbCliente values ('shioued@email.com', 'Claudio', 'Paraná', 'Curitiba', 81000000, 'Rua Lambreta', 101, 'Casa 5');
insert into tbCliente values ('jtsirouj@email.com', 'Mariana', 'Paraná', 'Curitiba', 82000000, 'Rua Liberdade', 789, 'AP 22');
insert into tbCliente values ('esdrihj@email.com', 'Alberto', 'Paraná', 'Curitiba', 83000000, 'Rua da Santa', 12, 'AP 7');
insert into tbCliente values ('RSTARG@email.com', 'RSTARG', 'Paraná', 'Curitiba', 84000000, 'Rua da Vitoria', 199, 'AP 88');
insert into tbCliente values ('diceo@email.com', 'Dice', 'Paraná', 'Curitiba', 85000000, 'Rua Geleira', 42, 'Galpão 6');
insert into tbCliente values ('postal2@email.com', 'RWS', 'Paraná', 'Curitiba', 86000000, 'Rua Marionete', 33, 'Trailer 0');

insert into tbTelefonesCliente values ('shioued@email.com', 999999999);
insert into tbTelefonesCliente values ('jtsirouj@email.com', 999999998);
insert into tbTelefonesCliente values ('esdrihj@email.com', 999999997);
insert into tbTelefonesCliente values ('RSTARG@email.com', 999999996);
insert into tbTelefonesCliente values ('RSTARG@email.com', 999999995);
insert into tbTelefonesCliente values ('diceo@email.com', 999999994);
insert into tbTelefonesCliente values ('diceo@email.com', 999999993);
insert into tbTelefonesCliente values ('postal2@email.com', 999999992);
insert into tbTelefonesCliente values ('postal2@email.com', 999999991);

insert into tbClientePessoaFisica values ('shioued@email.com', 12312312312, 123456789, 'M');
insert into tbClientePessoaFisica values ('jtsirouj@email.com', 12312312313, 123456788, 'F');
insert into tbClientePessoaFisica values ('esdrihj@email.com', 12312312311, 123456787, 'M');

insert into tbClientePessoaJuridica values ('RSTARG@email.com', 12345678901234, 'Rockstar Games');
insert into tbClientePessoaJuridica values ('diceo@email.com', 12345678901233, 'Dice Conglomerate');
insert into tbClientePessoaJuridica values ('postal2@email.com', 12345678901232, 'Running With Scissors INC.');

insert into tbHospedagemCheckIn values (11, 'shioued@email.com', 01, '2020-02-06', 11.00, '2020-02-09', 10.00);
insert into tbHospedagemCheckIn values (12, 'jtsirouj@email.com', 02, '2020-02-02', 10.00, '2020-06-03', 10.00);
insert into tbHospedagemCheckIn values (13, 'esdrihj@email.com', 03, '2020-07-01', 10.00, '2020-08-01', 12.00);
insert into tbHospedagemCheckIn values (14, 'RSTARG@email.com', 04, '2020-04-04', 11.00, '2020-04-06', 12.00);
insert into tbHospedagemCheckIn values (15, 'diceo@email.com', 05, '2019-07-02', 11.00, '2020-07-03', 11.00);
insert into tbHospedagemCheckIn values (16, 'postal2@email.com', 06, '2019-06-06', 12.00, '2019-09-01', 13.00);

insert into tbColaboradorAutorizado values (02312312312, 'Leopoldo');
insert into tbColaboradorAutorizado values (02312312313, 'Alessandra');
insert into tbColaboradorAutorizado values (02312312311, 'Mauro');

insert into tbAluguelJuridico values (02312312312, 'RSTARG@email.com');
insert into tbAluguelJuridico values (02312312313, 'diceo@email.com');
insert into tbAluguelJuridico values (02312312311, 'postal2@email.com');

insert into tbServico values (01, 'Alimentacao', 'Duplo cheeseburguer com fritas levadas ao quarto', 24);
insert into tbServico values (02, 'Alimentacao', 'Vinho branco levado ao quarto com rosas rosas', 79);
insert into tbServico values (03, 'Entreterimento', 'Massagem com pedras quentes', 89);
insert into tbServico values (04, 'Bem Estar', 'Lavanderia com funcionarios(as)', 19);

insert into tbConsumo values (01, 11);
insert into tbConsumo values (02, 12);
insert into tbConsumo values (03, 13);
insert into tbConsumo values (03, 14);
insert into tbConsumo values (02, 15);
insert into tbConsumo values (01, 16);

SELECT COUNT(email)
FROM tbCliente;

SELECT COUNT(dataCheckIn)
FROM tbHospedagemCheckIn
WHERE year(dataCheckIn) = 2020 and month(dataCheckIn) = 02;

SELECT SUM(tbApartamento.preco + tbServico.valor)
FROM tbApartamento,	tbHospedagemCheckIn, tbServico,	tbConsumo
WHERE year(tbHospedagemCheckIn.dataCheckIn) = 2019
											  and tbApartamento.numero = tbHospedagemCheckIn. numero
                                              and tbHospedagemCheckIn.codigoDeReserva = tbConsumo.codigoDeReserva
                                              and tbServico.codigo = tbConsumo.codigo;
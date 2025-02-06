create database hotel;
use hotel;

create table tbApartamento
(PRIMARY KEY(numero), numero int, 
andar int, nCamas int, preco int) ;

create table tbCliente
(PRIMARY KEY(email), email varchar(20), nome varchar(15), 
estado varchar(12), cidade varchar(12), CEP int, 
rua varchar(25), numero int, complemento varchar(10));

create table tbTelefonesCliente
(FOREIGN KEY(email) references tbCliente(email), email varchar(20), numeroTelefone int);

create table tbClientePessoaFisica
(FOREIGN KEY(email) references tbCliente(email), email varchar(20), 
CPF int, RG int, Sexo char);

create table tbClientePessoaJuridica
(FOREIGN KEY(email) references tbCliente(email), email varchar(20), 
CNPJ int, razaoSocial varchar(45));

create table tbHospedagemCheckIn
(PRIMARY KEY(codigoDeReserva), codigoDeReserva int, 
FOREIGN KEY(email) references tbCliente(email), email varchar(20),
FOREIGN KEY(numero) references tbApartamento(numero), numero int,
dataCheckIn date, horarioCheckIn time, dataCheckOut date, horarioCheckOut time);

create table tbColaboradorAutorizado
(PRIMARY KEY(CPF), CPF int, 
nome varchar(15));

create table tbAluguelJuridico
(FOREIGN KEY(CPF) references tbColaboradorAutorizado(CPF), CPF int,
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

insert into tbCliente values ('shioued@email.com', 'Claudio', 'Paraná', 'Curitiba', 810, 'Rua Lambreta', 101, 'Casa 5');
insert into tbCliente values ('jtsirouj@email.com', 'Mariana', 'Paraná', 'Curitiba', 812, 'Rua Liberdade', 789, 'AP 22');
insert into tbCliente values ('esdrihj@email.com', 'Alberto', 'Paraná', 'Curitiba', 813, 'Rua da Santa', 12, 'AP 7');
insert into tbCliente values ('RSTARG@email.com', 'RSTARG', 'Paraná', 'Curitiba', 811, 'Rua da Vitoria', 199, 'AP 88');
insert into tbCliente values ('diceo@email.com', 'Dice', 'Paraná', 'Curitiba', 742, 'Rua Geleira', 42, 'Galpão 6');
insert into tbCliente values ('postal2@email.com', 'RWS', 'Paraná', 'Curitiba', 666, 'Rua Marionete', 33, 'Trailer 0');

insert into tbTelefonesCliente values ('shioued@email.com', 3455);
insert into tbTelefonesCliente values ('jtsirouj@email.com', 3241);
insert into tbTelefonesCliente values ('esdrihj@email.com', 3233);
insert into tbTelefonesCliente values ('RSTARG@email.com', 9565);
insert into tbTelefonesCliente values ('RSTARG@email.com', 7345);
insert into tbTelefonesCliente values ('diceo@email.com', 1233);
insert into tbTelefonesCliente values ('diceo@email.com', 5343);
insert into tbTelefonesCliente values ('postal2@email.com', 7171);
insert into tbTelefonesCliente values ('postal2@email.com', 6666);

insert into tbClientePessoaFisica values ('shioued@email.com', 123, 1234, 'M');
insert into tbClientePessoaFisica values ('jtsirouj@email.com', 456, 4567, 'F');
insert into tbClientePessoaFisica values ('esdrihj@email.com', 789, 7890, 'M');

insert into tbClientePessoaJuridica values ('RSTARG@email.com', 2345, 'Rockstar Games');
insert into tbClientePessoaJuridica values ('diceo@email.com', 5678, 'Dice Conglomerate');
insert into tbClientePessoaJuridica values ('postal2@email.com', 8901, 'Running With Scissors INC.');

insert into tbHospedagemCheckIn values (11, 'shioued@email.com', 01, '2020-02-06', 11.00, '2020-02-09', 10.00);
insert into tbHospedagemCheckIn values (12, 'jtsirouj@email.com', 02, '2020-02-02', 10.00, '2020-06-03', 10.00);
insert into tbHospedagemCheckIn values (13, 'esdrihj@email.com', 03, '2020-07-01', 10.00, '2020-08-01', 12.00);
insert into tbHospedagemCheckIn values (14, 'RSTARG@email.com', 04, '2020-04-04', 11.00, '2020-04-06', 12.00);
insert into tbHospedagemCheckIn values (15, 'diceo@email.com', 05, '2019-07-02', 11.00, '2020-07-03', 11.00);
insert into tbHospedagemCheckIn values (16, 'postal2@email.com', 06, '2019-06-06', 12.00, '2019-09-01', 13.00);

insert into tbColaboradorAutorizado values (111, 'Leopoldo');
insert into tbColaboradorAutorizado values (211, 'Alessandra');
insert into tbColaboradorAutorizado values (311, 'Mauro');

insert into tbAluguelJuridico values (111, 'RSTARG@email.com');
insert into tbAluguelJuridico values (211, 'diceo@email.com');
insert into tbAluguelJuridico values (311, 'postal2@email.com');

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
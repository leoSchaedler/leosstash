drop database reservaCar; 
create database reservaCar;
use reservaCar;

create table tbCarro
(PRIMARY KEY(chassi), chassi varchar(20), placa varchar(15), tipoComb varchar(12), 
disponibilidade varchar(15), aroRodas int, litragem int, transmissao varchar(15), 
ignicao varchar(15), modelo varchar(15), marca varchar(15)) ;

create table tbClientePessoaFisica
(PRIMARY KEY(email), email varchar(20), nome varchar(15), sobrenome varchar(25), 
confianca int, fidelidade date, altura int, peso int, 
sexo varchar(10), idade int, cc int, vecnCC date, CPF int, CNH int, vencCNH date) ;

create table tbClientePessoaJuridica
(PRIMARY KEY(email), email varchar(20), nome varchar(15), sobrenome varchar(25), 
confianca int, fidelidade date, altura int, peso int, 
sexo varchar(10), idade int, cc int, vecnCC date, CNPJ int, razaoSocial varchar(20)) ;

create table tbReservaClientePessoaFisica
(email varchar(20), FOREIGN KEY(email) references tbClientePessoaFisica(email),  
chassi varchar(20), FOREIGN KEY(chassi) references tbCarro(chassi),  dataInicio date, dataFim date);

create table tbReservaClientePessoaJuridica
(FOREIGN KEY(email) references tbClientePessoaJuridica(email), email varchar(20), 
FOREIGN KEY(chassi) references tbCarro(chassi), chassi varchar(20), dataInicio date, dataFim date);

insert into tbCarro values ('128037127890', '1280S890', 'Alcool', 'Disp', 17, 1, 'Automatica', 'Botao', 'Prius', 'SD');
insert into tbCarro values ('128036858970', '132S8900', 'Gasolina', 'Indisp', 17, 2, 'Manual', 'Chave', 'Dodge', 'DS');
insert into tbCarro values ('123789576673', '1650S89R', 'Alcool', 'Disp', 17, 2, 'Automatica', 'Botao', 'Camar', 'DD');
insert into tbCarro values ('129745654680', '1R809780', 'Gasolina', 'Indisp', 17, 2, 'Manual', 'Chave', 'Lambar', 'TT');
insert into tbCarro values ('263423427678', '12GAS890', 'Gasolina', 'Disp', 17, 3, 'Automatica', 'Botao', 'Ferrire', 'SW');

insert into tbClientePessoaFisica values ('asd@ig.com', 'Ura', 'Ota', 80, 24/04/2010, 170, 100, 'Masculino', 27, 23978, 06/06/2016, 124390, 4256746, 06/06/2020);
insert into tbClientePessoaFisica values ('hdsgat@ig.com', 'Pop', 'Pa', 80, 09/04/2010, 180, 98, 'Masculino', 56, 2355423, 06/07/2015, 1423590, 626746, 06/07/2020);
insert into tbClientePessoaFisica values ('a678fgj@ig.com', 'Lala', 'Sab', 100, 23/05/2010, 160, 50, 'Feminino', 19, 23234, 06/08/2016, 12364236, 342346, 06/07/2020);
insert into tbClientePessoaFisica values ('att56345@g.com', 'Orra', 'Orq', 80, 24/08/2010, 190, 800, 'Masculino', 22, 2364355, 06/09/2018, 1254590, 366746, 07/06/2020);
insert into tbClientePessoaFisica values ('ghs65@ig.com', 'Opa', 'Ota', 80, 12/04/2010, 200, 90, 'Masculino', 32, 27456, 06/06/2017, 1253450, 6364545, 07/07/2020);

insert into tbClientePessoaJuridica values ('jfhjudf.com', 'Opa', 'Ota', 90, 12/04/2012, 169, 70, 'Masculino', 95, 27456, 06/09/2020, 120850, 'Busca');
insert into tbClientePessoaJuridica values ('urghjtd@ig.com', 'Opo', 'Ote', 90, 12/07/2011, 183, 80, 'Masculino', 97, 65236, 09/06/2017, 13537, 'Liberdade');
insert into tbClientePessoaJuridica values ('jdfghasef@ig.com', 'Opu', 'Oti', 90, 17/04/2010, 176, 93, 'Masculino', 92, 735334, 06/06/2017, 18950, 'Sufoco');
insert into tbClientePessoaJuridica values ('cvbhjdfthg@ig.com', 'Opi', 'Oto', 70, 28/04/2010, 175, 96, 'Masculino', 45, 73452, 09/06/2015, 13450, 'Derrota');
insert into tbClientePessoaJuridica values ('jdydfstgdsrt5@ig.com', 'Ope', 'Otu', 100, 12/04/2009, 200, 90, 'Masculino', 61, 254366, 25/12/2019, 000450, 'Fracasso');

insert into tbReservaClientePessoaFisica values ('asd@ig.com', '128037127890', 12/04/2018, 12/05/2018);
insert into tbReservaClientePessoaFisica values ('hdsgat@ig.com', '128036858970', 12/04/2018, 12/05/2018);
insert into tbReservaClientePessoaFisica values ('a678fgj@ig.com', '123789576673', 12/02/2018, 12/04/2018);
insert into tbReservaClientePessoaFisica values ('att56345@g.com', '129745654680', 12/01/2018, 12/04/2018);
insert into tbReservaClientePessoaFisica values ('ghs65@ig.com', '263423427678', 12/04/2017, 12/08/2017);

insert into tbReservaClientePessoaJuridica values ('jfhjudf.com', '128037127890', 12/06/2018, 12/07/2018);
insert into tbReservaClientePessoaJuridica values ('urghjtd@ig.com', '128036858970', 12/04/2017, 12/06/2017);
insert into tbReservaClientePessoaJuridica values ('jdfghasef@ig.com', '123789576673', 12/04/2016, 12/07/2016);
insert into tbReservaClientePessoaJuridica values ('cvbhjdfthg@ig.com', '129745654680', 12/04/2016, 12/09/2016);
insert into tbReservaClientePessoaJuridica values ('jdydfstgdsrt5@ig.com', '263423427678', 12/04/2016, 12/03/2017);

SELECT tbClientePessoaJuridica.nome, tbReservaClientePessoaJuridica.email
FROM tbClientePessoaJuridica, tbReservaClientePessoaJuridica
WHERE  tbReservaClientePessoaJuridica.email = tbClientepessoaJuridica.email;

SELECT tbReservaClientePessoaFisica.email, tbCarro.modelo or tbReservaClientePessoaJuridica.email, tbCarro.placa
FROM tbReservaClientePessoaFisica, tbReservaClientePessoaJuridica, tbCarro, tbClientePessoaFisica, tbClientePessoaJuridica
WHERE  tbReservaClientePessoaJuridica.chassi = tbCarro.chassi and tbReservaClientePessoaFisica.chassi = tbCarro.chassi 
					and tbReservaClientePessoaFisica.email = tbClientePessoaFisica.email and tbReservaClientePessoaJuridica.email = tbClientePessoaJuridica.email


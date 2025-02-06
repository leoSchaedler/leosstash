drop database campeonatoFutebol;
create database campeonatoFutebol;
use campeonatoFutebol;

create table tbTime
	(primary key(codigoDeIdentificacao), 
    codigoDeIdentificacao int,
    nomeTime varchar(45),
    dataCriacao date,
    cidadeDeOrigem varchar(25),
    totalJogadores int,
    numDeTitulos int,
    pontosCampeonato int);
    
create table tbUniforme
	(corUniforme varchar(20),
    brasaoUniforme varchar(30),
    codigoDeIdentificacao int,
    foreign key(codigoDeIdentificacao) references tbTime(codigoDeIdentificacao));
    
create table tbJogador
	(primary key(ID), 
    ID int,
	nomeJogador varchar(70), 
    nascJogador date,
    numDeGols int);

create table tbGoleiro
	(primary key(ID), 
    ID int,
	nomeGoleiro varchar(70), 
    nascGoleiro date,
    numDeGols int,
    numDeDefesas int);

create table tbTecnico
	(primary key(ID), 
    ID int,
	nomeTecnico varchar(70), 
    nascTecnico date);

create table tbArbitro
	(primary key(ID), 
    ID int,
	nomeArbitro varchar(70), 
    nacionalidade varchar(25));

create table tbEstadio
	(primary key(nome), 
    nome varchar(30),
    cidadeLocal varchar(25),
    capacidade int,
    anoUltimaRenovacao year,
    anoDeConstrucao year,
    timeProprietario int,
    foreign key(timeProprietario) references tbTime(codigoDeIdentificacao));


create table tbPartidas
	(primary key(idc), 
    idc int,
    dataHora datetime,
    cidadePartida varchar(25),
    estadioPartida varchar(30),
    arbitroPartida int,
	foreign key(estadiopartida) references tbEstadio(nome),
    foreign key(arbitroPartida) references tbArbitro(ID));

create table tbGandula
	(primary key(ID), 
    ID int,
	nomeGandula varchar(70));

create table tbContrato
	(primary key(cod), 
     cod int,
     idJogador int,
    codigoTime int,
    foreign key(idJogador) references tbJogador(ID),
    foreign key(codigoTime) references tbTime(codigoDeIdentificacao));
    
create table tbContratoGoleiro
	(primary key(cod), 
     cod int,
     idGoleiro int,
    codigoTime int,
    foreign key(idGoleiro) references tbGoleiro(ID),
    foreign key(codigoTime) references tbTime(codigoDeIdentificacao));
    
create table tbComandaTime
	(primary key(cod), 
     cod int,
     idTecnico int,
    codigoTime int,
    foreign key(idTecnico) references tbTecnico(ID),
    foreign key(codigoTime) references tbTime(codigoDeIdentificacao));
    
create table tbTimeParticipaPartida
	(primary key(cod), 
     cod int,
     idcPartida int,
    codigoTime int,
    foreign key(idcPartida) references tbPartidas(idc),
    foreign key(codigoTime) references tbTime(codigoDeIdentificacao));
    
create table tbEstadioSediaPartida
	(primary key(cod), 
     cod int,
     nomeEstadio varchar(30),
    idcPartida int,
    foreign key(nomeEstadio) references tbEstadio(nome),
    foreign key(idcPartida) references tbPartidas(idc));
    
create table tbGandulaAuxPartida
	(primary key(cod), 
     cod int,
	idGandula int,
    idcPartida int,
    foreign key(idGandula) references tbGandula(ID),
    foreign key(idcPartida) references tbPartidas(idc));
    
create table tbArbitroApitaPartida
	(primary key(cod), 
     cod int,
	idArbitro int,
    idcPartida int,
    foreign key(idArbitro) references tbArbitro(ID),
    foreign key(idcPartida) references tbPartidas(idc));

    
insert into tbTime values(1234, 'Maravilha', '1996-05-23', 'Maravilha', 14, 1, 27);
insert into tbTime values(1235, 'Nautica', '1994-02-11', 'Baia', 13, 2, 31);
insert into tbTime values(1236, 'Gama', '1989-01-28', 'Pontal', 18, 3, 42);
insert into tbTime values(1237, 'Lorota', '1999-09-09', 'Lambo', 16, 2, 24);
insert into tbTime values(1238, 'Ham', '2001-06-13', 'Burguer', 12, 0, 19);
insert into tbTime values(1239, 'Que', '2001-08-12', 'Ijo', 16, 0, 18);
    
insert into tbUniforme values('Vermelho', 'Maravilha1999', 1234);
insert into tbUniforme values('AzulMarinho', 'Nautica2015', 1235);
insert into tbUniforme values('Preto', 'Gama2013', 1236);
insert into tbUniforme values('VerdeLima', 'Lorota2018', 1237);
insert into tbUniforme values('Rosa', 'Ham2019', 1238);

insert into tbJogador values(123456, 'Alex', '2000-11-11', 31);
insert into tbJogador values(123457, 'Bernardo', '2001-10-11', 21);
insert into tbJogador values(123458, 'Dante', '1998-07-01', 72);
insert into tbJogador values(123459, 'Fabricio', '2000-12-12', 14);
insert into tbJogador values(123450, 'Giovane', '2001-10-18', 55);

insert into tbContrato values(001, 123456, 1234);
insert into tbContrato values(002, 123457, 1235);
insert into tbContrato values(003, 123458, 1236);
insert into tbContrato values(004, 123459, 1237);
insert into tbContrato values(005, 123450, 1238);
    
insert into tbGoleiro values(123451, 'Leonardo', '2001-11-08', 40, 171);
insert into tbGoleiro values(123452, 'Patrick', '1998-08-13', 14, 155);
insert into tbGoleiro values(123453, 'Solair', '2001-12-12', 21, 199);
insert into tbGoleiro values(123454, 'Jair', '2000-11-17', 32, 164);
insert into tbGoleiro values(123455, 'Felipe', '2001-09-09', 10, 187);

insert into tbContratoGoleiro values(001, 123451, 1238);
insert into tbContratoGoleiro values(002, 123452, 1237);
insert into tbContratoGoleiro values(003, 123453, 1236);
insert into tbContratoGoleiro values(004, 123454, 1235);
insert into tbContratoGoleiro values(005, 123455, 1234);

insert into tbTecnico values(012345, 'Adalberto', '1972-09-09');
insert into tbTecnico values(012346, 'Albert', '1981-07-10');
insert into tbTecnico values(012347, 'Manfred', '1992-08-11');
insert into tbTecnico values(012348, 'Alfred', '1969-09-12');
insert into tbTecnico values(012349, 'Jaspion', '1995-03-02');

insert into tbComandaTime values(001, 012345, 1234);
insert into tbComandaTime values(002, 012346, 1235);
insert into tbComandaTime values(003, 012347, 1236);
insert into tbComandaTime values(004, 012348, 1237);
insert into tbComandaTime values(005, 012349, 1238);
    
insert into tbArbitro values(112345, 'Enzo', 'Brasileiro');
insert into tbArbitro values(112346, 'Price', 'Ingles');
insert into tbArbitro values(112347, 'Gaz', 'Ingles');
insert into tbArbitro values(112348, 'Griggs', 'Americano');
insert into tbArbitro values(112349, 'Lambert', 'Italiano');
insert into tbArbitro values(112340, 'Jacquin', 'Frances');

insert into tbGandula values(000001, 'Marcos');
insert into tbGandula values(000002, 'Jim');
insert into tbGandula values(000003, 'Bruno');
insert into tbGandula values(000004, 'Eduardo');
insert into tbGandula values(000005, 'Gabriel');

insert into tbEstadio values('Arena', 'Maravilha', 35000, '2017', '1996', 1234);
insert into tbEstadio values('Warship', 'Baia', 22000, '2014', '1995', 1235);
insert into tbEstadio values('Porto', 'Pontal', 52000, '2018', '1990', 1236);
insert into tbEstadio values('Charada', 'Lambo', 30000, '2009', '2000', 1237);
insert into tbEstadio values('Combo', 'Burguer', 19000, '2001', '2001', 1238);

insert into tbPartidas values(000091, '2020-01-25 14:30:00', 'Maravilha', 'Arena', 112349);
insert into tbPartidas values(000092, '2020-02-21 13:30:00', 'Baia', 'Warship', 112348);
insert into tbPartidas values(000093, '2020-03-27 14:30:00', 'Pontal', 'Porto', 112347);
insert into tbPartidas values(000094, '2020-04-28 15:30:00', 'Lambo', 'Charada', 112346);
insert into tbPartidas values(000095, '2020-09-06 16:30:00', 'Burguer', 'Combo', 112345);

insert into tbTimeParticipaPartida values(001, 000091, 1234);
insert into tbTimeParticipaPartida values(002, 000091, 1235);
insert into tbTimeParticipaPartida values(003, 000092, 1235);
insert into tbTimeParticipaPartida values(004, 000092, 1234);
insert into tbTimeParticipaPartida values(005, 000093, 1236);
insert into tbTimeParticipaPartida values(006, 000093, 1237);
insert into tbTimeParticipaPartida values(007, 000094, 1237);
insert into tbTimeParticipaPartida values(008, 000094, 1236);
insert into tbTimeParticipaPartida values(009, 000095, 1238);
insert into tbTimeParticipaPartida values(010, 000095, 1239);

insert into tbEstadioSediaPartida values(001, 'Arena', 000091);
insert into tbEstadioSediaPartida values(002, 'Warship', 000092);
insert into tbEstadioSediaPartida values(003, 'Porto', 000093);
insert into tbEstadioSediaPartida values(004, 'Charada', 000094);
insert into tbEstadioSediaPartida values(005, 'Combo', 000095);

insert into tbGandulaAuxPartida values(001, 000001, 000091);
insert into tbGandulaAuxPartida values(002, 000001, 000092);
insert into tbGandulaAuxPartida values(003, 000002, 000093);
insert into tbGandulaAuxPartida values(004, 000003, 000094);
insert into tbGandulaAuxPartida values(005, 000004, 000095);

insert into tbArbitroApitaPartida values(001, 112345 , 000091);
insert into tbArbitroApitaPartida values(002, 112346 , 000092);
insert into tbArbitroApitaPartida values(003, 112347 , 000093);
insert into tbArbitroApitaPartida values(004, 112348 , 000094);
insert into tbArbitroApitaPartida values(005, 112349 , 000095);

#	- Qual jogador possui o maior número de gols?

SELECT tbJogador.nomeJogador, tbJogador.numDeGols
FROM tbJogador
GROUP BY tbJogador.nomeJogador
ORDER BY tbJogador.numDeGols DESC		
LIMIT    1;


#	- Qual o goleiro com o maior número de defesas?

SELECT tbGoleiro.nomeGoleiro, tbGoleiro.numDeDefesas
FROM tbGoleiro
GROUP BY tbGoleiro.nomeGoleiro
ORDER BY tbGoleiro.numDeDefesas DESC
LIMIT    1;

#	- Quais equipes possuem estádios construídos após 1999?

SELECT tbTime.nomeTime, tbEstadio.anoDeConstrucao
FROM tbTime, tbEstadio
WHERE tbTime.codigoDeIdentificacao = tbEstadio.timeProprietario and tbEstadio.anoDeConstrucao > 1999; 

#	- Quantas equipes não possuem estádio?

SELECT COUNT(tbTime.codigoDeIdentificacao)
FROM tbTime
WHERE tbTime.codigoDeIdentificacao
NOT IN (SELECT tbEstadio.timeProprietario
		FROM tbEstadio);

#	- Quantos gandulas não auxiliaram em nenhuma partida?

SELECT COUNT(tbGandula.ID)
FROM tbGandula
WHERE tbGandula.ID
NOT IN (SELECT tbGandulaAuxPartida.idGandula
		FROM tbGandulaAuxPartida);

#	- Quantos árbitros nunca apitaram nenhuma partida?

SELECT COUNT(tbArbitro.ID)
FROM tbArbitro
WHERE tbArbitro.ID
NOT IN (SELECT tbPartidas.arbitroPartida
		FROM tbPartidas);

#	- Qual(is) gandula(s) já auxiliou(aram) alguma partida?

SELECT DISTINCT tbGandula.nomeGandula
FROM tbGandula, tbGandulaAuxPartida
WHERE tbGandula.ID = tbGandulaAuxPartida.idGandula;
    
#	- Quanto(s) técnico(s) já comandou(aram) uma equipe?

SELECT COUNT(tbTecnico.nomeTecnico)
FROM tbTecnico, tbComandaTime
WHERE tbTecnico.ID = tbComandaTime.idTecnico;

#	- Qual(is) árbitro(s) é(são) brasileiro(s) e já apitou(aram) uma partida em um estádio com capacidade superior a 10.000 pessoas?

SELECT tbArbitro.nomeArbitro
FROM tbArbitro, tbPartidas, tbEstadio
WHERE tbArbitro.nacionalidade = 'Brasileiro'
	and tbArbitro.ID = tbPartidas.arbitroPartida 
    and tbEstadio.nome = tbPartidas.estadioPartida
    and tbEstadio.capacidade > 10000;

#	- Qual(is) jogador(es) nasceu(ram) antes de 2000 e tem(têm) contrato com um time que foi fundado antes de 2000? 

SELECT tbJogador.nomeJogador
FROM tbJogador, tbContrato,  tbTime
WHERE year(tbJogador.nascJogador) < 2000
	and tbJogador.ID = tbContrato.idJogador
    and tbTime.codigoDeIdentificacao = tbContrato.codigoTime
    and year(tbTime.dataCriacao) < 2000;




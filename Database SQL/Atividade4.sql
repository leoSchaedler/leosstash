drop database atividade4; 
create database atividade4;
use atividade4;

create table tbCliente(PRIMARY KEY(RG), RG INT, nome varchar(15), Sexo varchar(1), endereco varchar(30), email varchar(20));

create table tbTelefoneCliente(FOREIGN KEY(RG) references tbCliente(RG), RG int, telefone INT);

create table tbApartamento(PRIMARY KEY(Numero), Numero INT, Andar INT, Numero_Camas INT, Preco INT);

create table tbReserva(Numero_Reserva INT, PRIMARY KEY(Numero_Reserva), RG INT, FOREIGN KEY(RG) references
	tbCliente(RG), Numero_AP INT, FOREIGN KEY(Numero_AP) references tbApartamento(Numero));

create table CheckIn(Numero_Reserva INT, FOREIGN KEY(Numero_Reserva) references tbReserva(Numero_Reserva), 
	Data_Entrada INT, Hora_Entrada INT, Data_Saida INT, Hora_Saida INT);
    
create table tbServicos(Codigo INT, PRIMARY KEY(Codigo), Tipo varchar(10), Descricao varchar(40), valor INT);

create table Consumo(RG INT, Codigo INT, FOREIGN KEY(RG) references tbCliente(RG), 
	FOREIGN KEY(Codigo) references tbServicos(Codigo));

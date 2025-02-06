Create database departamento;
use departamento;

create table tbDepartamento(PRIMARY KEY(numero), numero INT, nome varchar(15), sigla varchar(3));

create table tbLocalizacao(PRIMARY KEY(codigo), codigo INT, descricao varchar(50));

create table tbDepartamento_Localizacao(numero_depto INT, FOREIGN KEY(numero_depto) references tbDepartamento(numero),
	codigo_localizacao INT, FOREIGN KEY(codigo_localizacao) references tbLocalizacao(codigo));
    
create table tbEmpregado(PRIMARY KEY(Numero_BI), Numero_BI INT, pre_nome varchar(10), sobrenome varchar(20), 
	endereco varchar(1), CPF INT, sexo varchar(1), Numero_BI_Supervisor INT, FOREIGN KEY(Numero_BI_Supervisor) 
    references tbEmpregado(Numero_BI), numero_departamento INT, FOREIGN KEY(numero_departamento) references tbDepartamento(numero));
    
create table tbGerencia(numero_departamento INT, FOREIGN KEY(numero_departamento) references tbDepartamento(numero),
	numero_BI INT, FOREIGN KEY(numero_BI) references tbEmpregado(Numero_BI));
    
create table tbProjeto(PRIMARY KEY(numero), numero INT, nome varchar(15), codigo_localizacao INT, FOREIGN KEY(codigo_localizacao) 
	references tbLocalizacao(codigo), numero_departamento INT, FOREIGN KEY(numero_departamento) references tbEmpregado(numero_departamento));
    
create table tbTrabalha(Numero_BI INT, FOREIGN KEY(Numero_BI) references tbEmpregado(Numero_BI), numero_projeto INT, FOREIGN KEY(numero_projeto) 
	references tbProjeto(numero), horas INT);
    
create table tbDependente(Numero_BI INT, FOREIGN KEY(Numero_BI) references tbEmpregado(Numero_BI), PRIMARY KEY(Sequencia), 
	Sequencia INT, nome varchar(15), grau varchar(10), data_nasc INT);

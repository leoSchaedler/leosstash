/*drop database atividade3;*/
create database atividade3;
use atividade3;

create table tbPrateleira(PRIMARY KEY(numero), numero INT, localizacao varchar(15));

create table tbProduto(PRIMARY KEY(numero), COD_Barras INT, numero INT, descricao varchar(35), numero_prateleira INT, 
	FOREIGN KEY(numero_prateleira) references tbPrateleira(numero));
    
create table tbCliente(PRIMARY KEY(COD), COD INT, nome varchar(15), endereco varchar(30), telefone INT);

create table tbVendedor(PRIMARY KEY(COD), COD INT, nome varchar(15), senha varchar(15), telefone INT);

create table tbVenda(PRIMARY KEY(NotaFiscal), NotaFiscal INT, data_venda INT, COD_vendedor INT, FOREIGN KEY(COD_vendedor) 
	references tbVendedor(COD), COD_cliente INT, FOREIGN KEY(COD_cliente) references tbVendedor(COD));
    
create table tbVendaProduto(NotaFiscal_venda INT, FOREIGN KEY(NotaFiscal_venda) references tbVenda(NotaFiscal),
	numero_produto INT, FOREIGN KEY(numero_produto) references tbProduto(numero));
	
package Banco;


import Exceptions.ExcecaoSaldoInsuficiente;
import Exceptions.ExcecaoSenhaInvalida;

public class ContaCorrente {

    private int numero;
    private String senha;
    private double saldo;



    
    

    public ContaCorrente(int numero, String senha, double saldo) {
		this.numero = numero;
		this.senha = senha;
		this.saldo = saldo;
	}



	public int getNumero() {
		return numero;
	}



	public String getSenha() {
		return senha;
	}



	public double getSaldo() {
		return saldo;
	}



	public void retirar(String senha, double valor)
            throws ExcecaoSenhaInvalida, ExcecaoSaldoInsuficiente
    {
		if (senha != this.senha)
            throw new ExcecaoSenhaInvalida();
        if (valor > saldo)
            throw new ExcecaoSaldoInsuficiente();
        saldo = saldo - valor;
        System.out.printf("O saldo da conta %d é igual: %.2f %n", this.numero, this.saldo);

    }
    

    }


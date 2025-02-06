package Banco;

import Exceptions.ExcecaoContaInvalida;
import Exceptions.ExcecaoHorarioInvalido;
import Exceptions.ExcecaoSaldoInsuficiente;
import Exceptions.ExcecaoSenhaInvalida;

import java.time.LocalTime;
import java.util.ArrayList;

public class Banco  {

    private String nome;
    ContaCorrente conta = null;
    
    ArrayList<ContaCorrente> contas = new  ArrayList<ContaCorrente>();

    public void Banco(String nome){
        this.nome = nome;
    }

    public void criar_conta(int numero, String senha, double saldo){
       conta = new ContaCorrente(numero,senha,saldo);
       contas.add(conta);

    }
    
    public boolean contaExiste(int numero)
    {
    	for(ContaCorrente conta : contas) {
    		if (conta.getNumero()== numero)
    			return true;
    	}
    	return false;
    }
    

    

    public void sacar(int numero, String senha, double valor) throws ExcecaoContaInvalida, ExcecaoSenhaInvalida, ExcecaoHorarioInvalido, ExcecaoSaldoInsuficiente
    {
    	ContaCorrente c = null;
    	
    	for(ContaCorrente i : contas) {
    		if (i.getNumero() == numero) 
    			c = i;
    			
    	}
    	 LocalTime HorarioLocal = LocalTime.now();
         LocalTime OitoHoras = LocalTime.of(8,0,0,0);
         LocalTime VinteduasHoras = LocalTime.of(22,0,0,0);
         boolean oito = HorarioLocal.isBefore(OitoHoras);
         boolean vinteDuas = HorarioLocal.isAfter(VinteduasHoras);


    	if (!contaExiste(numero))
            throw new ExcecaoContaInvalida();
    	//else if (!senha.equals(c.getSenha()))
            //throw new ExcecaoSenhaInvalida();
    	else if (oito == true || vinteDuas == true )
            throw new ExcecaoHorarioInvalido();
    	//else  if (valor > c.getSaldo())
            //throw new ExcecaoSaldoInsuficiente();
    	else
    	{
    	c.retirar(senha, valor);	
    	}
       
        


    		}
    }
    


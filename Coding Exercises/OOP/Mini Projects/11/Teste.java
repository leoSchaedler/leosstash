import Banco.Banco;
import Exceptions.ExcecaoContaInvalida;
import Exceptions.ExcecaoHorarioInvalido;
import Exceptions.ExcecaoSaldoInsuficiente;
import Exceptions.ExcecaoSenhaInvalida;

public class Teste {

    public static void main(String[] args) {
        Banco banco = new Banco();

        System.out.println(java.time.LocalTime.now());


        banco.criar_conta(0,"1234",1000);
        banco.criar_conta(1,"2345",2000);
        banco.criar_conta(2, "3456", 3000);
        banco.criar_conta(3, "4567", 4000);
        banco.criar_conta(4, "5678", 5000);


        try {
			banco.sacar(0,"1234",800);
		} catch (ExcecaoContaInvalida | ExcecaoSenhaInvalida | ExcecaoHorarioInvalido | ExcecaoSaldoInsuficiente e) {
			e.printStackTrace();
		}
        
        System.out.println();
        
      try {
		banco.sacar(5,"2345",100);
	} catch (ExcecaoContaInvalida | ExcecaoSenhaInvalida | ExcecaoHorarioInvalido | ExcecaoSaldoInsuficiente e) {
		e.printStackTrace();
	}
      
      System.out.println();
      
      try {
  		banco.sacar(1,"2345",500);
  	} catch (ExcecaoContaInvalida | ExcecaoSenhaInvalida | ExcecaoHorarioInvalido | ExcecaoSaldoInsuficiente e) {
  		e.printStackTrace();
  	}
      
      System.out.println();
 
      
      try {
    		banco.sacar(3,"5345",500);
    	} catch (ExcecaoContaInvalida | ExcecaoSenhaInvalida | ExcecaoHorarioInvalido | ExcecaoSaldoInsuficiente e) {
    		e.printStackTrace();
    	}
      
      System.out.println();

      try {
    		banco.sacar(3,"4567",6000);
    	} catch (ExcecaoContaInvalida | ExcecaoSenhaInvalida | ExcecaoHorarioInvalido | ExcecaoSaldoInsuficiente e) {
    		e.printStackTrace();
    	}
      
      System.out.println();
      
      
      try {
    		banco.sacar(3,"4567",3000);
    	} catch (ExcecaoContaInvalida | ExcecaoSenhaInvalida | ExcecaoHorarioInvalido | ExcecaoSaldoInsuficiente e) {
    		e.printStackTrace();
    	}

      try {
			banco.sacar(1,"2345",500);
		} catch (ExcecaoContaInvalida | ExcecaoSenhaInvalida | ExcecaoHorarioInvalido | ExcecaoSaldoInsuficiente e) {
			e.printStackTrace();
		}

		try {
			banco.sacar(1,"2345",500);
		} catch (ExcecaoContaInvalida | ExcecaoSenhaInvalida | ExcecaoHorarioInvalido | ExcecaoSaldoInsuficiente e) {
			e.printStackTrace();
		}

		try {
			banco.sacar(1,"2345",500);
		} catch (ExcecaoContaInvalida | ExcecaoSenhaInvalida | ExcecaoHorarioInvalido | ExcecaoSaldoInsuficiente e) {
			e.printStackTrace();
		}

		try {
			banco.sacar(1,"2345",500);
		} catch (ExcecaoContaInvalida | ExcecaoSenhaInvalida | ExcecaoHorarioInvalido | ExcecaoSaldoInsuficiente e) {
			e.printStackTrace();
		}





	}
    
    
}

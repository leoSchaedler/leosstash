
public class Teste {

	public static void main(String[] args) {
		
		Batman batman = new Batman();
		JamesBond jamesBond = new JamesBond();
		Coringa coringa = new Coringa();
		Pinguim pinguim = new Pinguim();
		Goldfinger goldfinger = new Goldfinger();
		DrNo drNo = new DrNo();
		
		
		System.out.println("###Batman###");
		batman.atirar();
		batman.camuflar(1);
		batman.correr(2, 2);
		batman.saltar(3);
		batman.morrer();
		System.out.println();
		
		System.out.println("###James Bond###");
		jamesBond.atirar();
		jamesBond.saltar(3);
		jamesBond.correr(3, 3);
		jamesBond.morrer();
		System.out.println();
		
		System.out.println("###Coringa###");
		coringa.atirar();
		coringa.saltar(3);
		coringa.correr(5, 1);
		coringa.morrer();
		System.out.println();
		
		System.out.println("###Pinguim###");
		pinguim.atirar();
		pinguim.correr(4, 1);
		pinguim.saltar(3);
		pinguim.morrer();
		System.out.println();
		
		System.out.println("###Goldfinger###");
		goldfinger.saltar(2);
		goldfinger.camuflar(2);
		goldfinger.personificar(jamesBond);
		goldfinger.correr(4,4);
		goldfinger.atirar();
		goldfinger.morrer();
		System.out.println();
		
		System.out.println("###DrNo###");
		drNo.saltar(3);
		drNo.atirar();
		drNo.correr(5,2);
		drNo.morrer();
		
		
		
		

	}

}

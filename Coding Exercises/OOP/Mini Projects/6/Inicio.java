
public class Inicio {

	public static void main(String[] args) {
		Usuario joao = new Usuario("joao");
		Usuario pedro = new Usuario("pedro");
		Usuario maria = new Usuario("maria");
		
		joao.iniciarConversa( pedro);
		joao.iniciarConversa( maria);
		joao.enviarMenssagem( pedro, "um, um, i, um");
		
		System.out.println(joao.toString());
		
		
		//System.out.println( joao.imprimirMenssagens());
		//System.out.println(joao.conversas);  
		
		

	}

}

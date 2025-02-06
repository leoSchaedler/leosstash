
public class Som extends Arquivo {

	public Som(String nome) {
		super(nome);
	}
	
	@Override
	public void abrir() {
		System.out.printf("Abrindo %s com Media Player......%n", this.nome);
	}
	
	
	
	

}


public class Planilha extends Arquivo {

	public Planilha(String nome) {
		super(nome);
	}
	
	@Override
	public void abrir() {
		System.out.printf("Abrindo %s com Excel...%n", this.nome);
	}
	
	

}

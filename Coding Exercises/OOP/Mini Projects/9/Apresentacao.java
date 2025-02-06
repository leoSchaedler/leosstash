public class Apresentacao extends Arquivo {

	public Apresentacao(String nome) {
		super(nome);
	}
	
	@Override
	public void abrir() {
		System.out.printf("Abrindo %s com Power Point...%n", this.nome);
	}
	
	

}

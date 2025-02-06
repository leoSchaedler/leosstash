
public class Imagem extends Arquivo {

	public Imagem(String nome) {
		super(nome);
	}

	@Override
	public void abrir() {
		System.out.printf("Abrindo %s com Fotos...%n", this.nome);
	}
	
	
	
	

}

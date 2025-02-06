
public class Video extends Arquivo {

	public Video(String nome) {
		super(nome);
	}
	
	@Override
	public void abrir() {
		System.out.printf("Abrindo %s com Videos...%n", this.nome);
	}
	

}

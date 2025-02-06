
public class Texto extends Arquivo {
	
	public Texto(String nome) {
		super(nome);
	}
	
	@Override
	public void abrir()
	{
		System.out.printf("Abrindo %s com Word...%n",this.nome);
	}
	
	

}

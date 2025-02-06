import java.util.ArrayList;

public class Disco {
	
	protected char nome;
	
	ArrayList<Pasta> pastas = new ArrayList<Pasta>();

	public Disco(char nome) {
		this.nome = nome;
	}
	
	public Disco() {
		
	}
	
	public Pasta criarPasta(String nome) 
	{
		Pasta pasta = new Pasta (nome);
		pastas.add(pasta);
		return pasta;
	}
	
	public void removerPasta(Pasta x) {
		
		pastas.remove(x);
	}
	
	public void listarPastas() 
	{
		pastas.forEach(pasta -> {
			System.out.println(pasta.nome);
		});
	}
	
	
	
	

}

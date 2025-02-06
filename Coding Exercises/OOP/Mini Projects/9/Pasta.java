import java.util.ArrayList;

public class Pasta extends Disco {
	
	protected String nome;
	
	ArrayList<Arquivo> arquivos = new ArrayList<Arquivo>();

	public Pasta(String nome) {
		super();
		this.nome = nome;
	}
	
	public Pasta() {
		
	}
	
	
	public void inserirArquivo(Arquivo x) 
	{
		arquivos.add(x);
	}
	
	public void removerArquivo(Arquivo x)
	{
		arquivos.remove(x);
	}
	
	public void listarArquivos()
	{
		arquivos.forEach(arq -> {
			System.out.println(arq.nome);
		});
	}
	
	public void duplicarArquivo(Arquivo x) throws CloneNotSupportedException
	{
			arquivos.add(x.copiarArquivo());
			
	}
	
	public void imprimirNomeDaPasta()
	{
		System.out.println(this.nome);
	}
	
	
	
	
	

}

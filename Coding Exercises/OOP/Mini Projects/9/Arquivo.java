import java.security.SecureRandom;
import java.util.Random;

public class Arquivo extends Pasta {
	
	protected String nome;
	private byte[] dados;
	 SecureRandom random = new SecureRandom();
	 
	 
	public Arquivo(String nome) {
		super();
		this.nome = nome;
		int t = 10 + random.nextInt(10);
		dados = new byte[t];
		for(int i = 0;i<dados.length;i++) {
			dados[i] = 0;
		}
		
	}
	
	public byte[] getDados()
	{
		return dados;
	}
	
	public Arquivo() {
		
	}
	
	public void abrir()
	{
		
	}
	
	public Arquivo copiarArquivo() throws CloneNotSupportedException
	{
		return (Arquivo) this.clone();
	}
	
	public void imprimirNomeDoArquivo()
	{
		System.out.println(this.nome);
	}
	
	
	 
	 
	 
	 

}

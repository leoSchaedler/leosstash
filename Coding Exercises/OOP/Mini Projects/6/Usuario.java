import java.util.*;

public class Usuario {
	
	private String nome;
	
	//Objetos Conversa
	Conversa a;
	Conversa b;

	List<String> conversas = new ArrayList<String>(); //Precissa ser um array de Objetos??
	
	public void iniciarConversa(Usuario contato) { //Cria 2 objetos Conversa,1 para cada usuario
		a = new Conversa(contato.getNome());
		b = new Conversa(this.nome);
		conversas.add(a.getNome());
		
	}
	
	public Usuario(String nome) { //Construtor
		this.nome = nome;
	}

	public String getNome() {
		return this.nome;
	}
	
	public boolean enviarMenssagem(Usuario Destinatario,String texto) { //Evia a menssagem para o metodo receber da Conversa
		a.receberMenssagem(b, texto);
		return true;
		
		
	}
	

	public void receberMenssagem(Usuario remetente,String Menssagem) {
		//Não sei pra q serve isso
		
	}

	@Override
	public String toString() { //Metodo toString chamando os outros toString
		return String.format("De: %s%n%s", nome,a.toString());
	}

	public String imprimirMenssagens() {
		return String.format("Usuario: %s%nConversas: %s",this.nome,conversas);
}
}
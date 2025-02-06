import java.util.ArrayList;
import java.util.List;

public class Conversa {
	
	private String nomeContato;
	Mensagem m;
	List<Mensagem> mensagens = new ArrayList<Mensagem>();

	public Conversa(String nomeContato) { //construtor
		this.nomeContato = nomeContato;
	}

	@Override
	public String toString() {
		return String.format("Nome do Contato: %s%nMensagens: %s%n", nomeContato,mensagens);
	}

	public String getNomeContado() { //Retorna o nome do contato
		return nomeContato;
	}

	public void enviar(String contato,String texto) { //Metodo chamado pelo enviar do Usuario
		m = new Mensagem(texto);
		this.nomeContato = contato;
		m.setEnviado_recebido(true);
		mensagens.add(m);
	}
	
	public void receber(String contato,String texto) { //Metodo chamado pelo receber do Usuario
		
		m = new Mensagem(texto);
		this.nomeContato = contato;
		m.setEnviado_recebido(false);
		mensagens.add(m);
	}


	public String imprimir() { // Retorna o nome do contato e suas mensagens
		return String.format("Nome do contato: %s%nLista de mensagens: %s%n", 
								nomeContato, mensagens);
	}


}

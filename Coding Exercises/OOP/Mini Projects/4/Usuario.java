import java.util.ArrayList;
import java.util.List;

public class Usuario {
	
	private String nome;
	List<Conversa> conversas = new ArrayList<Conversa>();
	Conversa a;
	Conversa b;
	Conversa c;
	
	
	
	
	public Usuario(String nome) { //Construtor
		this.nome = nome;
	} 
	public String getNome() { //Retorna o nome do Usuario
		return this.nome;
	}
	
	public void iniciarConversa(Usuario contato) { //cria 2 instancias da classe conversa
		a = new Conversa(contato.getNome());
		b = new Conversa(this.nome); //Instancia não utilizada
		conversas.add(a);
		
	}
	
	
	public void enviarMensagem(Usuario contato, String texto) { //Chama o metodo enviar da classe conversa
		try {
		a.enviar(contato.getNome(), texto);
		contato.receberMensagem(this, texto);
		
		}catch(NullPointerException npex) {
			System.out.println(npex.toString());
			}
	}
	
	private final void receberMensagem(Usuario remetente, String texto) { //chama o metodo receber da classe conversa
		try {															  
																			
		c = new Conversa(remetente.getNome());
		c.receber(remetente.getNome(), texto);
		conversas.add(c);
		
		}catch(NullPointerException npex) {
			System.out.println(npex.toString());
		}
		
	}
	

	public void imprimirMensagens(Usuario contato) { //mostra todas as mensagens de todas as conversas do usuario
		
		for (Conversa i : conversas) {
			if(i.getNomeContado() == contato.getNome()) 
			
		System.out.printf("Lista de mensagens de %s com %s:%n%nMensagens: %s%n",
							this.nome,i.getNomeContado() , i.mensagens);
			else {System.out.printf("Não ha conversas com %s%n" , contato.getNome());}
			
		}
		}
		
		
	@Override
	public String toString() {
		return String.format("Usuario: %s%n", nome);
	}
	
	
	
	
	
	
		}
		
	
	
	
	




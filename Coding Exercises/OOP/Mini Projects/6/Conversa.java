import java.util.ArrayList;
import java.util.List;

public class Conversa {
	
	private String nome;
	
	//Objetos Menssagem
	
	Menssagem a = new Menssagem();
	Menssagem b = new Menssagem();
	
	List<String> menssagens = new ArrayList<String>(); //Precissa ser um array de Objetos??

	public Conversa(String nome) {//Construtor
		this.nome = nome;
	}
	
public void receberMenssagem(Conversa remetente,String Menssagem) {
		a.setTexto(Menssagem);
		a.setEnviado(false);
		menssagens.add(a.getTexto());
		
		
	}


public String getNome() {
	return this.nome;
}

@Override
public String toString() {
	return String.format("Para: %s%n%s", nome,a.toString());
}
	

}
	



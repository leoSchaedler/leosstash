import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Jogador  implements Serializable {
	
	
 private String nome;
 private int pontuacao;
 
 public Jogador(String nome) {
 this.nome = nome;
 }
 
 
 public void setPontuacao(int pontuacao)
 {
	 this.pontuacao = pontuacao;
 }
 
 public int getPontuacao()
 {
	 return this.pontuacao;
 }
 
 public String getNome()
 {
	 return this.nome;
 }

 
 
 public void exibir() {
 System.out.println(nome + ": " + pontuacao);
 }
 
 public void salvar(String nome_arquivo) throws IOException {
	 FileOutputStream arquivo = new FileOutputStream(nome_arquivo);
	 ObjectOutputStream gravador = new ObjectOutputStream(arquivo);
	 gravador.writeObject(this);
	 gravador.flush();
	 gravador.close();
	 arquivo.flush();
	 arquivo.close();
	}
 
 public static Jogador abrir(String nome_arquivo) throws IOException, ClassNotFoundException {
	 Jogador jogador = null;
	 FileInputStream arquivo = new FileInputStream(nome_arquivo);
	 ObjectInputStream restaurador = new ObjectInputStream(arquivo);
	 jogador = (Jogador) restaurador.readObject();
	 restaurador.close();
	 arquivo.close();
	 return jogador;
	}


@Override
public String toString() {
	return "pontuacao=" + pontuacao;
}
 
 
 
 
}
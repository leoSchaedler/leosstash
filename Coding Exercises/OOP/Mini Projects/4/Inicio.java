import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Inicio {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in); //Classe scanner para o input
		
		List<Usuario> usuarios = new ArrayList<Usuario>();// Lista de Usuarios
		
		//Criação de usuarios
		System.out.println("Usuario: ");
		String user = input.nextLine();
		Usuario a = new Usuario(user);
		usuarios.add(a);
		System.out.println("Usuario Contato: ");
		String contato = input.nextLine();
		Usuario b = new Usuario(contato);
		usuarios.add(b);
		
		//Mostra os usuarios criados
		System.out.println("Usuarios Criados: ");
		System.out.println();
		for (Usuario i : usuarios) {
			System.out.println(i.getNome());
		}
		System.out.println("----------------------------------");
		
		
		//Inicia uma conversa e mostra a lista de conversas de um usuario
		a.iniciarConversa(b);
		System.out.printf("Lista de usuarios com quem %s mantem conversa:%n", a.getNome());
		System.out.println();
		for (Conversa i : a.conversas) {
			System.out.println(i.getNomeContado());
		}
		System.out.println("----------------------------------");
		
		
		//Envia uma mensagem e mostra a lista de mensagens do usuario
		System.out.println("Mensagem: ");
		String mensagem = input.nextLine();
		System.out.println("----------------------------------");
		a.enviarMensagem(b, mensagem);
		System.out.println();
		a.imprimirMensagens(b);
		


	}

}

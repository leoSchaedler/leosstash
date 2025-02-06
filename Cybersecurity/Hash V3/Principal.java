import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class Principal {
	
	static ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
	
	public static void main(String[] args) {
		
		
		Scanner input = new Scanner(System.in);
		
		int opcao = 0;
		Usuario usuario;
		
		do {
		System.out.println("Digite:\n(1) para cadastrar um usuario.\n(2) para autenticar.\n(3) para fechar o programa.");
		opcao = input.nextInt();
		}while(opcao != 1 && opcao != 2 && opcao != 3);
		
		switch(opcao) 
		{
		case 1:
			System.out.println("Nome do usuario: ");
			String nome1 = input.next();
			
			System.out.println("Digite a senha(4 caracteres):");
			String senha = input.next();
			if(senha.length() < 10) {
				System.out.println("Senha precisa ter no minimo 10 caracteres");
				main(args);
				break;
				}
			try {
				String Ksenha = Utili.gerarHash(Utili.salt(senha));
				Utili.abrirArquivo(nome1);
				Utili.addUser(Ksenha);
				Utili.closeFile();
				usuario = new Usuario(nome1,Ksenha);
				usuarios.add(usuario);
				
				main(args);
			}catch(NoSuchAlgorithmException e ) {
				
			}
			break;
		
			
		case 2:
			System.out.println("Usuario: ");
			String u = input.next();
			System.out.println("Senha: ");
			String s = input.next();
			try {
			boolean a = Utili.autenticar(usuarios, u, s);
			if(a == true) {
					System.out.println("Usuario autenticado");
					System.out.println();
					main(args);
					break;
			}else {System.out.println("Usuario ou senha incorretos");}
			}catch(NoSuchAlgorithmException e ) {
				
			}
			main(args);
			break;
			
			
			
		case 3:
			System.out.println("Fim!!");
			
			
			
			
		}
			
		
	
			
			
			
	
		
		
		
		
		
	}
	
	
	
	

}

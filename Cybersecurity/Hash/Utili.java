import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Utili {
	
	private static Formatter output;
	public static int index;

	public static void abrirArquivo(String usuario) {
		try {
			 	
//output = new Formatter( "C:\\Users\\Leo\\eclipse-workspace\\hash 2.0\\Users\\"+  usuario + ".txt");//Se comentar a linha de baixo e descomentar essa vc pode escolher o caminho onde vai salvar os arquivos
			output = new Formatter(usuario + ".txt");
		}catch(SecurityException securityException) {
			System.err.println("Write permission denied. Terminating.");
			System.exit(1);
		} catch (FileNotFoundException fileNotFoundException) {
			System.err.println("Error opening file. Terminating.");
			System.exit(1);
		}
	}
	
	
	public static void addUser(String x) {
			try {
				output.format("%s", x);
			}catch (FormatterClosedException formatterClosedException) {
				System.err.println("Error writing to file. Terminating.");

			}catch (NoSuchElementException elementException) {
				System.err.println("Invalid input. Please try again.");
			}
		}
		
		
	
	public static void closeFile() {
		if (output != null)
			output.close();
	}
	
	
	public static String gerarHash(String senha) throws NoSuchAlgorithmException {
	    MessageDigest m = MessageDigest.getInstance("MD5");
	    m.update(senha.getBytes(), 0, senha.length());
	    BigInteger senha1 = new BigInteger(1, m.digest());
	    return String.format("%1$032X", senha1);

	}
	
	
	public static void acharUsuario(List<Usuario> a, String nome,String senha) throws NoSuchAlgorithmException 
	{	
		String Ksenha = Utili.gerarHash(senha);
		for(Usuario i : a) {
			if (i.getNome().equals(nome) == true && i.getSenha().equals(Ksenha) == true) {
				System.out.println("Sucesso");
				
		}else {System.out.println("erro");}
	}

		
		
	
	
	}
	

	
	public static boolean autenticar (ArrayList<Usuario> a , String nome, String senha) throws NoSuchAlgorithmException {
		String Ksenha = Utili.gerarHash(senha);
		for(Usuario i:a) {
			if(i.getNome().equals(nome) && i.getSenha().equals(Ksenha))
				return true;
	}
		return false;
	
	
	
	
}
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	



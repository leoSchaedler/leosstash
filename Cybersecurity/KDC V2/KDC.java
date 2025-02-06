import java.security.SecureRandom;

public class KDC {
	
	
	
	static String[][] chaves = {{"bob","bolabolabolabola"},{"alice","bolabolabolabola"}}; //Lista de Usuarios e suas chaves
	
	public static void gerarChave(String nome, byte[] Knome, byte[] Kcontato, Usuario[] u) {  //Metodo principal para gerar a chave de Sessao
		for(int i=0;i<chaves.length;i++) {
			if (chaves[i][0].equals(nome)) {
				try {
					String knome = AES.decifra(Knome, chaves[i][1]);
					boolean ChaveCerta = comparar(nome,knome);
					if (ChaveCerta == true) {
					String ChaveDeSessao = gerarString();
					u[0].receberChave(ChaveDeSessao);
					enviarChavecontato(u[1],ChaveDeSessao);
					System.out.println("Chave gerada");
					//System.out.printf("Chave de sessao: %s%n" ,ChaveDeSessao );
					u[0].setChaveDeSessaoGerada(true);
					u[1].setChaveDeSessaoGerada(true);
					}
					
					
				}catch(Exception e)
				{
					System.out.println(e.getMessage());
				}
				
				
				
			}
		}
		
	}
	
	private static boolean comparar(String x,String y) {  //Compara duas Strings
		if (x.equals(y)) {
			return true;
		}
		return false;
	}

	
	private static String gerarString(){        //Gera uma String aleatoria de 16bytes que é usada como chave de Sessão
		SecureRandom gerar = new SecureRandom();
        final int tamanho = 16;
        String letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
        String chave = "";
        char[] texto = new char[tamanho];
        for(int i=0;i<tamanho;i++){
            texto[i] = letras.charAt(gerar.nextInt(letras.length()));
        }
        for(int i =0;i<texto.length;i++){
            chave+=texto[i];
        }
        return chave;
    }

private static void enviarChavecontato(Usuario x,String chave) {  //Envia a chave de sessao cifrada na chave do usuario contato
	try {
		
	byte[] textoCifrado = AES.cifra( chave, x.getChave());
	x.receberChaveContato(textoCifrado);

	}catch(Exception e)
	{
		System.out.println(e.getMessage());
	}
	
}




}

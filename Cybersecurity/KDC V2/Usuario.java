import java.security.SecureRandom;

public class Usuario {
	
	private String nome;
	private String chave;
	private String chaveDeSessao;
	
	private boolean chaveDeSessaoGerada = false;
	private boolean NonceAutenticado = false;
	
	
	
	
	
	public boolean isNonceAutenticado() {
		return NonceAutenticado;
	}



	public void setNonceAutenticado(boolean nonceAutenticado) {
		NonceAutenticado = nonceAutenticado;
	}



	public boolean isChaveDeSessaoGerada() {
		return chaveDeSessaoGerada;
	}



	public void setChaveDeSessaoGerada(boolean chaveDeSessaoGerada) {
		this.chaveDeSessaoGerada = chaveDeSessaoGerada;
	}



	public Usuario(String nome, String chave) {
		this.nome = nome;
		this.chave = chave;
	}



	public String getNome() {
		return nome;
	}



	public String getChave() {
		return chave;
	}



	public void pedirChave(Usuario contato) {   //Pede uma chave de sessao ao KDC
		try {
		Usuario[] u = {this,contato};
		byte[] Knome = AES.cifra(this.nome, this.chave);
		byte[] Kcontato = AES.cifra(contato.getNome(), this.chave);
		KDC.gerarChave(this.nome, Knome, Kcontato,u);
		
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
	}
	
	public void receberChave(String chaveDeSessao) {
		this.chaveDeSessao = chaveDeSessao;
	}
	
	
	
	
	public void receberChaveContato(byte[] chaveDeSessao) {   //Recebe a chave cifrada e decifra
		try {
		String Cs = AES.decifra(chaveDeSessao, this.chave);
		this.chaveDeSessao = Cs;
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	private static  int gerarNonce() {
		SecureRandom r = new SecureRandom();
		return r.nextInt(500000);
		
	}
	private static int calcularNonce(int Nonce) {
		return Nonce++;
	}
	
	public void compararNonce(Usuario x) {
		if (x.isChaveDeSessaoGerada() == true && this.isChaveDeSessaoGerada() == true) {
		int Nonce = gerarNonce();
		int KNonce = calcularNonce(Nonce);
		System.out.printf("O Nonce gerado por %s é : %d%n", this.getNome(),KNonce);
		int NonceRetornado = x.calcularNonce(Nonce);
		System.out.printf("O Nonce retornado por %s é : %d%n", x.getNome() ,NonceRetornado);
		if(KNonce == NonceRetornado) {
			System.out.println("Nonce autenticado com sucesso");
			this.setNonceAutenticado(true);
			x.setNonceAutenticado(true);
		}
		}
		else {
			System.out.println("Autenticação do Nonce falhou");
			x.setNonceAutenticado(false);
		}
	}
	
	public void enviarMensagem(Usuario contato ,String mensagem) {
		if (contato.isNonceAutenticado() == true && this.isNonceAutenticado() == true) {
			try {
			String chave = this.chaveDeSessao; //tamanho: 16		
			byte[] textoCifrado = AES.cifra(mensagem, chave);
			System.out.printf("Mensagem de: %s%nPara: %s%nTexto Cifrado:%n", this.nome,contato.getNome());
			AES.Imprimir(textoCifrado);
			System.out.println("Texto as claras: ");
			String textoDecifrado = AES.decifra(textoCifrado, chave);
			AES.Imprimir(textoDecifrado);
			
			}catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
		}
		else {
			System.out.println("Não há uma chave de sessao para conversar com este usuario");
		}
		}

	
	
	public static void main(String[] args) {
		Usuario bob = new Usuario("bob","bolabolabolabola");
		Usuario alice = new Usuario("alice","bolabolabolabola");
		bob.pedirChave(alice);
		alice.compararNonce(bob);
		System.out.println();
		System.out.println();
		bob.enviarMensagem(alice, "Bom Dia");
		
		
	}
	

}

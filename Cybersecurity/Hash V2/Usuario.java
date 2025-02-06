
public class Usuario {
	
	private String nome;
	private String senha;
	
	//Construtor
	public Usuario(String nome, String senha) 
	{
		if(nome.length() != 4 )
			throw new IllegalArgumentException("Nome e senha precissam ter 4 caracteres");
		this.nome = nome;
		this.senha = senha;
		
	}
	
	
	
	
	public String getNome() {
		return nome;
	}




	public void setNome(String nome) {
		this.nome = nome;
	}




	public String getSenha() {
		return senha;
	}




	public void setSenha(String senha) {
		this.senha = senha;
	}





	

}

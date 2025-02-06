
public class Mensagem {
	
	private String texto;
	private boolean enviado_recebido;
	public boolean isEnviado_recebido() {
		return enviado_recebido;
	}

	public void setEnviado_recebido(boolean enviado_recebido) {
		this.enviado_recebido = enviado_recebido;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	@Override
	public String toString() {
		if(enviado_recebido == true)
			return String.format("Texto: %s%nStatus: Enviado%n%n", texto);
		else {
		return String.format("Texto: %s%nStatus: Recebido%n%n", texto);
		}
	}

	public Mensagem(String texto) { //Construtor

		this.texto = texto;
	}

}
//Possui apenas getters and setters e um construtor
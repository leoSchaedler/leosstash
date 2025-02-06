
public class Menssagem {
	
	private String texto;
	private boolean enviado;
	
	
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public boolean isEnviado() {
		return enviado;
	}
	public void setEnviado(boolean enviado) {
		this.enviado = enviado;
	}
	
	@Override
	public String toString() {
		return String.format("Menssagem: %s%nEnviado/Recebido: %s",texto,enviado);
	}
	

}

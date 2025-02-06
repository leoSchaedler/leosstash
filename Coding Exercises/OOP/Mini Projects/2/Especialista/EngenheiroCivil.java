package Especialista;
import Especialista.Arquiteto;
import Matematica.Retangulo;
public class EngenheiroCivil {
	public static void main(String[] args) {
		Arquiteto.exibir_dados_pessoais();
		Retangulo.definir_lados(4.7f,8.2f);
		System.out.println(Retangulo.area());
		System.out.println(Retangulo.perimetro());
	}
}


public class Pinguim extends Ladrao {

	@Override
	public void correr(float x,float y)
	{
		posicao_x = x;
		posicao_y = y;
		System.out.println("Pinguim Correndo");
	}

	@Override
	public void atirar() {
		System.out.println("Pinguim Atirando");
	}

	
}

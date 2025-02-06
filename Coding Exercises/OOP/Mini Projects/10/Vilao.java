
public abstract class Vilao extends Personagem{
	
	@Override
	public void correr(float x,float y)
	{
		posicao_x = x;
		posicao_y = y;
		
		System.out.println("Vilão correndo");
		
	}

}

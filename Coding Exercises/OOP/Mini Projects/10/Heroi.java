
public abstract class Heroi extends Personagem {
	
	@Override
	public void correr(float x,float y)
	{
		posicao_x = x;
		posicao_y = y;
		System.out.println("Heroi Correndo");
	}
	
	@Override
	public void saltar(float z)
	{
		posicao_z = z;
		System.out.println("Heroi Saltando");
	}
	
	
}


public abstract class Personagem {
	
	private boolean vivo;
	protected float posicao_x;
	protected float posicao_y;
	protected float posicao_z;
	protected int cor;
	
	
	
	
	public abstract void correr(float x,float y);

	
	public abstract void saltar(float z);

	
	public abstract void atirar();

	
	public void morrer()
	{
		vivo = false;
		System.out.println("Morto");
	}


	
	
	
}



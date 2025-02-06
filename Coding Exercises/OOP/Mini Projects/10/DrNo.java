
public class DrNo extends Terrorista {
	
	@Override
	public void saltar(float z)
	{
		posicao_z = z;
		System.out.println("DrNo Saltanto");
	}
	
	@Override
	public void atirar()
	{
		System.out.println("DrNo atirando");
	}

	@Override
	public void correr(float x,float y)
	{
		posicao_x = x;
		posicao_y = y;
		System.out.println("DrNo Correndo");
		
	}
	
	
}

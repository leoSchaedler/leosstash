
public class JamesBond extends Heroi{

	@Override
	public void atirar()
	{
		System.out.println("James Bond atirando");
	}
	
	@Override
	public void saltar(float z)
	{
		posicao_z = z;
		System.out.println("James Bond saltando");
	}
	
	
}

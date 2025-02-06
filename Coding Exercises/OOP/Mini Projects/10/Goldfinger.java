
public class Goldfinger extends Terrorista implements Personificacao {
	
	private Heroi heroi = null;

	@Override
	public void camuflar(int cor) {
		System.out.println("Goldfinger camuflando");

	}

	@Override
	public void personificar(Heroi H) {
		heroi = H;
		System.out.println("Personificando");

	}

	@Override
	public void saltar(float z)
	{
		posicao_z = z;
		System.out.println("Goldfinger Saltando");

	}

}

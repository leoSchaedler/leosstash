import java.util.concurrent.*;

public class Teste07 {

	public static void main(String[] args) {

		final int N = 6; // numero de filosofos e de garfos
		Semaphore limitador = new Semaphore(N-1);
		
		Semaphore[] garfo = new Semaphore[N]; // vetor para N semaforos
		for (int i=0; i<N; i++)
			garfo[i] = new Semaphore(1);
		
		for (int i=0; i<N; i++)
		{
			int dir = i;
			int esq = (i+1) % N;
			Filosofo f = new Filosofo(i, garfo[dir], garfo[esq], limitador);
			f.start();
		}
	}

}

package mutex;

import java.util.concurrent.Semaphore;

public class GeradorImpares extends Thread{

    Semaphore mutex;
    int n;

    public GeradorImpares(Semaphore mutex) {
        this.mutex = mutex;
    }

    @Override
    public void run() {
        while (true)
            try {
                mutex.acquire();
                Thread.sleep(1000 + (int) (Math.random() * 2000));
                n = (int) (Math.random() * 9);
                n += (n % 2 == 0 ? 1 : 0);
                System.out.println("Numero impar gerado: " + n);
                Main.CHAVE[Main.POS] = n;
                Main.POS++;
                Main.contadorIMPAR++;
                mutex.release();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }catch (ArrayIndexOutOfBoundsException f) {
                mutex.release();
                break;
            }


    }
}

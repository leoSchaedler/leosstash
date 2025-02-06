package mutex;

import java.util.concurrent.Semaphore;

public class GeradorPares extends Thread {

    Semaphore mutex;
    int n;

    public GeradorPares(Semaphore mutex) {
        this.mutex = mutex;
    }

    @Override
    public void run() {
        while (true)
            try {
                mutex.acquire();
                Thread.sleep(1000 + (int) (Math.random() * 2000));
                n = (int) (Math.random() * 8);
                n += (n % 2 != 0 ? 1 : 0);
                System.out.println("Numero par gerado: " + n);
                Main.CHAVE[Main.POS] = n;
                Main.POS++;
                Main.contadorPAR++;
                mutex.release();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }catch (ArrayIndexOutOfBoundsException f) {
                mutex.release();
                break;
            }


    }
}

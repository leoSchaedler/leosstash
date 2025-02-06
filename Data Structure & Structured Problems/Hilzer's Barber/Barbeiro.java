package barbeiroHilzer;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Barbeiro extends Thread
{

    private final int numeroBarbeiro;
    private final int timeOutMinimo;
    private final int timeOutMaximo;

    private Semaphore mutex;

    public Barbeiro(int numeroBarbeiro, int timeOutMinimo, int timeOutMaximo, Semaphore mutex)
    {
        this.numeroBarbeiro = numeroBarbeiro;
        this.timeOutMinimo = timeOutMinimo;
        this.timeOutMaximo = timeOutMaximo;
        this.mutex = mutex;
    }

    public void cortarCabelo() throws InterruptedException {
        System.out.println("Cortando o cabelo do cliente...");
        Thread.sleep(new Random().nextInt(timeOutMaximo - timeOutMinimo) + timeOutMaximo);
    }

    @Override
    public void run()
    {
        while (true)
        {
            try {
                System.out.println("Barbeiro esperando...");
                Barbearia.clienteFilaSofa.acquire();
                mutex.acquire();
                Semaphore auxiliar = Barbearia.filaSofa.remover();
                mutex.release();
                auxiliar.release();
                System.out.println("B1 Checkpoint");
                Barbearia.clienteFilaCadeiras.acquire();
                mutex.acquire();
                auxiliar = Barbearia.filaCadeiras.remover();
                mutex.release();
                auxiliar.release();
                System.out.println("B2 Checkpoint");
                Barbearia.barbeiro.release();
                cortarCabelo();

                Barbearia.pagamento.acquire();
                Barbearia.recebimento.release();
                System.out.println("B3 Checkpoint");

            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
}

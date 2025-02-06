package BarbeariaFIFO;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Barbeiro extends Thread
{

    private final int timeOutMinimo;
    private final int timeOutMaximo;

    public Barbeiro(int timeOutMinimo, int timeOutMaximo)
    {
        this.timeOutMinimo = timeOutMinimo;
        this.timeOutMaximo = timeOutMaximo;
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
                Barbearia.cliente.acquire();
                Barbearia.mutex.acquire();

                Semaphore auxiliar = Barbearia.filaDeEspera[Barbearia.primeiroDaFila];

                if (auxiliar != null)
                {
                    Barbearia.filaDeEspera[Barbearia.primeiroDaFila] = null;
                    Barbearia.primeiroDaFila = Barbearia.primeiroDaFila+1;

                    if (!(Barbearia.primeiroDaFila < Barbearia.limiteFila))
                    {
                        Barbearia.primeiroDaFila = 0;
                    }
                    Barbearia.mutex.release();
                    auxiliar.release();

                    System.out.println("Barbeiro: Vamos iniciar o corte.");
                    cortarCabelo();
                    System.out.println("Barbeiro: Acabamos!");

                    Barbearia.corteConcluido.release();
                    Barbearia.clienteSatisfeito.acquire();
                }
                else {
                    Barbearia.mutex.release();
                    System.out.println("Operação não pode ser realizada.");
                }

            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
}

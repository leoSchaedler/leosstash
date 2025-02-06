package BarbeariaFIFO;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Cliente extends Thread
{

    private final int timeOutMinimo;
    private final int timeOutMaximo;
    private final int numeroCliente;

    public Cliente(int numeroCliente, int timeOutMinimo, int timeOutMaximo)
    {
        this.numeroCliente = numeroCliente;
        this.timeOutMinimo = timeOutMinimo;
        this.timeOutMaximo = timeOutMaximo;
    }


    public void terCabeloCortado() throws InterruptedException {
        System.out.println("O cabelo do cliente(" + this.numeroCliente + ") está sendo cortado.");
        Thread.sleep(new Random().nextInt(timeOutMaximo - timeOutMinimo) + timeOutMaximo);
    }

    public void desistir()
    {
        System.out.println("Cliente (" + this.numeroCliente + ") desistiu de ser atendido.");
    }

    @Override
    public void run()
    {
            try {
                System.out.println("Cliente (" + this.numeroCliente +  "): Chegou na barbearia e deseja ser atendido.");
                Semaphore auxiliar = new Semaphore(0);
                Barbearia.mutex.acquire();

                if (Barbearia.contadorFila < Barbearia.limiteFila)
                {
                    Barbearia.contadorFila = Barbearia.contadorFila+1;
                    Barbearia.filaDeEspera[Barbearia.primeiroDaFila] = auxiliar;
                    System.out.println("Cliente (" + this.numeroCliente +  "): Procura para um lugar para sentar.");
                    Barbearia.mutex.release();
                }
                else
                {
                    desistir();
                    Barbearia.mutex.release();
                }

                Barbearia.cliente.release();
                auxiliar.acquire();

                System.out.println("Cliente (" + this.numeroCliente +  "): Meu corte de cabelo começou agora.");
                terCabeloCortado();
                System.out.println("Cliente (" + this.numeroCliente +  "): Meu corte finalizou.");

                Barbearia.clienteSatisfeito.release();
                Barbearia.corteConcluido.acquire();
                Barbearia.mutex.acquire();
                Barbearia.contadorFila = Barbearia.contadorFila-1;

                Barbearia.mutex.release();
                System.out.println("Cliente (" + this.numeroCliente +  "): Saiu da barbearia.");


            } catch (InterruptedException e) {
                System.out.println("Cliente");
                e.printStackTrace();
            }
    }
}

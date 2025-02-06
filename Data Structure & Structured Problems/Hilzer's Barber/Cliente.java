package barbeiroHilzer;

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

                Semaphore representanteFilaSofa = new Semaphore(0);
                Semaphore representanteFilaCadeiras = new Semaphore(0);
                Barbearia.mutex.acquire();
                if (Barbearia.contadorClientes == Barbearia.N)
                {
                    Barbearia.mutex.release();
                    desistir();
                }
                System.out.println("C1 Checkpoint");
                Barbearia.contadorClientes = Barbearia.contadorClientes + 1;
                Barbearia.filaSofa.adicionar(representanteFilaSofa);
                Barbearia.mutex.release();
                System.out.println("C2 Checkpoint");
                Barbearia.clienteFilaSofa.release();
                representanteFilaSofa.acquire();
                System.out.println("C3 Checkpoint");
                Barbearia.sofa.acquire();
                Barbearia.mutex.acquire();
                System.out.println("C4 Checkpoint");
                Barbearia.filaCadeiras.adicionar(representanteFilaCadeiras);
                Barbearia.mutex.release();
                Barbearia.clienteFilaCadeiras.release();
                representanteFilaCadeiras.acquire();
                Barbearia.sofa.release();
                terCabeloCortado();
                Barbearia.pagamento.release();
                Barbearia.recebimento.acquire();
                Barbearia.mutex.acquire();
                Barbearia.contadorClientes = Barbearia.contadorClientes + 1;
                Barbearia.mutex.release();
                System.out.println("C5 Checkpoint");


            } catch (InterruptedException e) {
                System.out.println("Cliente");
                e.printStackTrace();
            }
    }
}

package BarbeariaFIFO;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Barbearia {

    public static int limiteFila = 4;
    public static Semaphore[] filaDeEspera = new Semaphore[limiteFila];
    public static int contadorFila = -1;
    public static int primeiroDaFila = 0;
    public static Semaphore mutex = new Semaphore(1);
    public static Semaphore cliente = new Semaphore(0);
    public static Semaphore clienteSatisfeito = new Semaphore(0);
    public static Semaphore corteConcluido = new Semaphore(0);

    public static void main(String[] args) {
        System.out.println("Bem Vindo a Barbearia Leal, entre na fila para ser atendido.");

        int contadorCliente = 0;
        int timeOutMinimo = 1000;
        int timeOutMaximo = 2000;
        Random r = new Random();

        Barbeiro barbeiro = new Barbeiro(timeOutMinimo, timeOutMaximo);

        try {
            barbeiro.start();

            while (true)
            {
                new Cliente(contadorCliente, timeOutMinimo, timeOutMaximo).start();
                contadorCliente++;
                Thread.sleep(r.nextInt(10000));
            }

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}

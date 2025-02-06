package barbeiroHilzer;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Barbearia {

    public static int limiteFila = 4;
    public static int N = 20;
    public static int maxBarbeiros = 3;
    public static int contadorClientes = 0;

    public static MyFila<Semaphore> filaSofa= new MyFila<Semaphore>(4);
    public static MyFila<Semaphore> filaCadeiras= new MyFila<Semaphore>(3);
    public static Semaphore mutex = new Semaphore(1);
    public static Semaphore sofa = new Semaphore(4);
    public static Semaphore barbeiro = new Semaphore(0);
    public static Semaphore pagamento = new Semaphore(0);
    public static Semaphore recebimento = new Semaphore(0);
    public static Semaphore clienteFilaSofa = new Semaphore(0);
    public static Semaphore clienteFilaCadeiras = new Semaphore(0);


    public static void main(String[] args) throws InterruptedException {
        System.out.println("Bem Vindo a Barbearia Leal, sente-se no sofá caso haja espaço e espere ser atendido.");

        int contadorBarbeiro = 0;
        int contadorCliente = 0;
        int timeOutMinimo = 1000;
        int timeOutMaximo = 2000;

        for(int i =0; i < maxBarbeiros; i++)
        {
            new Barbeiro(contadorBarbeiro,timeOutMinimo, timeOutMaximo, new Semaphore(1)).start();
        }

        while (true)
        {
            new Cliente(contadorCliente,timeOutMinimo,timeOutMaximo).start();
            Thread.sleep(2000);
        }

    }
}

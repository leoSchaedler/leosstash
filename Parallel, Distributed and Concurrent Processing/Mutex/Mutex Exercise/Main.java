package mutex;

import java.util.Arrays;
import java.util.concurrent.Semaphore;

public class Main {

    public static int[] CHAVE = new int[100];
    public static int POS = 0;
    public static int contadorIMPAR = 0;
    public static int contadorPAR = 0;

    public static void main(String[] args) {

        Semaphore mutex = new Semaphore(4);
        GeradorPares gPar1 = new GeradorPares(mutex);
        GeradorPares gPar2 = new GeradorPares(mutex);
        GeradorImpares gImpar1 = new GeradorImpares(mutex);
        GeradorImpares gImpar2 = new GeradorImpares(mutex);

        gPar1.start();
        gImpar1.start();
        gPar2.start();
        gImpar2.start();

        try {
            gPar1.join();
            gImpar1.join();
            gPar2.join();
            gImpar2.join();

            System.out.println(Arrays.toString(Main.CHAVE));
            System.out.println("Numeros pares: " + Main.contadorPAR);
            System.out.println("Numeros impares: " + Main.contadorIMPAR);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
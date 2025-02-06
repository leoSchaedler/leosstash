package br.pucpr;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Main {

    //Todo: processadores
    private static final int taferasPorProcessador = 1;
    private static final Runtime runtime = Runtime.getRuntime();
    public static int nProcessadores = (runtime.availableProcessors()/2) * taferasPorProcessador;

    //Todo: threads
    public static Semaphore mutex = new Semaphore(1);
    public static int contFilhos = 0;

    //Todo: Globais
    private static final Random random = new Random();
    private static int sizeOfArray;

    public static void main(String[] args) throws InterruptedException, IOException {
        Tempos tempos = new Tempos();

        System.out.println(Legendas.getColor(-1) + "nProcessadores: " + nProcessadores);

            int loops = 0;
            int maxLopps = 5;

            while (loops < maxLopps) {
                ++loops;
                System.out.println(Legendas.getColor(-1) + "loop: " + loops + " -- begin of execution");

                tempos.settGerar1(System.currentTimeMillis());    //Get tempo de contrução da array

                int[] vetor = geradoraArray(15, 27, 0, 10000);

                tempos.settGerar2(System.currentTimeMillis());    //Get tempo de contrução da array

                tempos.settCalculo1(System.currentTimeMillis());  //Get tempo de sort da array

                Semaphore sincPai = new Semaphore(0);

                new Sort(vetor, 0, vetor.length - 1, 0, sincPai).start();

                sincPai.acquire();

                tempos.settCalculo2(System.currentTimeMillis());  //Get tempo de sort da array

                tempos.calcularDelta();
                tempos.escrever(String.valueOf(loops), String.valueOf(sizeOfArray));

                System.out.println(Legendas.getColor(-1) + "loop: " + loops + " -- end of execution");
//            System.out.println(Legendas.getColor(-1) + "loop: " + loops + " -- is sorted -> " + isSorted(vetor));
//            System.out.println(Legendas.getColor(-1) + "loop: " + loops + " -- vetor -> " + Arrays.toString(vetor));

                contFilhos = 0;
            }
        tempos.close();
    }

    private static int[] geradoraArray(int minBound, int maxBound, int minValue, int maxValue){
        int n = (random.nextInt(maxBound-minBound))+minBound;
//        int n = 6;  //teste
        sizeOfArray = (int) Math.pow(2,n);
        System.out.println(Legendas.getColor(-1) + "comprimento do vetor: 2^" + n + " = " + sizeOfArray);
        int[] vec = new int[sizeOfArray];
        for (int i = 0; i < sizeOfArray; i++)
            vec[i] = random.nextInt(maxValue - minValue) + minValue;
        return vec;
    }

    //confere se o vetor está ordenado
    private static boolean isSorted(int[] a) {
        for (int i = 0; i < a.length - 1; i++)
            if (a[i] > a[i + 1])
                return false;
        return true;
    }
}

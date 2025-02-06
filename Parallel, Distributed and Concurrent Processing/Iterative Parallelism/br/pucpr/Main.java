package br.pucpr;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.Semaphore;

public class Main {

    //Todo: Globais
    /*public static final int [][] mkn = {
            {1000,1000,1000},
            {2000,2000,2000},
            {1000,2000,1000},
            {2000,4000,2000}
    };*/

    public static final int [][] mkn = {
            {1000,1000,1000},
            {1000,1000,1000},
            {1000,1000,1000},
            {1000,1000,1000}
    };

    public static int m;
    public static int k;
    public static int n;
    public static int contadorGlobal = 1;

    //Todo: Tempos
    public static long tLeitura_1;
    public static long tLeitura_2;
    private static long tLeitura;
    //Calculo
    public static long tCalculo_1;
    public static long tCalculo_2;
    private static long tCalculo;
    //Escrita
    public static long tEscrita_1;
    public static long tEscrita_2;
    private static long tEscrita;

    //Todo: Threads
    private static final int taferasPorProcessador = 1;
    private static final Runtime runtime = Runtime.getRuntime();
    private static final int nProcessadores = runtime.availableProcessors()/2;
    public static final int nThreads = 1 * taferasPorProcessador;
    public static int nElementos;

    //Todo: Arquivos
    public static String pathA;
    public static BufferedWriter fileA;
    public static String pathB;
    public static BufferedWriter fileB;
    public static String pathC;
    public static BufferedWriter fileC;

    public static BufferedWriter fileT;

    //Todo: Matriz
    public static double[][] matrizA;
    public static double[][] matrizB;
    public static double[][] matrizC;

    //Todo: Semaforo
    public static Semaphore sincronizador = new Semaphore(0);

    private static int[] divisora_inicio(){
        int[] parts = new int[nThreads];
        for (int i = 0; i < nThreads; i++)
            parts[i] = (i) * nElementos;
        return parts;
    }

    private static int[] divisora_ultimo(){
        int[] parts = new int[nThreads];
        for (int i = 0; i < nThreads; i++)
            parts[i] = ((i+1) * nElementos)-1;
        return parts;
    }

    private static void clearFiles() throws IOException {
        File folder = new File("C:\\Users\\hrsch\\Área de Trabalho\\Files");
        File[] listFiles = folder.listFiles();
        for (File f : listFiles)
            if (f.exists()) f.delete();
        File folderTimes = new File("C:\\Users\\hrsch\\Área de Trabalho\\FilesTimes");
        File[] listFilesTimes = folderTimes.listFiles();
        for (File f : listFilesTimes)
            if (f.exists()) f.delete();
    }

    private static void clearMatrix(){
        for (int i = 0; i < Main.matrizA.length; i++)
            for (int j = 0; j < Main.matrizA[i].length; j++)
                Main.matrizA[i][j] = 0.0D;
        for (int i = 0; i < Main.matrizB.length; i++)
            for (int j = 0; j < Main.matrizB[i].length; j++)
                Main.matrizB[i][j] = 0.0D;
        for (int i = 0; i < Main.matrizC.length; i++)
            for (int j = 0; j < Main.matrizC[i].length; j++)
                Main.matrizC[i][j] = 0.0D;
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        clearFiles();

        int contador = 0;
        int max = 50;    //Numero de vezes que o algoritmo irá executar

        while (contador < max){
            System.out.println("\033[1;31m" + "Loop " + (contador) + " incializou");

            fileT = new BufferedWriter(new FileWriter(new File("C:\\Users\\hrsch\\Área de Trabalho\\FilesTimes\\Times_" + contador + ".csv")));

            for (int i = 0; i < mkn.length; i++){

                m = mkn[i][0];
                k = mkn[i][1];
                n = mkn[i][2];

                //gerar arquivos
                new Geradora("A", String.valueOf(contadorGlobal), m,k).gerar();
                new Geradora("B", String.valueOf(contadorGlobal), k,n).gerar();

                matrizC = new double[m][n];

                new Leitora().ler();    //ler arquivos

                nElementos = (m*n)/nThreads;

                int[] nElementosPorParte_primeiros = divisora_inicio();
                int[] nElementosPorParte_ultimos = divisora_ultimo();

                tCalculo_1 = System.currentTimeMillis();

                for (int j = 0; j < nThreads; j++)
                    new Calculadora(String.valueOf(j), nElementosPorParte_primeiros[j], nElementosPorParte_ultimos[j]).start();

                new Escritora(String.valueOf(contadorGlobal)).escrever();

                //Calculo do tempo de execucao para ler a matrizA e matrizB
                tLeitura = tLeitura_2 - tLeitura_1;

                //Calculo do tempo de execucao para calcular a matrizC
                tCalculo = tCalculo_2 - tCalculo_1;

                //Calculo do tempo de execucao para escrever a matrizC
                tEscrita = tEscrita_2 - tEscrita_1;

                fileT.write(contadorGlobal + "," + nProcessadores + "," + m + "," + k + "," + n + "," + tLeitura + "," + tCalculo + "," + tEscrita);
                fileT.newLine();

                System.out.println("\033[0;31m" + "Processo " + (contadorGlobal++) + " concluido");

                clearMatrix();
            }
            fileT.close();
            System.out.println("\033[1;31m" + "Loop " + (contador++) + " concluido");
        }
    }
}

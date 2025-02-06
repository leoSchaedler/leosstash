package br.pucpr;

import java.io.*;
import java.util.Random;

public class Geradora {

    //Identificadores
    private final String named;
    private final String id;

    //Matriz
    private final int m;
    private final int k;
    private final double[][] matriz;

    //Random
    private final int maxRandom;
    private final int minRandom;
    private final Random rand;

    public Geradora(String id, String named, int m, int k){
        this.id = id;
        this.named = named;
        this.m = m;
        this.k = k;
        this.matriz = new double[m][k];
        this.maxRandom = 1000;
        this.minRandom = 0;
        this.rand = new Random();
    }

    private double getRandom(){
        return (rand.nextInt(maxRandom-minRandom) + minRandom) + rand.nextDouble();
    }

    private void criateFile() throws IOException {
        if (id == "a" || id == "A") {
            Main.pathA = "C:\\Users\\hrsch\\Área de Trabalho\\Files\\A.txt";
            File fileA = new File(Main.pathA);
            fileA.createNewFile();
            Main.fileA = new BufferedWriter(new FileWriter(fileA));
        }
        else if (id == "b" || id == "B") {
            Main.pathB = "C:\\Users\\hrsch\\Área de Trabalho\\Files\\B.txt";
            File fileB = new File(Main.pathB);
            fileB.createNewFile();
            Main.fileB = new BufferedWriter(new FileWriter(fileB));
        }
        System.out.println("\033[0;32m" + "Geradora [" + this.id +"] >> Terminou de Criar o arquivo");
    }

    private void gerarNovaMatriz(){
        for (int i = 0; i < this.m; i++)
            for (int j = 0; j < this.k; j++)
                this.matriz[i][j] = getRandom();
        System.out.println("\033[0;32m" + "Geradora [" + this.id +"] >> Terminou de gerar");
    }

    private void escrever() throws IOException {
        if (id == "a" || id == "A") {
            Main.fileA.write(this.matriz.length + "," + this.matriz[0].length);
            Main.fileA.newLine();
            for (double[] i : this.matriz) {
                for (double j : i)
                    Main.fileA.write(j + ",");
                Main.fileA.newLine();
            }
            Main.fileA.close();
        } else if (id == "b" || id == "B") {
            Main.fileB.write(this.matriz.length + "," + this.matriz[0].length);
            Main.fileB.newLine();
            for (double[] i : this.matriz) {
                for (double j : i)
                    Main.fileB.write(j + ",");
                Main.fileB.newLine();
            }
            Main.fileB.close();
        }
        System.out.println("\033[0;32m" + "Geradora [" + this.id +"] >> Finalizou a escrita");
    }

    public void gerar() throws IOException {
        criateFile();
        gerarNovaMatriz();
        escrever();
        System.out.println("\033[0;32m" + "Geradora [" + this.id +"] >> FInalizou tarefa");
    }
}

package br.pucpr;

import java.io.*;

public class Leitora {

    //A
    private final BufferedReader fileA;
    private double[][] matrizA;

    //B
    private final BufferedReader fileB;
    private double[][] matrizB;

    public Leitora() throws FileNotFoundException {
        this.fileA = new BufferedReader(new FileReader(new File(Main.pathA)));
        this.fileB = new BufferedReader(new FileReader(new File(Main.pathB)));
    }

    private void leituraA() throws IOException {
        String[] auxS = fileA.readLine().split(",");
        int[] size = {Integer.parseInt(auxS[0]), Integer.parseInt(auxS[1])};
        this.matrizA = new double[size[0]][size[1]];
        for (int i = 0; i < size[0]; i++){
            String[] line = fileA.readLine().split(",");
            for (int j = 0; j < line.length; j++)
                this.matrizA[i][j] = Double.parseDouble(line[j]);
        }
        System.out.println("\033[0;34m" + "Leitura da matriz A");
    }

    private void leituraB() throws IOException {
        String[] auxS = fileB.readLine().split(",");
        int[] size = {Integer.parseInt(auxS[0]), Integer.parseInt(auxS[1])};
        this.matrizB = new double[size[0]][size[1]];
        for (int i = 0; i < size[0]; i++){
            String[] line = fileB.readLine().split(",");
            for (int j = 0; j < line.length; j++)
                this.matrizB[i][j] = Double.parseDouble(line[j]);
        }
        System.out.println("\033[0;34m" + "Leitura da matriz B");
    }

    private void saveMain(){
        Main.matrizA = new double[this.matrizA.length][this.matrizA[0].length];
        Main.matrizB = new double[this.matrizB.length][this.matrizB[0].length];
        for (int i = 0; i < this.matrizA.length; i++)
            for (int j = 0; j < this.matrizA[0].length; j++){
                Main.matrizA[i][j] = this.matrizA[i][j];
                this.matrizA[i][j] = 0.0D;
            }
        for (int i = 0; i < this.matrizB.length; i++)
            for (int j = 0; j < this.matrizB[0].length; j++){
                Main.matrizB[i][j] = this.matrizB[i][j];
                this.matrizB[i][j] = 0.0D;
            }
        System.out.println("\033[0;34m" + "Matrizes salvas no Main");
    }

    public void ler() throws IOException {

        Main.tLeitura_1 = System.currentTimeMillis();
        leituraA();
        leituraB();
        Main.tLeitura_2 = System.currentTimeMillis();

        saveMain();
        System.out.println("\033[0;34m" + "Leitura completa");
    }

}

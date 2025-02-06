package br.pucpr;

import java.util.Arrays;

public class Calculadora extends Thread{

    private final int inicio;
    private final int ultimo;

    private final String id;

    public Calculadora(String id, int inicio, int ultimo){
        this.inicio = inicio;
        this.ultimo = ultimo;
        this.id = id;
    }

    public void multiplicaçãoDeMatrizes(){
        int n = Main.matrizA.length;       //  numero coluna
        int k = Main.matrizA[0].length;    //  numero de linha
        int m = Main.matrizB[0].length;    //  numero de coluna
        for (int x = this.inicio; x < this.ultimo; x++){
            int[] pos = achaPosicao(x);
            int linha = pos[0];
            int coluna = pos[1];
//            System.out.println("calculando linha: " + linha + " calculando coluna: " + coluna);
            Main.matrizC[linha][coluna] = 0.0;
            for (int i = 0; i < k; i++)
                Main.matrizC[linha][coluna] = Main.matrizC[linha][coluna] + Main.matrizA[linha][i] * Main.matrizB[i][coluna];
        }
    }

    private int[] achaPosicao(int x){
        return new int[]{x / Main.matrizC.length, x % Main.matrizC.length};
    }

    @Override
    public void run() {
        multiplicaçãoDeMatrizes();
        System.out.println("\033[0;35m" + "Calculadora [" + this.id + "]" + " >> Multiplicação concluida");
        Main.sincronizador.release();   //Libera
    }
}

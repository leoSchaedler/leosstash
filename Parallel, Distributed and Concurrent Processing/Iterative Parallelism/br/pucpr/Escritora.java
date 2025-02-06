package br.pucpr;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class Escritora {

    private final String id;

    public Escritora(String id) throws IOException {
        this.id = id;
        Main.pathC = "C:\\Users\\hrsch\\Área de Trabalho\\Files\\C.txt";
        File file = new File(Main.pathC);
        file.createNewFile();
        Main.fileC = new BufferedWriter(new FileWriter(file));
    }

    public void escrever() throws IOException, InterruptedException {

        Main.sincronizador.acquire(Main.nThreads);  // Espera pelas Threads acabarem de calcular

        Main.tCalculo_2 = System.currentTimeMillis();

        //Escrita
        Main.tEscrita_1 = System.currentTimeMillis();
        for (double[] i : Main.matrizC) {
            Main.fileC.write(Arrays.toString(i));
            Main.fileC.newLine();
        }
        Main.tEscrita_2 = System.currentTimeMillis();

        Main.fileC.close();
        System.out.println("\033[0;33m" + "Escritora [" + this.id + "] terminou a escrita");
    }

}

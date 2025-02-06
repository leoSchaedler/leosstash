

import Arvore.ArvoreBinaria;

import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;

public class Teste {


    public static void main(String[] args) {


        int[] numeros;
        ArvoreBinaria<Integer> x = new ArvoreBinaria<Integer>();

        Random r = new Random();
        Scanner input = new Scanner(System.in);
        int range;


        System.out.print("Informe o tamanho do conjunto de dados : ");
        range = input.nextInt();
        System.out.println();
        numeros = new int[range];
        for(int i=0;i<range;i++){
            numeros[i] = r.nextInt(10000) * r.nextInt(10000);
        }
        System.out.printf("%d numeros aleatorios gerados\n\n", range);



        System.out.println("Arvore Binaria: ");
        long startArvore = System.currentTimeMillis();
        for(int i=0;i<range;i++){
            x.inserir(numeros[i]);
        }
        long endArvore = System.currentTimeMillis();
        float tempoArvore = (endArvore - startArvore) / 1000F;
        System.out.printf("Tempo para inserir: %.2f segundos\n", tempoArvore);


        long startProcuraArvore = System.currentTimeMillis();
        for(int i=0;i<range;i++){
            x.procurar(numeros[i]);
        }
        long endtProcuraArvore = System.currentTimeMillis();
        float tempoArvoreProcura = (endtProcuraArvore - startProcuraArvore) / 1000F;
        System.out.printf("Tempo de busca: %.2f segundos\n", tempoArvoreProcura);
        System.out.printf("Tempo total: %.2f segundos\n\n", (tempoArvore+ tempoArvoreProcura));


        System.out.println("Array: ");
        long startLista = System.currentTimeMillis();
        Arrays.sort(numeros);
        long endLista = System.currentTimeMillis();
        float tempoLista = (endLista - startLista) / 1000F;
        System.out.printf("Tempo para ordenar: %.2f segundos\n", tempoLista);


        long startBusca = System.currentTimeMillis();
        for(int i=0;i<range;i++) {
            BuscaBinaria.buscar(numeros, numeros[i]);
        }
        long endBusca = System.currentTimeMillis();
        float tempoBusca = (endBusca - startBusca) / 1000F;
        System.out.printf("Tempo para busca binaria: %.2f segundos\n", tempoBusca);
        System.out.printf("Tempo total: %.2f segundos\n\n", (tempoLista + tempoBusca));









    }


}

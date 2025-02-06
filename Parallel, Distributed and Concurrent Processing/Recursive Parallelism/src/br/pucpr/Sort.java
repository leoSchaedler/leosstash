package br.pucpr;

import java.util.Arrays;
import java.util.concurrent.Semaphore;

public class Sort  extends Thread{

    //Global
    private final int id;

    //QuickSort
    private final int[] vetor;
    private final int inicio;
    private final int fim;

    //Semaforos
    private final Semaphore sincPai;
    private final Semaphore sincFilho;

    public Sort( int[] vetor, int inicio, int fim, int id, Semaphore sincPai){
        this.id = id;
        this.inicio = inicio;
        this.fim = fim;
        this.vetor = vetor;
        this.sincPai = sincPai;
        this.sincFilho = new Semaphore(0);
    }

    @Override
    public void run() {
        try {
            Main.mutex.acquire();
                Main.contFilhos = Main.contFilhos + 1;
            Main.mutex.release();

            System.out.println(Legendas.getColor(id) + "Thread altura [" + this.id + "] -- " + Legendas.getComeco() + " começou calculo em sequencia");

            quickSort_Paralelo(vetor, inicio, fim);  //chamada da função de quickSort

            System.out.println(Legendas.getColor(id) + "Thread altura [" + this.id + "] -- " + Legendas.getFim() + " terminou calculo em sequencia");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private boolean conferese() throws InterruptedException {
        Main.mutex.acquire();
            if (Main.contFilhos < Main.nProcessadores){
                Main.mutex.release();
                return true;
            }
            else{
                Main.mutex.release();
                return false;
            }
    }

    private void quickSort_Paralelo(int[] vetor, int inicio, int fim) throws InterruptedException {
        if (inicio <= fim && conferese()) //Confere se ainda é possível criar novos filhos
        {
            int posicaoPivo = separar(vetor, inicio, fim);

            //Deixa de ser filho
            Main.mutex.acquire();
                Main.contFilhos = Main.contFilhos - 1;
            Main.mutex.release();

            System.out.println(Legendas.getColor(id) + "Thread altura [" + this.id + "] -- dividiu");

            //Cria novos dois filhos
            new Sort(vetor, inicio, posicaoPivo-1, id+1, sincFilho).start();
            new Sort(vetor, posicaoPivo+1, fim, id+1, sincFilho).start();

            sincFilho.acquire(2);
        }
        else
        {
            sort_Sequencial(vetor, inicio, fim);
        }
        sincPai.release();
    }

    private void sort_Sequencial(int[] vetor, int inicio, int fim){
        if (fim > inicio){
            int size = fim-inicio+1;
            int [] seroma = new int[size];
            //Copia da array
            System.arraycopy(vetor, inicio, seroma, 0, size);
            //Fazer o sort da copia
            Arrays.sort(seroma);
            //Voltar para o array original
            System.arraycopy(seroma, 0, vetor, inicio, size);
        }
    }

    private int separar(int[] vetor, int inicio, int fim) {
        int pivo = vetor[inicio];
        int i = inicio + 1, f = fim;
        while (i <= f) {
            if (vetor[i] <= pivo)
                i++;
            else if (pivo < vetor[f])
                f--;
            else {
                int troca = vetor[i];
                vetor[i] = vetor[f];
                vetor[f] = troca;
                i++;
                f--;
            }
        }
        vetor[inicio] = vetor[f];
        vetor[f] = pivo;
        return f;
    }

}

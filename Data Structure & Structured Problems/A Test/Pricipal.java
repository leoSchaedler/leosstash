import java.util.Random;
import java.util.Scanner;

public class Pricipal {
    public static void main(String[] args) {
        Random r = new Random();
        Scanner input = new Scanner(System.in);
        TabHash tabela;
        ArvoreBinaria arvore = new ArvoreBinaria();

        int tamanho;
        int[] chavesAinserir;
        int[] chavesaProcurar;

        System.out.println("=================================================");
        System.out.println("*     Comparação empirica entre Tabela Hash     *");
        System.out.println("*                        e                      *");
        System.out.println("*            Árvore Binária de Busca            *");
        System.out.println("=================================================");


        System.out.print("Informe o tamanho do conjunto de dados : ");
        tamanho = input.nextInt();
        System.out.println();
        chavesAinserir = new int[tamanho];
        chavesaProcurar = new int[tamanho];
        tabela = new TabHash(tamanho);
        for(int i=0;i<tamanho;i++){
            chavesAinserir[i] = 1 + r.nextInt();
        }
        System.out.printf("%d numeros aleatorios gerados\n\n", tamanho);

        for(int i=0;i<tamanho;i++){
            chavesaProcurar[i] = 1 + r.nextInt();
        }

/////////////////// Inserção na tabela ////////////////////////////
        System.out.println("-> Tabela hash ");
        long startTab = System.currentTimeMillis();
        for(int i=0;i<tamanho;i++){
            tabela.insere(chavesAinserir[i]);
        }
        long endTab = System.currentTimeMillis();
        float tempoTabela = (endTab - startTab) / 1000F;
        System.out.printf("Tempo para inserir: %.2f segundos\n", tempoTabela);
 /////////////////// Busca na tabela ////////////////////////////
        long startTab2 = System.currentTimeMillis();
        for(int i=0;i<tamanho;i++){
            tabela.busca(chavesaProcurar[i]);
        }
        long endTab2 = System.currentTimeMillis();
        float tempoTabela2 = (endTab2 - startTab2) / 1000F;
        System.out.printf("Tempo para busca: %.2f segundos\n", tempoTabela2);
        System.out.printf("Tempo total: %.2f segundos\n", tempoTabela2 + tempoTabela);
        System.out.printf("Total de colisões : %d \n\n", tabela.getNcolisoes());



//////////////// Inserção na arvore /////////////////////////////////////
        System.out.println("-> Arvore Binaria de Busca ");
        long startArvore = System.currentTimeMillis();
        for(int i=0;i<tamanho;i++){
            arvore.inserir(chavesAinserir[i]);
        }
        long endArvore = System.currentTimeMillis();
        float tempoArvore = (endArvore - startArvore) / 1000F;
        System.out.printf("Tempo para inserir: %.2f segundos\n", tempoArvore);

//////////////// Busca na arvore /////////////////////////////////////
        long startArvore2 = System.currentTimeMillis();
        for(int i=0;i<tamanho;i++){
            arvore.procurar(chavesaProcurar[i]);
        }
        long endArvore2 = System.currentTimeMillis();
        float tempoArvore2 = (endArvore2 - startArvore2) / 1000F;
        System.out.printf("Tempo para buscar: %.2f segundos\n", tempoArvore2);
        System.out.printf("Tempo total: %.2f segundos\n\n", tempoArvore + tempoArvore2);












    }
}

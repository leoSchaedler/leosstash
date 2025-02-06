package Utilidade;

import Grafo.*;

import java.math.*;
import java.security.SecureRandom;
import java.util.Scanner;

public class Gerador {


    private static SecureRandom random = new SecureRandom();
    private static Scanner input = new Scanner(System.in);


    private static double roundDouble(int places) {
        BigDecimal bigDecimal = new BigDecimal(Double.toString(random.nextInt(1000) + random.nextDouble()));
        bigDecimal = bigDecimal.setScale(places, RoundingMode.HALF_UP);
        return bigDecimal.doubleValue();
    }

    public static Grafo gerar() {
        int NumVertices;
        int NumArestas;
        char conexo;

        System.out.print("Numero de vertices: ");
        NumVertices = input.nextInt();
        System.out.print("\nNumero de arestas: ");
        NumArestas = input.nextInt();
        System.out.print("\nConexo(S/N)?: ");
        conexo = input.next().charAt(0);
        System.out.println();


        // Grafo g = new Grafo(NumVertices);

        if (conexo == 's' || conexo == 'S') {
            return gerarConexo(NumVertices, NumArestas);
        } else if (conexo == 'n' || conexo == 'N') {
            return gerarNConexo(NumVertices, NumArestas);
        }


        return null;
    }



    private static Grafo gerarConexo(int NumVertices, int NumArestas) {
        Grafo g = new Grafo(NumVertices);

        int inicio = 0;
        int fim = 1;
        int aux = NumArestas;
        double peso;


        for (int i = 0; i < NumVertices; i++)
            g.seta_informacao(i, String.valueOf(i));

        while (fim != NumVertices) {
            peso = roundDouble(2);
            g.cria_adjacenciaIn(inicio, fim, peso);
            g.cria_adjacenciaIn(fim++, inicio++, peso);
            aux -= 2;
        }


        if (aux < 0)
            System.out.printf("\nATENÇÂO\nNúmero minimo de vertices para um grafo direcionado e " +
                            "conexo com %d vertices é de %d arestas \n\n",
                    NumVertices, g.numArestas());

        while (aux > 0) {
            inicio = random.nextInt(NumVertices);
            fim = random.nextInt(NumVertices);

            while (inicio - 1 == fim || inicio + 1 == fim)
                fim = random.nextInt(NumVertices);

                g.cria_adjacenciaIn(inicio, fim, roundDouble(2));
                aux--;

        }


        return g;
    }


    private static Grafo gerarNConexo(int NumVertices, int NumArestas) {
        Grafo g = new Grafo(NumVertices);

        int inicio = 0;
        int fim = 1;
        int aux = NumArestas;
        int peso;

        for (int i = 0; i < NumVertices; i++)
            g.seta_informacao(i, String.valueOf(i));

        int um = random.nextInt(NumVertices);
        int dois = random.nextInt(NumVertices);


        if ((um != dois)) {
            g.cria_adjacenciaIn(um, dois, roundDouble(2));
            aux--;
        } else {
            g.cria_adjacenciaIn(um, dois+1, roundDouble(2));
            aux--;
        }

        while (aux > 0) {
            inicio = random.nextInt(NumVertices);
            fim = random.nextInt(NumVertices);

            while (inicio == um && fim == dois) {
                inicio = random.nextInt(NumVertices);
                fim = random.nextInt(NumVertices);
            }

            while (inicio - 1 == fim || inicio + 1 == fim)
                fim = random.nextInt(NumVertices);


                    g.cria_adjacenciaIn(inicio, fim, roundDouble(2));
                    aux--;

            }


        return g;
        }


    }



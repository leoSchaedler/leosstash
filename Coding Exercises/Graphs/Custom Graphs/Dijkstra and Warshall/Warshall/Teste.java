package br.pucpr.Warshall;

public class Teste {

    public static void main(String[] args) throws Exception {

        Grafo G = new Grafo(4);

        G.matriz = new int[][] {{0, 5, Warshall.INF, 10},
                {Warshall.INF, 0, 3, Warshall.INF},
                {Warshall.INF, Warshall.INF, 0, 1},
                {Warshall.INF, Warshall.INF, Warshall.INF, 0}
        };

        System.out.println("=======================================================================");
        System.out.println("*                            Grafo                                    *");
        System.out.println("=======================================================================\n");

        G.imprimi_adjacencias();
        System.out.println();

        System.out.println("=======================================================================");
        System.out.println("*                            Warshal                                   *");
        System.out.println("=======================================================================\n");

        Warshall floyd = new Warshall();
        floyd.floydWarshall(G);

    }

}






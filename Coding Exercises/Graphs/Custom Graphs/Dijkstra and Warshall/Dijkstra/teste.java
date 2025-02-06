package br.pucpr.Dijkstra;

public class teste {

    public static void main(String[] args) {

        Grafo G = new Grafo(6);

        G.seta_informacao(0,"x");
        G.seta_informacao(1,"1");
        G.seta_informacao(2,"2");
        G.seta_informacao(3,"3");
        G.seta_informacao(4,"4");
        G.seta_informacao(5,"y");


        G.cria_adjacencia(0,1,3);
        G.cria_adjacencia(1,0,3);

        G.cria_adjacencia(1,3,6);
        G.cria_adjacencia(3,1,6);

        G.cria_adjacencia(3,5,3);
        G.cria_adjacencia(5,3,3);

        G.cria_adjacencia(5,4,1);
        G.cria_adjacencia(4,5,1);

        G.cria_adjacencia(4,2,7);
        G.cria_adjacencia(2,4,7);

        G.cria_adjacencia(2,0,8);
        G.cria_adjacencia(0,2,8);

        G.cria_adjacencia(0,3,4);
        G.cria_adjacencia(3,0,4);

        G.cria_adjacencia(3,4,1);
        G.cria_adjacencia(4,3,1);

        G.cria_adjacencia(0,5,10);
        G.cria_adjacencia(5,0,10);

        System.out.println("=======================================================================");
        System.out.println("*                            Grafo                                    *");
        System.out.println("=======================================================================\n");

        G.imprime_adjacencias();
        System.out.println();

        System.out.println("=======================================================================");
        System.out.println("*                           Dijkstra                                  *");
        System.out.println("=======================================================================\n");


        Buscas.dijkstra(G, 0, 4);
        Buscas.dijkstra(G, 2, 5);


//        Buscas.Profundidade(G,G.vertices[0]);
//        System.out.println("\n");
//        G.resetarV();                                           //Reset manual
//        Buscas.Largura(G,G.vertices[0]);
//        System.out.println("\n");
//        G.resetarV();

    }
}

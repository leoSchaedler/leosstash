package br.pucpr;


import br.pucpr.Grafo.Grafo;
import br.pucpr.Grafo.Vertice;

import java.util.ArrayList;
import java.util.List;

public class neww {

    public static void main(String[] args) {
        Grafo G = new Grafo(6);

        G.seta_informacao(0,"x");
        G.seta_informacao(1,"1");
        G.seta_informacao(2,"2");
        G.seta_informacao(3,"3");
        G.seta_informacao(4,"4");
        G.seta_informacao(5,"y");



        G.cria_adjacenciaIn(0,1,3);
        G.cria_adjacenciaIn(1,0,3);

        G.cria_adjacenciaIn(1,3,6);
        G.cria_adjacenciaIn(3,1,6);

        G.cria_adjacenciaIn(3,5,3);
        G.cria_adjacenciaIn(5,3,3);

        G.cria_adjacenciaIn(5,4,1);
        G.cria_adjacenciaIn(4,5,1);

        G.cria_adjacenciaIn(4,2,7);
        G.cria_adjacenciaIn(2,4,7);

        G.cria_adjacenciaIn(2,0,8);
        G.cria_adjacenciaIn(0,2,8);

        G.cria_adjacenciaIn(0,3,4);
        G.cria_adjacenciaIn(3,0,4);

        G.cria_adjacenciaIn(3,4,1);
        G.cria_adjacenciaIn(4,3,1);

        G.cria_adjacenciaIn(0,5,10);
        G.cria_adjacenciaIn(5,0,10);

        G.imprime_adjacencias();

        System.out.println();

        G.nComponentes();

        if(G.isCyclic())
            System.out.println("Graph contains cycle");
        else
            System.out.println("Graph doesn't "
                    + "contain cycle");

        /*

        Buscas.dijkstra(G,0,5);

        System.out.println();

        G.maiorCaminho(0, 5);

        System.out.println();

        ArrayList<Vertice> mem = G.profundPorDist(0,2);

        for (Vertice i : mem)
            System.out.println(i.getId());

        System.out.println();


     //  G.maiorCaminho(0);

         */


    }
}

package testeGRafo_2311;

import Grafo.Grafo;

public class teste2311 {

    public static void main(String[] args) {

        Grafo g = new Grafo(5);

        g.seta_informacao(0, "A");
        g.seta_informacao(1, "B");
        g.seta_informacao(2, "C");
        g.seta_informacao(3, "D");
        g.seta_informacao(4, "E");


        g.cria_adjacenciaIn(0, 1, 1.00);
        g.cria_adjacenciaIn(1,0,1.00);
        g.cria_adjacenciaIn(1,2,1.00);
        g.cria_adjacenciaIn(2,1,1);
        g.cria_adjacenciaIn(2,3,1);
        g.cria_adjacenciaIn(3,2,1);
        g.cria_adjacenciaIn(3,4,1);
        g.cria_adjacenciaIn(4,3,1);

        g.imprime_adjacencias();


        for (int i = 0; i < g.getTAM(); i++)
            System.out.println(g.CentralidadePosicionamento(i));


        Grafo G = new Grafo(6);

        G.seta_informacao(0, "Mark");
        G.seta_informacao(1, "Bridget");
        G.seta_informacao(2, "Alice");
        G.seta_informacao(3, "Doug");
        G.seta_informacao(4, "Charles");
        G.seta_informacao(5, "Michael");

        G.cria_adjacenciaIn("Mark", "Alice");
        G.cria_adjacenciaIn("Alice", "Doug");
        G.cria_adjacenciaIn("Alice", "Bridget");
        G.cria_adjacenciaIn("Alice", "Charles");
        G.cria_adjacenciaIn("Charles", "Michael");

        G.imprime_adjacencias();
        System.out.println();
        for (int i = 0; i < G.getTAM(); i++)
            System.out.println(G.getVertice(i).getId() + " -> " + G.CentralidadeIntermediacao(G.getVertice(i), true));
    }

}

package testeGrafo_2111;

import Grafo.Grafo;

public class grafo2111 {

    public static void main(String[] args) {

        Grafo grafo = new Grafo(5);

        grafo.seta_informacao(0, "1");
        grafo.seta_informacao(1, "2");
        grafo.seta_informacao(2, "3");
        grafo.seta_informacao(3, "4");
        grafo.seta_informacao(4, "5");

        //vertice 1
        grafo.cria_adjacenciaIn(0,1, 1);
        grafo.cria_adjacenciaIn(0,2, 1);

        //vertice 2
        grafo.cria_adjacenciaIn(1,0, 1);
        grafo.cria_adjacenciaIn(1,3, 1);
        grafo.cria_adjacenciaIn(1,4, 1);

        //vertice 3
        grafo.cria_adjacenciaIn(2, 0, 1);
        grafo.cria_adjacenciaIn(2, 3, 1);

        //vertice 4
        grafo.cria_adjacenciaIn(3, 2, 1);
        grafo.cria_adjacenciaIn(3, 1, 1);

        //vertice 5
        grafo.cria_adjacenciaIn(4, 1, 1);


        System.out.println("Centralidade de Proximidade (2): " + grafo.CentralidadePosicionamento(1));
        System.out.println("Centralidade de Proximidade (5): " + grafo.CentralidadePosicionamento(4));

        System.out.println("Centralidade de Intermediação (2): " + grafo.CentralidadeIntermediacao(grafo.getVertice(1), true));
        System.out.println("Centralidade de Intermediação (1): " + grafo.CentralidadeIntermediacao(grafo.getVertice(0), false));
        System.out.println("Centralidade de Intermediação (5): " + grafo.CentralidadeIntermediacao(grafo.getVertice(4), false));
//        grafo.kruskal();

    }

}

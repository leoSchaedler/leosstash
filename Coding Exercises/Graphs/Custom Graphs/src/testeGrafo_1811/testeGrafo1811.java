package testeGrafo_1811;

import Grafo.Grafo;

public class testeGrafo1811 {

    public static void main(String[] args) {

        Grafo grafo = new Grafo(8);

        grafo.seta_informacao(0,"1");
        grafo.seta_informacao(1,"2");
        grafo.seta_informacao(2,"3");
        grafo.seta_informacao(3,"4");
        grafo.seta_informacao(4,"5");
        grafo.seta_informacao(5,"6");
        grafo.seta_informacao(6,"7");
        grafo.seta_informacao(7,"8");

        /*
        //vert 1:
        grafo.cria_adjacenciaIn(0,1,3.00);
        grafo.cria_adjacenciaIn(0,4,1.00);

        //vert 2:
        grafo.cria_adjacenciaIn(1,0,3.00);
        grafo.cria_adjacenciaIn(1,4,2.00);
        grafo.cria_adjacenciaIn(1,6,5.00);
        grafo.cria_adjacenciaIn(1,7,4.00);
        grafo.cria_adjacenciaIn(1,3,2.00);
        grafo.cria_adjacenciaIn(1,2,3.00);

        //vert 3:
        grafo.cria_adjacenciaIn(2,1,3.00);
        grafo.cria_adjacenciaIn(2,3,2.00);

        //vert 4:
        grafo.cria_adjacenciaIn(3,2,2.00);
        grafo.cria_adjacenciaIn(3,1,2.00);
        grafo.cria_adjacenciaIn(3,7,2.00);

        //vert 5:
        grafo.cria_adjacenciaIn(4,0,1.00);
        grafo.cria_adjacenciaIn(4,1,2.00);
        grafo.cria_adjacenciaIn(4,6,3.00);
        grafo.cria_adjacenciaIn(4,5,2.00);

        //vert 6:
        grafo.cria_adjacenciaIn(5,4,2.00);
        grafo.cria_adjacenciaIn(5,6,2.00);

        //vert 7:
        grafo.cria_adjacenciaIn(6,4,3.00);
        grafo.cria_adjacenciaIn(6,5,2.00);
        grafo.cria_adjacenciaIn(6,1,5.00);
        grafo.cria_adjacenciaIn(6,7,1.00);

        //vert 8:
        grafo.cria_adjacenciaIn(7,6,1.00);
        grafo.cria_adjacenciaIn(7,1,4.00);
        grafo.cria_adjacenciaIn(7,3,2.00);
         */

//        /*
        //vert 1:
        grafo.cria_adjacenciaIn(0,1,1.00);
        grafo.cria_adjacenciaIn(0,4,1.00);

        //vert 2:
        grafo.cria_adjacenciaIn(1,0,1.00);
        grafo.cria_adjacenciaIn(1,4,1.00);
        grafo.cria_adjacenciaIn(1,6,1.00);
        grafo.cria_adjacenciaIn(1,7,1.00);
        grafo.cria_adjacenciaIn(1,3,1.00);
        grafo.cria_adjacenciaIn(1,2,1.00);

        //vert 3:
        grafo.cria_adjacenciaIn(2,1,1.00);
        grafo.cria_adjacenciaIn(2,3,1.00);

        //vert 4:
        grafo.cria_adjacenciaIn(3,2,1.00);
        grafo.cria_adjacenciaIn(3,1,1.00);
        grafo.cria_adjacenciaIn(3,7,1.00);

        //vert 5:
        grafo.cria_adjacenciaIn(4,0,1.00);
        grafo.cria_adjacenciaIn(4,1,1.00);
        grafo.cria_adjacenciaIn(4,6,1.00);
        grafo.cria_adjacenciaIn(4,5,1.00);

        //vert 6:
        grafo.cria_adjacenciaIn(5,4,1.00);
        grafo.cria_adjacenciaIn(5,6,1.00);

        //vert 7:
        grafo.cria_adjacenciaIn(6,4,1.00);
        grafo.cria_adjacenciaIn(6,5,1.00);
        grafo.cria_adjacenciaIn(6,1,1.00);
        grafo.cria_adjacenciaIn(6,7,1.00);

        //vert 8:
        grafo.cria_adjacenciaIn(7,6,1.00);
        grafo.cria_adjacenciaIn(7,1,1.00);
        grafo.cria_adjacenciaIn(7,3,1.00);
//         */


        grafo.imprime_adjacencias();
        System.out.println();

        System.out.println("a)");
        System.out.println("Coeficiente de Agrupamento Local (1): " + grafo.caLocal(grafo.getVertice(0), false));
        System.out.println("Coeficiente de Agrupamento Local (2): " + grafo.caLocal(grafo.getVertice(1), false));
        System.out.println("Coeficiente de Agrupamento Local (7): " + grafo.caLocal(grafo.getVertice(6), false));
        System.out.println("b)");
        System.out.println("Coeficiente de Agrupamento Medio: " + grafo.caMedio(false));
        System.out.println("c)");
        System.out.println("Centralidade de Proximidade (1): " + grafo.CentralidadePosicionamento(0));
        System.out.println("Centralidade de Proximidade (2): " + grafo.CentralidadePosicionamento(1));
        System.out.println("Centralidade de Proximidade (6): " + grafo.CentralidadePosicionamento(5));
        System.out.println("Centralidade de Proximidade (7): " + grafo.CentralidadePosicionamento(6));
        System.out.println("d)");
        System.out.println("Centralidade de Intermediação (1): " + grafo.CentralidadeIntermediacao(grafo.getVertice(0), false));
        System.out.println("Centralidade de Intermediação (2): " + grafo.CentralidadeIntermediacao(grafo.getVertice(1), false));
        System.out.println("Centralidade de Intermediação (7): " + grafo.CentralidadeIntermediacao(grafo.getVertice(6), false));
        System.out.println("Centralidade de Intermediação (8): " + grafo.CentralidadeIntermediacao(grafo.getVertice(7), false));
//        grafo.kruskal();

    }

}

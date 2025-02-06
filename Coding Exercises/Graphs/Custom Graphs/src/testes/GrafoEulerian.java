package testes;

import Grafo.Vertice;
import Grafo.Grafo;
import Utilidade.Buscas;

import java.util.ArrayList;

public class GrafoEulerian {

    public static void main(String[] args) {

        Grafo g = new Grafo(5);

        g.seta_informacao(0, "u1");
        g.seta_informacao(1, "u2");
        g.seta_informacao(2, "u3");
        g.seta_informacao(3, "u4");
        g.seta_informacao(4, "u5");

        /*
        //u1
        g.cria_adjacenciaIn(0, 1, 12.00);
        g.cria_adjacenciaIn(0, 2, 12.00);
        g.cria_adjacenciaIn(0, 4, 12.00);

        //u2
        g.cria_adjacenciaIn(1,0, 12.00);
        g.cria_adjacenciaIn(1,2, 12.00);

        //u3
        g.cria_adjacenciaIn(2,1,12.00);
        g.cria_adjacenciaIn(2,0,12.00);
        g.cria_adjacenciaIn(2,3,12.00);
        g.cria_adjacenciaIn(2,5,12.00);

        //u5
        g.cria_adjacenciaIn(4,2,12.00);

        //u4
        g.cria_adjacenciaIn(3,2,12.00);
        g.cria_adjacenciaIn(3,0,12.00);

         */

        g.cria_adjacenciaIn(1,0,2);
        g.cria_adjacenciaIn(0,2,3);
        g.cria_adjacenciaIn(2,1,4);
        g.cria_adjacenciaIn(0,3,5);
        g.cria_adjacenciaIn(3,4,0);
        g.cria_adjacenciaIn(4,0,0);

        g.imprime_adjacencias();
        System.out.println();

        System.out.println("Conexo: " + g.isConexo());
        System.out.println();

        System.out.println("Euleriano: " + g.isEuleriano());
        System.out.println();

        System.out.println("Centralidade de Intermediação (0): " + g.CentralidadeIntermediacao(g.getVertice(0), true));
        System.out.println("Centralidade de Intermediação (2): " + g.CentralidadeIntermediacao(g.getVertice(2), true));
        System.out.println("Centralidade de Intermediação (4): " + g.CentralidadeIntermediacao(g.getVertice(4), true));


        System.out.println();
        System.out.println("Centralidade de Posicionamento (0): "  + g.CentralidadePosicionamento(0));
    }

}

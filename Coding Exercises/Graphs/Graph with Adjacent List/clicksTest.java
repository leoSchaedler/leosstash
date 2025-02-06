package br.pucpr;

import br.pucpr.Grafo.Grafo;
import br.pucpr.Grafo.Vertice;

public class clicksTest {

    public static void main(String[] args) {
        Grafo g = new Grafo(5);

        g.seta_informacao(0, "1");
        g.seta_informacao(1, "2");
        g.seta_informacao(2, "3");
        g.seta_informacao(3, "4");
        g.seta_informacao(4, "5");

        //1
        g.cria_adjacenciaIn(0, 1, 0);
        g.cria_adjacenciaIn(0, 2, 0);
        g.cria_adjacenciaIn(0, 3, 0);

        //2
        g.cria_adjacenciaIn(1, 0, 0);
        g.cria_adjacenciaIn(1, 2, 0);
        g.cria_adjacenciaIn(1, 3, 0);

        //3
        g.cria_adjacenciaIn(2, 1, 0);
        g.cria_adjacenciaIn(2, 0, 0);
        g.cria_adjacenciaIn(2, 4, 0);
        g.cria_adjacenciaIn(2, 3, 0);

        //4
        g.cria_adjacenciaIn(3, 0, 0);
        g.cria_adjacenciaIn(3, 1, 0);
        g.cria_adjacenciaIn(3, 2, 0);
        g.cria_adjacenciaIn(3, 4, 0);

        //5
        g.cria_adjacenciaIn(4, 2, 0);
        g.cria_adjacenciaIn(4, 3, 0);

        Vertice[] aux = {g.getVertices()[2], g.getVertices()[3], g.getVertices()[4]};
//        Vertice[] aux = {g.getVertices()[0], g.getVertices()[1], g.getVertices()[2]};

        g.imprime_adjacencias();

        System.out.println();
        System.out.println("Forma um clique: " + g.isClick(aux));
        System.out.println("Clique é maximal: " + g.clickMax(aux));
    }

}

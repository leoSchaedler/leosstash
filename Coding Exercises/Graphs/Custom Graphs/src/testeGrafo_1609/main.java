package testeGrafo_1609;

import Grafo.*;

import java.io.IOException;


public class main {

    public static void main(String[] args) throws IOException {

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

        System.out.println(G.caLocal(G.getVertice(0), false));
        System.out.println(G.caMedio(false));


        System.out.println("Conexo: " + G.isConexo());

        System.out.println("Euleriano: " + G.isEuleriano());

        System.out.println("Centralidade de Proximidade (2): " + G.CentralidadePosicionamento(1));
        System.out.println("Centralidade de Proximidade (5): " + G.CentralidadePosicionamento(4));

        System.out.println("Centralidade de Intermediação (2): " + G.CentralidadeIntermediacao(G.getVertice(1), true));
        System.out.println("Centralidade de Intermediação (1): " + G.CentralidadeIntermediacao(G.getVertice(0), false));
        System.out.println("Centralidade de Intermediação (5): " + G.CentralidadeIntermediacao(G.getVertice(4), false));


    }









}

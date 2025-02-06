import Grafo.Grafo;
import Utilidade.Gerador;
import Utilidade.Pajek;

public class TDE05 {

    public static void main(String[] args) throws Exception{

        Pajek pajek = new Pajek();

        //TODO -> Carrega um grafo em um arquivo
        System.out.println("--  Carrega um grafo em um arquivo    ---");

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

        pajek.Gravar(G, true);

        System.out.println(">> Arquivo carregado");


        //TODO -> Gera um grafo a partir de um arquivo
        System.out.println();
        System.out.println("--  Gera um grafo a partir de um arquivo    ---");
        Grafo tnt = pajek.ler("testeGrafo.txt");
        tnt.imprime_adjacencias();


        //TODO -> FUNÇÕES
        System.out.println();
        System.out.println("---     FUNÇÕES     ---");

        Grafo grafo = new Grafo(8);

        grafo.seta_informacao(0,"1");
        grafo.seta_informacao(1,"2");
        grafo.seta_informacao(2,"3");
        grafo.seta_informacao(3,"4");
        grafo.seta_informacao(4,"5");
        grafo.seta_informacao(5,"6");
        grafo.seta_informacao(6,"7");
        grafo.seta_informacao(7,"8");

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

                /*
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
         */

        grafo.imprime_adjacencias();

        System.out.println();
        System.out.print("    -- Conexo: ");
        System.out.print(grafo.isConexo());
        System.out.println();
        System.out.print("    -- Euleriano: ");
        System.out.print(grafo.isEuleriano());
        System.out.println();
        System.out.print("    -- Ciclico: ");
        System.out.print(grafo.isCyclic());
        System.out.println();
        System.out.println("    -- Centralidade de Posicionamento: ");
        System.out.println("        (1): " + grafo.CentralidadePosicionamento(0));
        System.out.println("        (2): " + grafo.CentralidadePosicionamento(1));
        System.out.println("        (7): " + grafo.CentralidadePosicionamento(6));
        System.out.println("    -- Centralidade de Intermediação: ");
        System.out.println("        (1): " + grafo.CentralidadeIntermediacao(grafo.getVertice(0), false));
        System.out.println("        (2): " + grafo.CentralidadeIntermediacao(grafo.getVertice(1), false));
        System.out.println("        (7): " + grafo.CentralidadeIntermediacao(grafo.getVertice(6), false));
        System.out.println();



        /*//TODO -> Gera um grafo aleatorio
        System.out.println();
        System.out.println("--  Gera um grafo aleatorio    ---");
       Grafo grafoAleatorio = Gerador.gerar();
        grafoAleatorio.imprime_adjacencias();*/
    }

}

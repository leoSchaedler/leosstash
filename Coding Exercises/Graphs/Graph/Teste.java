package grafo;

public class Teste {

    public static void main(String[] args) throws Exception {


        Grafo G = new Grafo(4);

        G.cria_adjacencia(1,1,33);
        G.cria_adjacencia(2,1,87);
        G.cria_adjacencia(2,3,55);

        G.set_inf(1,"UmNó");
        G.set_inf(3,"TresNó");

        G.imprimi_adjacencias();
        System.out.println(" ");
        G.remove_adjacencia(2,3);
        G.imprimi_adjacencias();

        Grafo.Node[] adj = new Grafo.Node[10];

        int x = G.adjacentes(2,adj);
        System.out.println(x);


        System.out.print("[ ");
        for(int i=0;i<x;i++) {
            System.out.print(adj[i].getRotulo() + " ");
        }
        System.out.println("]");

    }

}




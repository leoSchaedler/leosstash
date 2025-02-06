

public class teste {

    public static void main(String[] args) {

        Grafo G = new Grafo(5);

        G.seta_informacao(0,"Zero");
        G.seta_informacao(1,"Um");
        G.seta_informacao(2,"Dois");
        G.seta_informacao(3,"Tres");
        G.seta_informacao(4,"Quatro");



        G.cria_adjacencia(0,1,4);
        G.cria_adjacencia(0,2,1);
        G.cria_adjacencia(1,0,55);
        G.cria_adjacencia(1,2,98);
        G.cria_adjacencia(2,0,91);
        G.cria_adjacencia(2,1,44);
        G.cria_adjacencia(2,2,11);
        G.cria_adjacencia(4,0,551);
        G.cria_adjacencia(4,2,87);



        System.out.println("=======================================================================");
        System.out.println("*                            Grafo                                    *");
        System.out.println("=======================================================================\n");

        G.imprime_adjacencias();
        System.out.println();


        System.out.println("=======================================================================");
        System.out.println("Removendo 1,0 e 2,1: ");
        G.remove_adjacencia(1,0);
        G.remove_adjacencia(2,1);
        G.imprime_adjacencias();


        System.out.println();
        Vertice[] adj = new Vertice[6];
        System.out.println("=======================================================================");
        System.out.print("Adjacencias do vertice 2 (id: \"Tres\"): ");
        System.out.println(G.adjacentes(2,adj));
        int j=0;
        System.out.print("[ ");
        while(adj[j]!=null) {
            System.out.print(adj[j].getId() + " ");
            j++;
        }
        System.out.println(" ]");

        System.out.println("=======================================================================");
        System.out.print("\nPeso entre as adjacencias (4,0): ");
        double p = G.peso(4,0);
        System.out.println(p);
        System.out.println("========================================================================\n");

      //  System.out.println(Arrays.toString(adj));





    }






}

import Grafo.Grafo;
import Utilidade.Gerador;
import Utilidade.Pajek;

public class testePajek_Random {

    public static void main(String[] args) throws Exception{

        Pajek pajek = new Pajek();

        Grafo G ;


//        G = Gerador.gerar();
//        G.imprime_adjacencias();

//        pajek.Gravar(G, false);

        System.out.println();


        Grafo tnt = pajek.ler("testeGrafo.txt");
//        Grafo tnt = pajek.ler("grafoExemplo5000.txt");
        tnt.imprime_adjacencias();
    }

}

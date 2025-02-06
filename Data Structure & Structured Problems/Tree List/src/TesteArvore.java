import Arvore.Arvore;
import Arvore.noArvore;

public class TesteArvore {


    public static void main(String[] args) {

        Arvore x = new Arvore();

        x.inserir("10","Arquivo X");
        x.inserir("09","Arquivo X");
        x.inserir("20","Arquivo X");
        x.inserir("31","Arquivo X");
        x.inserir("71", "Arquivo X");
        x.inserir("15","Arquivo X");



        x.imprimirPreOrdem();
        System.out.println();
        //x.imprimirInOrdem();
        System.out.println();
        //x.imprimirPosOrdem();
        System.out.println();

    }
}

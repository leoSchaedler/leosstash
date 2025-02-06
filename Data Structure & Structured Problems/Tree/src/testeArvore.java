import Arvore.Arvore;
import Arvore.noArvore;

public class testeArvore {


    public static void main(String[] args) {

        Arvore x = new Arvore();

        x.inserir("44","ee");
        x.inserir("34","ee");
        x.inserir("23", "ee");
        x.inserir("55","ee");


        x.imprimirPreOrdem();;
        x.remover("34");
        System.out.println();
        x.imprimirPreOrdem();









    }
}


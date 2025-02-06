
public class Merge {

    private Fila<Integer> z;
    private int tamanho;



    public Merge() {

    }


    public Fila<Integer> opMerge(Fila<Integer> x, Fila<Integer> y) {
        z = new Fila<Integer>((x.tamanho+y.tamanho));
        while(!z.estaCheio()) {
            if(y.estaVazio()) {
                while(!x.estaVazio()) {
                    z.adicionar(x.retirar());
                }
            }else if(x.estaVazio()) {
                while(!y.estaVazio()) {
                    z.adicionar(y.retirar());
                }
            }else if(x.verificaPrimeiro() < y.verificaPrimeiro()) {
                z.adicionar(x.retirar());}else  {z.adicionar(y.retirar());}



        }
        return z;

    }





}

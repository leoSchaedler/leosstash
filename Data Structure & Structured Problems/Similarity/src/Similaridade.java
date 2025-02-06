public class Similaridade {


    ListaSE lista = new ListaSE();




    public double Similaridade(ListaSE x, ListaSE y) {
        return (produtoEscalar(x, y)/raiz(x, y));

    }



    public double produtoEscalar(ListaSE x, ListaSE y) {
        double p = 0;
        No i = x.getPrimeiro();
        No j = y.getPrimeiro();
        while(i !=null) {
            p+=(i.getDado()*j.getDado());
            i = i.getProximo();
            j = j.getProximo();
        }
        return p;

    }


    public double raiz(ListaSE x, ListaSE y) {
        double somaX = 0;
        double somaY = 0;
        No i = x.getPrimeiro();
        No j = y.getPrimeiro();
        while(i!=null) {
            somaX+=Math.pow(i.getDado(), 2);
            somaY+=Math.pow(j.getDado(), 2);
            i=i.getProximo();
            j=j.getProximo();
        }

        return (Math.sqrt(somaY * somaX));





    }








}

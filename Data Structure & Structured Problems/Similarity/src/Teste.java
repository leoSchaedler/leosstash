public class Teste {

    public static void main(String[] args) {


        ListaSE lista = new ListaSE();


        ListaSE x = new ListaSE();
        ListaSE y = new ListaSE();


        x.insereOrdenado(30);
        x.insereOrdenado(4);
        x.insereOrdenado(66);
        x.insereOrdenado(110);
        x.insereOrdenado(1);
        x.insereOrdenado(5);

        y.inserePrimeiro(3);
        y.inserePrimeiro(4);
        y.inserePrimeiro(11);
        y.inserePrimeiro(12);
        y.inserePrimeiro(55);
        y.inserePrimeiro(5);
        y.inserePrimeiro(1);
        System.out.println("Lista x: ");
        x.imprimir();
        System.out.println("Lista y: ");
        y.imprimir();



        Inter i = new Inter();
        ListaSE g = i.interseçao(x, y);
        System.out.println("Interseção: ");
        g.imprimir();
        System.out.println("----------------------");


        Similaridade s = new Similaridade();

        ListaSE a = new ListaSE();
        ListaSE b = new ListaSE();

        /*a.insereOrdenado(3);
        a.insereOrdenado(3);
        a.insereOrdenado(3);
        a.insereOrdenado(3);
        a.insereOrdenado(3);



        b.insereOrdenado(3);
        b.insereOrdenado(3);
        b.insereOrdenado(3);
        b.insereOrdenado(3);
        b.insereOrdenado(4);*/

        a.insereOrdenado(44);
        a.insereOrdenado(111);
        a.insereOrdenado(322);
        a.insereOrdenado(0);
        a.insereOrdenado(656);

        b.insereOrdenado(44);
        b.insereOrdenado(111);
        b.insereOrdenado(322);
        b.insereOrdenado(110);
        b.insereOrdenado(656);

        System.out.println("Lista a: ");
        a.imprimir();

        System.out.println("Lista b: ");
        b.imprimir();
        /*ListaSE invertida = b.inverte();
        invertida.imprimir();*/



        double f = s.Similaridade(a, b);
        System.out.printf("Similaridade = %.2f", f);







    }


}

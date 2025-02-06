public class Run {

    public static void main(String[] args) {

        /*Conversor x = new Conversor();
        String y;
        y = x.Converter(357);

        System.out.println(y);*/ //CONVERSOR LEO

        Pilha p = new Pilha(10);
        System.out.println("Exercicio 1");
        p.empilhar(p, 10);
        p.retornardados(p);
        p.empilhar(p, -2);
        p.retornardados(p);
        p.empilhar(p, 16);
        p.retornardados(p);
        p.achartopo(p);
        p.retornardados(p);
        p.desempilhar(p);
        p.retornardados(p);
        p.empilhar(p, 40);
        p.retornardados(p);
        p.desempilhar(p);
        p.retornardados(p);
        p.desempilhar(p);
        p.retornardados(p);
        p.desempilhar(p);
        p.retornardados(p);
        p.empilhar(p, 35);
        p.retornardados(p);
        System.out.println("");
        System.out.println("Conversor:");
        p.converte(12); // TAD PILHA RODRIGO
    }
}

public class teste {

    public static void main(String[] args) {


        Pilha stack = new Pilha(10);
        System.out.println(stack.top());
        stack.empilhar(3);
        stack.empilhar(2);
        stack.empilhar(4);
        stack.empilhar(22);
        stack.empilhar(55);
        System.out.println(stack.top());
        stack.desempilhar();
        System.out.println(stack.top());




        Expressao m = new Expressao();
        boolean x = m.match(")");
     //   boolean y = m.match("{[()[}");
        System.out.println(x);
      //  System.out.println(y);

    }

}
public class Expressao {


    private Pilha<Character> stack;
    private int tamanho;


    public Expressao() {
        tamanho = 100;
        stack = new Pilha<>(tamanho);

    }
    public Expressao(int x) {
        this.tamanho = x;
        stack = new Pilha<>(tamanho);

    }




    public  boolean match(String x) {
        for(int i = 0; i<x.length();i++) {

            if(x.charAt(i) == '(')
                stack.empilhar('(');
            else if(x.charAt(i) == '[')
                stack.empilhar('[');
            else if(x.charAt(i) == '{')
                stack.empilhar('{');


            else if(x.charAt(i) == ')') {
                if(stack.estaVazio() || !stack.top().equals('('))
                    return false;
                stack.desempilhar();
            }
            else if(x.charAt(i) == ']') {
                if(stack.estaVazio() || !stack.top().equals('['))
                    return false;
                stack.desempilhar();
            }
            else if(x.charAt(i) == '}') {
                if(stack.estaVazio()|| !stack.top().equals('{'))
                    return false;
                stack.desempilhar();
            }


        }
        if(stack.estaVazio()) {
           // stack = new Pilha<>(tamanho);
            return true;
        }
        //stack = new Pilha<>(tamanho);
        return false;
    }


}

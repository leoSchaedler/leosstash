package pilhaEstatica;

public class Test {

    public static void main(String[] args) {
        MyPilha stack = new MyPilha(10);


        //System.out.println(stack.top());
        stack.empilhar(10);

        stack.empilhar(-20);

        stack.empilhar(30);
        //System.out.println(stack.top());

        while(!stack.estaVazia()){
            System.out.println(stack.desempilhar());
        }
        //stack.desempilhar();
        //System.out.println(stack.top());
    }


}

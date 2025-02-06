import java.util.Stack;

public class Conversor {

    Stack pilha;
    private int numero;

    public Conversor(){
        pilha = new Stack();
    }

    public String Converter(int numero){
        this.numero = numero;
        String result = new String();
        while (numero!=0){
            pilha.push(numero%2);
            numero = numero/2;
        }
        while(!pilha.empty()){
            result += pilha.pop();
        }
        return result;
    }


}

package pilhaEstatica;

public class MyPilha<E> implements Pilha<E>{

    protected int MAX;  // Capacidade máxima da Pilha.
    protected E dado[]; // E é um tipo qualquer de objeto, generalização.
    protected int topo = -1; // Topo deve estar em -1 pois a primeira posição da pilha é 0 (elemento 1).



    public MyPilha() {
        MAX = 100;
    }

    public MyPilha(int x)
    {
        MAX = x;
        dado = (E[]) new Object[MAX];
    }

    public int tamanho() {
        return (topo+1);
    }

    public boolean estaVazia() {
        return (topo < 0);
    }
    public boolean estaCheia() {
        return (++topo == MAX);
    }

    public void empilhar(E elemento) { // Problema gerado pelo objeto genérico "E", caso fosse int estaria certo
        if (tamanho() == MAX)
            System.out.println("Pilha cheia");
        else {
            dado[++topo] = elemento;
        }
    }

    public E top() {
        if(estaVazia()) {
            System.out.println("Pilha vazia");
            return null;
        }
        return dado[topo];
    }


    public E desempilhar() {
        E elemento;
        if(estaVazia()) {
            System.out.println("Pilha vazia");
            return null;
        }
        elemento = dado[topo];
        dado[topo--] = null;
        return elemento;
    }

}

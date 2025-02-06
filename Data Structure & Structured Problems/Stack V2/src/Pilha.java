public class Pilha<E> {

    private int capacidade;
    private E S[];
    private int topo = -1;



    public Pilha() {
        capacidade = 100;
        S = (E[]) new Object[capacidade];
    }


    public Pilha(int x)
    {
        capacidade = x;
        S = (E[]) new Object[capacidade];
    }

    public int tamanho() {
        return (topo+1);
    }

    public boolean estaVazio() {
        return (topo < 0);
    }

    public void empilhar(E elemento) {
        if (tamanho() == capacidade)
            System.out.println("Pilha cheia");
        else {
            S[++topo] = elemento;
        }
    }

    public E top() {
        if(estaVazio()) {
            System.out.println("Pilha vazia");
            return null;
        }
        return S[topo];
    }


    public E desempilhar() {
        E elemento;
        if(estaVazio()) {
            System.out.println("Pilha vazia");
            return null;
        }
        elemento = S[topo];
        S[topo--] = null;
        return elemento;
    }







}

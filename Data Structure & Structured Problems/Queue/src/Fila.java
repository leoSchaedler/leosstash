
public class Fila<E> {


    protected final int capacidade;
    protected E S[];

    protected int inicio = 0;
    protected int fim = -1;
    protected int tamanho = 0;


    public Fila() {
        capacidade = 100;
        S = (E[]) new Object[capacidade];
    }

    public Fila(int capacidade) {
        this.capacidade = capacidade;
        S = (E[]) new Object[this.capacidade];
    }


    public boolean estaCheio() {
        return (tamanho == capacidade);
    }

    public boolean estaVazio() {
        return (tamanho == 0);
    }




    public void adicionar(E elemento) {
        if(estaCheio()) {
            System.out.println("Fila cheia");
        }else if(fim == (capacidade-1)){
            fim = 0;
            S[fim] = elemento;
            tamanho++;
        }
        else {
            S[++fim] = elemento;
            tamanho++;
        }

    }

    public E retirar() {
        if(estaVazio()) {
            System.out.println("Fila vazia");
            return null;
        }else if(inicio == (capacidade-1)) {
            E elemento;
            elemento = S[inicio];
            inicio = 0;
            tamanho--;
            return elemento;
        }
        tamanho--;
        return S[inicio++];

    }




    public E verificaPrimeiro() {
        if(estaVazio()) {
            System.out.println("Fila vazia");
            return null;}
        return S[inicio];

    }

    public E verificaUltimo() {
        if(estaVazio()) {
            System.out.println("Fila vazia");
            return null;}
        return S[fim];

    }



    public String toString() {
        String s = "[ ";
        for(E elemento: S)
            s+=elemento + " ";
        return s+="]";
    }
















}

















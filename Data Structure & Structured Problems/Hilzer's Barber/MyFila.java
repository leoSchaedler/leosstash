package barbeiroHilzer;

public class MyFila <E> {

    protected int capacidade;
    protected E F[];
    protected int inicio = 0;
    protected int fim = -1; // Semelhante ao topo da pilha
    protected int tamanho = 0; // Nº de elementos na fila

    public boolean estaVazia() {
        return (fim < 0);
    }

    public boolean estaCheia() {
        return (tamanho == capacidade);
    }

    public MyFila() {
        capacidade = 100;
        F = (E[]) new Object[capacidade];
    }

    public MyFila(int capacidade) {
        this.capacidade = capacidade;
        F = (E[]) new Object[capacidade];
    }

    public void adicionar(E elemento) {
        if (estaCheia()) {
            //System.out.println("Fila cheia");
        }
        else if(fim == (capacidade-1)){
            fim = 0;
            F[fim] = elemento;
            tamanho++;
        }
        else {
            F[++fim] = elemento;
            tamanho++;
        }
    }

    public E remover() {
        E elemento;
        if (estaVazia()) {
            //System.out.println("Fila vazia");
            return null;
        } else if(inicio == (capacidade-1)){
            inicio = 0;
            tamanho--;
            return F[inicio];
        }
        tamanho--;
        return F[inicio];
    }

    public E verificarInicio() {
        if (estaVazia()) {
            //System.out.println("Fila vazia");
            return null;
        }
        return F[inicio];
    }

    public E verificarFim() {
        if (estaCheia()) {
            //System.out.println("Fila cheia");
            return null;
        }
        else
            return F[fim];

    }
}

package pilhaEstatica;

public interface Pilha<E> {

    int tamanho();

    boolean estaVazia();
    boolean estaCheia();

    void empilhar(E elemento);

    E top();

    E desempilhar();
}

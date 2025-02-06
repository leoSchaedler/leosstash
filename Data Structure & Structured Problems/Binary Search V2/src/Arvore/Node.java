package Arvore;

public class Node <T extends Comparable<T>>{

    private T dado;
    private Node esquerda,direita;


    public Node() {
        esquerda = null;
        direita = null;
    }


    public Node(T dado) {
        this.dado = dado;
        esquerda = null;
        direita = null;
    }





    public T getDado() {
        return dado;
    }
    public void setDado(T dado) {
        this.dado = dado;
    }
    public Node getEsquerda() {
        return esquerda;
    }
    public void setEsquerda(Node esquerda) {
        this.esquerda = esquerda;
    }
    public Node getDireita() {
        return direita;
    }
    public void setDireita(Node direita) {
        this.direita = direita;
    }










}
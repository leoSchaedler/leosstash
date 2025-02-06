package Lista;

public class Node {

    private String arq;
    private int freq;
    private Node proximo;

    public Node(String arq, Node proximo) {
        this.arq = arq;
        freq = 1;
        this.proximo = proximo;
    }

    public int getFreq() {
        return freq;
    }

    public void addFreq() {
        freq++;
    }

    public void setArq(String arq) {
        this.arq = arq;
    }

    public String getArq() {
        return arq;
    }

    public void setProximo(Node proximo) {
        this.proximo = proximo;

    }

    public Node getProximo() {
        return proximo;
    }














}
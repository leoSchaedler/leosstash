package Grafo;

import java.util.LinkedList;

public class Vertice implements Comparable<Vertice> {

    protected String id;
    protected int index;
    protected LinkedList<Aresta> adj;
    protected boolean visitado;
    protected LinkedList<Vertice> caminho;
    protected double minDistancia = Double.POSITIVE_INFINITY;
    protected Vertice anterior;

    public int compareTo(Vertice other){
            return Double.compare(minDistancia,other.minDistancia);
        }


    public LinkedList<Vertice> getCaminho() {
        return caminho;
    }

    public LinkedList<Aresta> getAdj() {
        return adj;
    }


    public void setCaminho(LinkedList<Vertice> caminho) {
        this.caminho = caminho;
    }

    public double getMinDistancia() {
        return minDistancia;
    }

    public void setMinDistancia(double minDistancia) {
        this.minDistancia = minDistancia;
    }

    public Vertice getAnterior() {
        return anterior;
    }

    public void setAnterior(Vertice anterior) {
        this.anterior = anterior;
    }

    public Vertice(){
        this.id = String.valueOf(getClass().hashCode());
        adj = new LinkedList<Aresta>();
        caminho = new LinkedList<Vertice>();
    }

    public Vertice(int index){
        this.id = String.valueOf(getClass().hashCode());
        adj = new LinkedList<>();
        caminho = new LinkedList<>();
        this.index = index;
    }


    public boolean isVisitado() {
        return visitado;
    }

    public void setVisitado(boolean visitado) {
        this.visitado = visitado;
    }

    public Vertice(String id) {
        this.id = id;
        adj = new LinkedList<Aresta>();
        caminho = new LinkedList<Vertice>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void imprimi_adj(){
        System.out.print(this.id + " -> " );
        if(adj.isEmpty())
            System.out.print("{}");
        else
            for(Aresta a: adj)
                System.out.print("{\'" + a.getAdj().id + "\' | " + a.getPeso() + "}---");

        System.out.println();
    }


    public int getNAdj(){
        return adj.size();
    }


    public int nAdjVerticeVizinho(){
        int cont = 0;

        for (Aresta a : adj)
            cont += a.getAdj().getNAdj();

        return cont;
    }

    public boolean isAdj(Vertice v){
        for (Aresta a : adj)
            if (a.getAdj() == v)
                return true;
        return false;
    }

    public double getPeso(Vertice verticeAdjacente){
        for (Aresta a : adj)
            if (a.getAdj() == verticeAdjacente)
                return a.getPeso();
        return Double.POSITIVE_INFINITY;
    }
}

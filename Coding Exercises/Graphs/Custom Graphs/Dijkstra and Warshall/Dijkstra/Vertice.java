package br.pucpr.Dijkstra;

import java.util.LinkedList;

public class Vertice implements Comparable<Vertice>{

    private String id;
    protected LinkedList<Aresta> adj;
    private boolean visitado;
    public LinkedList<Vertice> caminho;
    public double minDistancia = Double.POSITIVE_INFINITY;
    public Vertice anterior;

    public int compareTo(Vertice other){
        return Double.compare(minDistancia,other.minDistancia);
    }


    public Vertice(){
        this.id = String.valueOf(getClass().hashCode());
        this.adj = new LinkedList<Aresta>();
        this.caminho = new LinkedList<Vertice>();
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
}

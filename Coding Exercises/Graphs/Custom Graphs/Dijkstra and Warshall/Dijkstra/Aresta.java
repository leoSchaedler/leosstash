package br.pucpr.Dijkstra;

public class Aresta {

    private Vertice adj;
    private double peso;


    public Aresta(Vertice adj, double peso) {
        this.adj = adj;
        this.peso = peso;
    }

    public Vertice getAdj() {
        return adj;
    }

    public void setAdj(Vertice adj) {
        this.adj = adj;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

}

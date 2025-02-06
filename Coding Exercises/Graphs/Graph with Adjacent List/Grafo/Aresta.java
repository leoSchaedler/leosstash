package br.pucpr.Grafo;

public class Aresta {

    private Vertice adj;
    private Vertice origem;
    private double peso;


    public Aresta(Vertice adj, Vertice origem,  double peso) {
        this.adj = adj;
        this.peso = peso;
        this.origem = origem;
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

    public Vertice getOrigem() {
        return origem;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

}

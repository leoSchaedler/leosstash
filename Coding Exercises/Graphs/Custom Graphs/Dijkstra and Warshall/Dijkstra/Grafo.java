package br.pucpr.Dijkstra;

import java.util.LinkedList;

public class Grafo {

    private final int TAM;
    protected Vertice[] vertices;

    public Grafo(int tam) {
        TAM = tam;
        vertices = new Vertice[TAM];
        for(int i=0;i<TAM;i++)
            vertices[i] = new Vertice();
    }

    public void cria_adjacencia(int i, int j, int p)
    {
        try {
            Vertice a = vertices[j];
            vertices[i].adj.add(new Aresta(a, p));
        }catch(IndexOutOfBoundsException e){
            System.out.println("Vertice inexistentes: " + e);
        }
    }

    public void remove_adjacencia(int i, int j)
    {
        try {
            Vertice r = vertices[j];
            for (Aresta v : vertices[i].adj)
                if (v.getAdj().getId() == r.getId())
                    vertices[i].adj.remove(v);
             }catch(IndexOutOfBoundsException e){
            System.out.println("Vertice inexistente: " + e);
        }
    }

    public int achaInd(Vertice x){
        for(int i=0; i<TAM; i++)
            if(vertices[i].getId() == x.getId())
                return i;
            return -1;
    }


    public void  imprime_adjacencias()
    {
        for(Vertice v: vertices)
            v.imprimi_adj();
    }

    public void seta_informacao(int i, String V)
    {
        try {
            vertices[i].setId(V);
        }catch(IndexOutOfBoundsException e){
            System.out.println("Vertice inexistente: " + e);
        }

    }

    public int adjacentes(int i, Vertice[] adj)
    {
        try{
            int size = vertices[i].adj.size();
            for(int k=0;k<size;k++){
                adj[k] = vertices[i].adj.get(k).getAdj();
            }
            return size;
            }
        catch(IndexOutOfBoundsException e) {
            System.out.println("Vertice inexistente: " + e);
        }
        return -1;
    }

    public double peso(int i, int j)
    {
        try{
            Vertice a = vertices[i];
            Vertice y = vertices[j];
            for(Aresta b: a.adj){
                if(b.getAdj() == y)
                    return b.getPeso();
            }

           } catch(IndexOutOfBoundsException e) {
            System.out.println("Vertice inexistente: " + e);
        }
        return -1;
    }

    public void resetarV(){
        for(int i=0; i<TAM; i++) {
            vertices[i].setVisitado(false);
            vertices[i].caminho = new LinkedList<Vertice>();
            vertices[i].minDistancia = Integer.MAX_VALUE;
            vertices[i].anterior = null;
        }
    }

    public int getTAM()
    {
        return TAM;
    }
}

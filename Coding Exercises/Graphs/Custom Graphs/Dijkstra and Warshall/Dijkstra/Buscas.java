package br.pucpr.Dijkstra;

import java.util.LinkedList;
import java.util.PriorityQueue;

public class Buscas {


    public static void Profundidade(Grafo G, Vertice a) {
        a.setVisitado(true);
        System.out.print(a.getId() + " >> ");
        for (Aresta n : a.adj) {
            if (!n.getAdj().isVisitado())
                Profundidade(G, n.getAdj());
        }
    }


    public static void Largura(Grafo G, Vertice a){

            LinkedList<Vertice> Q = new LinkedList<>();

            a.setVisitado(true);
            System.out.print(a.getId() + " >> ");
            Q.addFirst(a);

            while(!Q.isEmpty()){
                for(Aresta n : Q.getFirst().adj){
                    if(!n.getAdj().isVisitado()){
                        n.getAdj().setVisitado(true);
                        System.out.print(n.getAdj().getId() + " >> ");
                        Q.addFirst(n.getAdj());
                    }
                }
                Q.removeFirst();
            }
                G.resetarV();
        }


    private static void calculate(Vertice source){
        // Algo:
        // 1. Take the unvisited node with minimum weight.
        // 2. Visit all its neighbours.
        // 3. Update the distances for all the neighbours (In the Priority Queue).
        // Repeat the process till all the connected nodes are visited.
        source.minDistancia = 0;
        PriorityQueue<Vertice> queue = new PriorityQueue<Vertice>();
        queue.add(source);
        while(!queue.isEmpty()){
            Vertice u = queue.poll();
            for(Aresta neighbour:u.adj){
                Double newDist = u.minDistancia+neighbour.getPeso();
                if(neighbour.getAdj().minDistancia>newDist){
                    // Remove the node from the queue to update the distance value.
                    queue.remove(neighbour.getAdj());
                    neighbour.getAdj().minDistancia = newDist;
                    // Take the path visited till now and add the new node.s
                    neighbour.getAdj().caminho = new LinkedList<Vertice>(u.caminho);
                    neighbour.getAdj().caminho.add(u);
                    //Reenter the node with new distance.
                    queue.add(neighbour.getAdj());
                }
            }
        }
    }


    public static void dijkstra(Grafo G, int O, int D){
        calculate(G.vertices[O]);
        System.out.printf("Vertice %s para %s:\nDistancia =  %.2f\nCaminho = ",
                          G.vertices[O].getId(), G.vertices[D].getId(), G.vertices[D].minDistancia);
        for(Vertice pathvert:G.vertices[D].caminho) {
            System.out.print(pathvert.getId()+" -> ");
        }
        System.out.println(""+G.vertices[D].getId() + "\n");
        G.resetarV();
    }



































}





package Utilidade;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;

import Grafo.*;

public class Buscas {


    public static void Profundidade(Grafo G, Vertice a) {
        a.setVisitado(true);
        System.out.print(a.getId() + " >> ");
        for (Aresta n : a.getAdj()) {
            if (!n.getAdj().isVisitado())
                Profundidade(G, n.getAdj());
        }
    }

    public static void Busca_Profundidade(Grafo G, Vertice a, ArrayList<Vertice> v) {
        a.setVisitado(true);
        v.add(a);
        for (Aresta n : a.getAdj()) {
            if (!n.getAdj().isVisitado())
                Busca_Profundidade(G, n.getAdj(), v);
        }
    }


    public static void Largura(Grafo G, Vertice a){

        LinkedList<Vertice> Q = new LinkedList<>();

        a.setVisitado(true);
        System.out.print(a.getId() + " >> ");
        Q.addFirst(a);

        while(!Q.isEmpty()){
            for(Aresta n : Q.getFirst().getAdj()){
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
        source.setMinDistancia(0);
        PriorityQueue<Vertice> queue = new PriorityQueue<Vertice>();
        queue.add(source);


        while(!queue.isEmpty()){

            Vertice u = queue.poll();

            for(Aresta neighbour:u.getAdj()){
                Double newDist = u.getMinDistancia()+neighbour.getPeso();

                if(neighbour.getAdj().getMinDistancia()>newDist){
                    // Remove the node from the queue to update the distance value.
                    queue.remove(neighbour.getAdj());
                    neighbour.getAdj().setMinDistancia(newDist);

                    // Take the path visited till now and add the new node.s
                    neighbour.getAdj().setCaminho(new LinkedList<Vertice>(u.getCaminho()));
                    neighbour.getAdj().getCaminho().add(u);

                    //Reenter the node with new distance.
                    queue.add(neighbour.getAdj());
                }
            }
        }
    }


    public static void dijkstra(Grafo G, int O, int D){
        calculate(G.getVertices()[O]);
        System.out.printf("Vertice %s para %s:\nDistancia =  %.2f\nCaminho = ",
                G.getVertices()[O].getId(), G.getVertices()[D].getId(), G.getVertices()[D].getMinDistancia());
        for(Vertice pathvert:G.getVertices()[D].getCaminho())
            System.out.print(pathvert.getId()+" -> ");
        System.out.println(""+G.getVertices()[D].getId() + "\n");
        G.resetarV();
    }

    public static ArrayList<Vertice> dijkstraConexo(Grafo G, int O, int D){
        ArrayList<Vertice> caminho = new ArrayList<>();
        calculate(G.getVertices()[O]);
        for(Vertice pathvert : G.getVertices()[D].getCaminho())
            caminho.add(pathvert);
        caminho.add(G.getVertices()[D]);
        G.resetarV();
        return caminho;
    }

    public static boolean dijkstraCIntermediario(Grafo G, int Origem, int Destino, Vertice central){
        ArrayList<Vertice> caminho = new ArrayList<>();

        calculate(G.getVertices()[Origem]);
        for(Vertice pathvert : G.getVertices()[Destino].getCaminho())
            caminho.add(pathvert);
        caminho.add(G.getVertices()[Destino]);
        G.resetarV();

        for (int i = 1; i < caminho.size()-1; i++)
            if (central == caminho.get(i))
                return true;

        return false;
    }


    public static double dijkstraCPosicionamento(Grafo G, int Origem, int Destino){
        ArrayList<Vertice> caminho = new ArrayList<>();

        double acumuladorPeso = 0.0;

        calculate(G.getVertices()[Origem]);
        for(Vertice pathvert : G.getVertices()[Destino].getCaminho())
            caminho.add(pathvert);
        caminho.add(G.getVertices()[Destino]);
        G.resetarV();

        for (int i = 0; i < caminho.size()-1; i++)
            acumuladorPeso += caminho.get(i).getPeso(caminho.get(i+1));

        return acumuladorPeso;
    }



    public static void ProfundidadeEmail(Grafo G, Vertice a, Vertice ultimo) {
        a.setVisitado(true);
        System.out.print(a.getId() + " >> ");
        for (Aresta n : a.getAdj()) {
            if(!ultimo.isVisitado()){   //Criterio de parada
                if (!n.getAdj().isVisitado())
                    ProfundidadeEmail(G, n.getAdj(), ultimo);
            }
        }

    }




    public static void LarguraEmail(Grafo G, Vertice a, Vertice ultimo){

        LinkedList<Vertice> Q = new LinkedList<>();

        a.setVisitado(true);
        System.out.print(a.getId() + " >> ");
        Q.addFirst(a);

        while(!Q.isEmpty()){
            for(Aresta n : Q.getFirst().getAdj()){
                if(!ultimo.isVisitado()){   //Cria de parada
                    if(!n.getAdj().isVisitado()) {
                        n.getAdj().setVisitado(true);
                        System.out.print(n.getAdj().getId() + " >> ");
                        Q.addFirst(n.getAdj());
                    }
                }
            }

            Q.removeFirst();
        }

    }




}
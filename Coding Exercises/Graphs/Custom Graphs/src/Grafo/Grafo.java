package Grafo;


//import testes.Graph;

import Utilidade.Buscas;

import java.util.*;

public class Grafo  {



    public class ArestasComparator implements Comparator<Vertice> {

        @Override
        public int compare(Vertice o1, Vertice o2) {
            if(o1.adj.size() < o2.adj.size())
                return 1;
            else if(o1.adj.size() > o2.adj.size())
                return -1;
            return 0;
        }
    }



    private class ComparableEdge implements Comparable<ComparableEdge> {
        private double distance; // Distance from closest point on source to furthest point on edge
        private Aresta edge;

        private ComparableEdge(double distance, Aresta edge) {
            this.distance = distance;
            this.edge = edge;
        }

        @Override
        public int compareTo(ComparableEdge another) {
            return Double.compare(distance, another.distance);
        }
    }



    private class Subset {
        int parent, rank;
    };



    private final int TAM;
    protected Vertice[] vertices;
    protected ArrayList<Aresta> arestas;

    public Grafo(int tam) {
        TAM = tam;
        vertices = new Vertice[TAM];
        for(int i=0;i<TAM;i++)
            vertices[i] = new Vertice(i);
        arestas = new ArrayList<Aresta>();
    }










    public Vertice[] getVertices() {
        return vertices;
    }


    public void cria_adjacenciaIn(int i, int j, double p)
    {
        try {
            Vertice a = vertices[j];
            Vertice b = vertices[i];
            Aresta aux = new Aresta(a, b, p);
            vertices[i].adj.add(aux);
            arestas.add(aux);
        }catch(IndexOutOfBoundsException e){
            System.out.println("Vertice inexistentes: " + e);
        }
    }

    public Vertice getVertice(int index){
        return vertices[index];
    }

    private Vertice getVertice(ArrayList<Vertice> S){

        if (S.size() == 0)
            return this.vertices[0];

        for(Vertice v: this.vertices){

            for(int i=0;i <= S.size();i++)
                if(S.get(i).getId().equals(v.getId()))
                    break;
                return v;
            }
        return null;
    }

    public int nComponentes(){
        int i = 0;
        ArrayList<Vertice> S = new ArrayList<>();
        while (S.size() < this.TAM){
           Vertice inicial = getVertice(S);
            ArrayList<Vertice> v = new ArrayList<>();
            Utilidade.Buscas.Busca_Profundidade(this, inicial, v);
            i++;
            S.addAll(v);
            System.out.print("Componenete " + i + ": ");
            v.forEach(ver -> {
                System.out.print(ver.getId()+ " >> ");
            });
            System.out.println();
         }
        return i;
    }

    private boolean isCyclicUtil(Vertice i, boolean[] visited, boolean[] recStack) {
        // Mark the current node as visited and
        // part of recursion stack
        if (recStack[getIn(i)])
            return true;

        if (visited[getIn(i)])
            return false;

        visited[getIn(i)] = true;

        recStack[getIn(i)] = true;
        List<Aresta> children = i.getAdj();

        for (Aresta c: children)
            if (isCyclicUtil(c.getAdj(), visited, recStack))
                return true;

        recStack[getIn(i)] = false;

        return false;
    }

    public boolean isCyclic() {

        // Mark all the vertices as not visited and
        // not part of recursion stack
        boolean[] visited = new boolean[TAM];
        boolean[] recStack = new boolean[TAM];


        // Call the recursive helper function to
        // detect cycle in different DFS trees
        for (int i = 0; i < TAM; i++)
            if (isCyclicUtil(vertices[i], visited, recStack))
                return true;

        return false;
    }


    public boolean cria_adjacenciaIn(String k, String j){
        int a = -1;
        int b = -1;

        for(int i=0;i<vertices.length;i++)
            if(vertices[i].getId().equals(k))
                a = i;
        for(int i=0;i<vertices.length;i++)
            if(vertices[i].getId().equals(j))
                b = i;

        if(a>-1){
            for (Aresta v : vertices[a].adj)
                if (v.getAdj().getId().equals(j)) {
                    v.setPeso(v.getPeso() + 1);
                    return true;
                }
            if(b>-1)
                cria_adjacenciaIn(a, b, 1);
            return true;
        }
    return false;



    }


    public int numVertices(){
        return vertices.length;
    }

    public int numArestas(){
        int acc = 0;
        for(Vertice v: vertices)
            acc+=v.adj.size();
        return acc;
    }

    public Vertice maiorArestas(){
        int biggest = vertices[0].getNAdj();
        Vertice vertice = vertices[0];
        for (Vertice v : vertices)
            if (v.getNAdj() > biggest){
                biggest = v.getNAdj();
                vertice = v;
            }
        return vertice;
    }

    public Vertice[] grauSaida() {
        /////TODO////////////
        //      Mudar para 20////
        PriorityQueue<Vertice> queue = new PriorityQueue<Vertice>(vertices.length, new ArestasComparator());
        Vertice[] r = new Vertice[20];
        int ctrl = 0;

        for (Vertice v : vertices)
            queue.offer(v);

        while (ctrl < 20) {
            Vertice v = queue.poll();
            r[ctrl++] = v;
            System.out.println(v.getId() + ": " + v.getAdj().size());

        }
        return r;
    }


    public Vertice[] maiorGrauEntrada() {
        /////TODO////////////
        //      Mudar para 20////
        Vertice[] r = new Vertice[20];
        Hashtable<Vertice, Integer> table = new Hashtable<Vertice, Integer>();  //contador da lista de adj
        PriorityQueue<Vertice> queue = new PriorityQueue<Vertice>(vertices.length, new Comparator<Vertice>() {
            @Override
            public int compare(Vertice o1, Vertice o2) {
                if(table.get(o1) < table.get(o2))
                    return 1;
                else if(table.get(o1) > table.get(o2))
                    return -1;
                return 0;
            }
        });
        for(Vertice v: vertices)
            for(int i=0; i<v.getAdj().size(); i++){
                if (table.containsKey(v.adj.get(i).getAdj())) {
                    table.put(v.getAdj().get(i).getAdj(), table.get(v.getAdj().get(i).getAdj()) + 1);
                } else {
                    table.put(v.getAdj().get(i).getAdj(), 1);
                }
            }
        //Transfer as List and sort it
        ArrayList<Map.Entry<Vertice, Integer>> l = new ArrayList(table.entrySet());
        Collections.sort(l, new Comparator<Map.Entry<Vertice, Integer>>(){

            public int compare(Map.Entry<Vertice, Integer> o1, Map.Entry<Vertice, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }});

        for(int i=0; i<20;i++) {
            System.out.println(l.get(i).getKey().getId() + ": " + l.get(i).getValue());
            r[i] = l.get(i).getKey();
        }
        return r;
    }


    public void remove_adjacencia(int i, int j) {
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
            for(int k=0;k<size-1;k++){
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


    public int getIn(Vertice x){
        for(int i=0;i<TAM;i++)
            if(vertices[i].getId().equals(x.id))
                return i;
        return -1;
    }


    private static void calculate2(Vertice source){
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
                Double newDist = u.getMinDistancia()+(1/neighbour.getPeso());

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


    public void maiorCaminho(int O, int D){
        calculate2(this.getVertices()[O]);
        System.out.printf("Vertice %s para %s:\nDistancia =  %.2f\nCaminho = ",
                this.getVertices()[O].getId(), this.getVertices()[D].getId(), (this.getVertices()[D].getMinDistancia()) * 100);
        for(Vertice pathvert:this.getVertices()[D].getCaminho()) {
            System.out.print(pathvert.getId()+" -> ");
        }
        System.out.println(""+this.getVertices()[D].getId() + "\n");
        resetarV();



    }

    public boolean existeV(String x){
        for(Vertice v: vertices)
            if(v.getId().equals(x))
                return true;
        return false;
    }

    public int getTAM()
    {
        return TAM;
    }


    public void profundPorDistInterno(Vertice vertice, int dist, int cont, LinkedList visitados, List vertDist){
        visitados.add(vertice);
        //System.out.println(vertice.rotulo + "- ");
        if(cont == dist){
            vertDist.add(vertice);
        }
        Iterator<Aresta> i = vertice.getAdj().listIterator();
        while(i.hasNext()){
            Aresta n = i.next();
            if(!visitados.contains(n.getAdj())){
                cont++;
                profundPorDistInterno(n.getAdj(),dist,cont,visitados,vertDist);
                cont--;
            }
        }
    }


    public ArrayList<Vertice> profundPorDist(int v, int dist){
        Vertice n = vertices[v];
        LinkedList visitados = new LinkedList();
        ArrayList<Vertice> vertDist = new ArrayList();
        int cont = 0;
        profundPorDistInterno(n,dist,cont,visitados,vertDist);
        return vertDist;
    }



    public void kruskal(){

        for(Vertice v: vertices)
            for(Aresta a: v.getAdj())
                arestas.add(a);




        Aresta result[] = new Aresta[vertices.length];
        for (int i = 0; i < vertices.length; i++)
            result[i] = new Aresta(null, null, Double.POSITIVE_INFINITY);


       // Arrays.sort(arestas.toArray());
        Collections.sort(arestas);

        Subset subsets[] = new Subset[vertices.length];
        for (int i = 0; i < vertices.length; i++)
            subsets[i] = new Subset();

        for(int i = 0; i < vertices.length; i++){
            subsets[i].parent = i;
            subsets[i].rank = 0;
        }
        int contI = 0;
        int contE = 0;
        try {
            while (contE < vertices.length -1){
                Aresta proxAresta = arestas.get(contI++);
                int x = find(subsets, proxAresta.getOrigem().index);
                int y = find(subsets, proxAresta.getAdj().index);
                if (x != y){
                    result[contE++] = proxAresta;
                    Union(subsets, x, y);
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        for (int i = 0; i < contE; ++i)
            System.out.println(result[i].getOrigem().id + " - " + result[i].getAdj().id + ": " + result[i].getPeso());
    }

    private int find(Subset subsets[], int i) {
        if (subsets[i].parent != i)
            subsets[i].parent = find(subsets, subsets[i].parent);
        return subsets[i].parent;
    }
    private void Union(Subset subsets[], int x, int y) {
        int xroot = find(subsets, x);
        int yroot = find(subsets, y);

        if (subsets[xroot].rank < subsets[yroot].rank)
            subsets[xroot].parent = yroot;
        else if (subsets[xroot].rank > subsets[yroot].rank)
            subsets[yroot].parent = xroot;
        else {
            subsets[yroot].parent = xroot;
            subsets[xroot].rank++;
        }
    }



    public boolean isAdj(Vertice[] v){
        for(int i=0;i< v.length;i++)
            for(int j=0;j<v.length;j++)
                if(i != j)
                    if (!auxAdj(v[i], v[j]))
                        return false;
        return true;
    }

    public boolean auxAdj(Vertice x, Vertice y){
        for(Aresta a: x.getAdj())
            if(a.getAdj().getId() == y.getId())
            return true;
        return false;

    }

    public boolean isClick(Vertice[] V){
            if (!isAdj(V))
                return false;
        return true;
    }

    private boolean pertence(Vertice x, Vertice[] v){
        for (Vertice i : v)
            if (i == x)
                return true;
        return false;
    }

    public boolean clickMax(Vertice[] V){
        Vertice[] aux = new Vertice[V.length+1];
        for (Vertice i : vertices){
            if (!pertence(i, V)){
                System.arraycopy(V, 0, aux, 0, V.length);
                aux[V.length] = i;
                if (isClick(aux)){
                    return false;
                }
            }
        }
        return true;
    }

    public double caLocal(Vertice v, boolean isDirecionado){

        Vertice[] vizinhos  = new Vertice[v.getNAdj()];

        int cont = 0;

        for (int i = 0; i < vizinhos.length; i++)
            vizinhos[i] = v.getAdj().get(i).getAdj();

        for (int j = 0; j < vizinhos.length; j++)
            for (int k = j; k < vizinhos.length; k++)
                if (vizinhos[j].isAdj(vizinhos[k]))
                    cont++;

//        System.out.println("cont: " + cont);
//        System.out.println("(v.getNAdj() * (v.getNAdj()-1): " + (v.getNAdj() * (v.getNAdj()-1)));

        if (cont == 0 || (v.getNAdj() * (v.getNAdj()-1)) == 0)
            return 0.00;

        if (isDirecionado){
            double aux = (double) cont / (v.getNAdj() * (v.getNAdj()-1));
//            System.out.println("result: "+aux);
            return aux;
        }
        else{
            double mem = (double) (2* cont) / (v.getNAdj() * (v.getNAdj()-1));
//            System.out.println("result: "+mem);
            return mem;
        }
    }

    public double caMedio(boolean isDirecionado){
        double cont = 0;
        for (int i = 0; i < vertices.length; i++){
//            System.out.println("caMedio >> calculando... >> " + i);
            double aux = caLocal(getVertice(i), isDirecionado);
//            System.out.println(aux);
            cont += aux;
        }
//        System.out.println("count: " + cont);
//        System.out.println("n: " + vertices.length);
//        System.out.println(cont/vertices.length);
        return cont/vertices.length;
    }


    public boolean arestasImpares(){

        int v = 0;

        for (int i = 0; i < vertices.length; i++)
            if (vertices[i].getNAdj()%2 != 0)
                v++;

        return v == vertices.length;
    }

    public boolean isEuleriano(){

        int aux = 0;

        for (Vertice v : vertices)
            if (v.getNAdj()%2 != 0)
                aux++;

        return (aux == 0) || (aux == 2);
    }

    public boolean isConexo(){
        for (int i = 0; i < vertices.length; i++)
            for (int j = 0; j < vertices.length; j++)
                    if (i != j && Buscas.dijkstraConexo(this,i,j).size() <= 1)
                        return false;
        return true;
    }

    public double CentralidadeIntermediacao (Vertice vertice, boolean direcionado){

        int cont = 0;
        for (int i = 0; i < vertices.length; i++){
//            if (vertices[i].getNAdj() > 1)      //  objetivo  de otimizar o calculo, entretanto, parecem distorcer o resultado
            for (int j = 0; j < vertices.length; j++){
//                if (vertices[j].getNAdj() > 1)  //  objetivo  de otimizar o calculo, entretanto, parecem distorcer o resultado
//                    System.out.println("CentralidadeIntermediacao >> fazendo a busca ... -> [" + i + "," + j + "]");
                    if (Buscas.dijkstraCIntermediario(this,i,j,vertice))
                        cont++;
            }
        }

        if (cont == 0)
            return 0.00;

        int div = (vertices.length-1) * (vertices.length-2);
        if (direcionado)
            return (double) cont/ (double) div;
        return 2 * (cont/ (double)  div);
    }


    public double CentralidadePosicionamento (int vertice){

        double Edist = 0.0;

        for (int i = 0; i < vertices.length; i++){
//            System.out.println("CentralidadePosicionamento >> calculando ... >> " + i);
            Edist += Buscas.dijkstraCPosicionamento(this, vertice, i);
        }

        return Math.pow((Edist/(vertices.length-1)), -1);
    }

}

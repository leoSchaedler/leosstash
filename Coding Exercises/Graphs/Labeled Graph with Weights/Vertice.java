import java.util.LinkedList;

public class Vertice {

    private String id;
    protected LinkedList<Aresta> adj;


    public Vertice(){
        this.id = String.valueOf(getClass().hashCode());
        adj = new LinkedList<Aresta>();
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
package Lista;

public class Lista {

    private Node primeiro,ultimo;


    public Lista() {
        primeiro = null;
        ultimo = null;
    }


    public Node getPrimeiro() {
        return primeiro;
    }


    public void setPrimeiro(Node primeiro) {
        this.primeiro = primeiro;
    }


    public Node getUltimo() {
        return ultimo;
    }


    public void setUltimo(Node ultimo) {
        this.ultimo = ultimo;
    }

    public boolean vazia() {
        return (primeiro == null);
    }

    public void inserePrimeiro(String arq) {
        if(vazia()) {
            Node x = new Node(arq,null);
            primeiro = x;
            ultimo = x;

        }else {
            Node x = new Node(arq,primeiro);
            primeiro = x;
        }

    }

    public void insereUltimo(String arq) {
        if(vazia()) {
            inserePrimeiro(arq);

        }else {
            Node x = new Node(arq,null);
            ultimo.setProximo(x);
            ultimo = x;
        }


    }

    public void insereDepois(Node no ,String arq) {
        if(vazia()) {
            System.out.println("Lista vazia");
        }else {
            Node e = primeiro;
            while(e!=no) {
                e = e.getProximo();
            }
            Node novo = new Node(arq,e.getProximo());
            e.setProximo(novo);
        }

    }

    public void insereOrdenado(String arq) {
        if(vazia() || (primeiro.getArq().compareTo(arq) > 0)){              //.getDado()>=dado)) {
            inserePrimeiro(arq);
        }else if(ultimo.getArq().compareTo(arq) < 0){                //.getDado()<=dado){
            insereUltimo(arq);
        }else {
            Node p = primeiro;
            while(arq.compareTo(p.getProximo().getArq()) > 0)                  //dado>=p.getProximo().getDado())
                p = p.getProximo();
            insereDepois(p, arq);
        }


    }

    public Node retiraPrimeiro() {
        if(vazia()) {
            System.out.println("Lista vazia!");
            return null;
        }
        Node e = primeiro;
        primeiro = primeiro.getProximo();
        e.setProximo(null);
        return e;
    }

    public Node retiraUltimo() {
        if(vazia()) {
            System.out.println("Lista vazia!");
            return null;
        }
        Node p = primeiro;
        while(p.getProximo()!=ultimo) {
            p = p.getProximo();
        }
        Node e = p.getProximo();
        p.setProximo(null);
        return e;

    }

    public boolean existe(String arq){
        for (Node i = primeiro; i!=null; i = i.getProximo()){
            if (i.getArq().equals(arq))
                return true;
        }
        return false;
    }

    public void addFreq(String arq){
        for(Node i = primeiro; i!=null; i = i.getProximo()){
            if (i.getArq().equals(arq)){
                i.addFreq();
                break;
            }
        }
    }

    public void imprimir() {
        Node p;
        p = primeiro;
        String s = "[ ";
        while(p!=null) {
            System.out.println("Arquivo " + "\"" + p.getArq() + "\": " + p.getFreq());
            p = p.getProximo();
        }


    }

    public int total(){
        int x = 0;
        for(Node i = primeiro; i!=null; i = i.getProximo()){
            x+=i.getFreq();
        }
        return x;
    }




}
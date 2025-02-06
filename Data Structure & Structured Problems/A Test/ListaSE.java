public class ListaSE <T extends Comparable<T>>{
    public class Node <T extends Comparable<T>>{

        private T dado;
        private Node proximo;


        public Node(){
            proximo = null;
        }

        public Node (T dado){
            this.dado = dado;
            proximo = null;
        }

        public T getDado() {
            return dado;
        }

        public void setDado(T dado) {
            this.dado = dado;
        }

        public Node getProximo() {
            return proximo;
        }

        public void setProximo(Node proximo) {
            this.proximo = proximo;
        }
    }

    protected Node primeiro;
    protected Node ultimo;

    private int tamanho;

    public ListaSE(){
        primeiro = null;
        ultimo = null;
        tamanho = 0;
    }

    public int getTamanho(){
        return this.tamanho;
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

    public void inserirPrimeiro(T dado){
        Node novo = new Node(dado);
        if(primeiro==null){
            primeiro = novo;
            ultimo = novo;
        }else{
            novo.setProximo(primeiro);
            setPrimeiro(novo);
        }
        tamanho++;

    }

    public void inserirUltimo(T dado){
        Node novo = new Node(dado);
        if(primeiro==null)
            inserirPrimeiro(dado);
        else{
            ultimo.setProximo(novo);
            setUltimo(novo);
        }
        tamanho++;

    }

    public void inserirOrdenado(T dado){
        if(primeiro==null)
            inserirPrimeiro(dado);
        else if (existe(dado))
            return;
        else if(dado.compareTo((T)primeiro.getDado()) < 0)
            inserirPrimeiro(dado);
        else if(dado.compareTo((T)ultimo.getDado()) > 0)
            inserirUltimo(dado);
        else{
            Node novo = new Node(dado);
            Node atual = primeiro;
            while(atual.getProximo().getDado().compareTo(dado) < 0){
                atual =atual.getProximo();
            }
            novo.setProximo(atual.getProximo());
            atual.setProximo(novo);
        }
        tamanho++;


    }

    public void removerPrimeiro(){
        primeiro = primeiro.getProximo();
        tamanho--;
    }

    public void removerUltimo(){
        Node atual = primeiro;
        while (atual.getProximo()!=ultimo)
            atual=atual.getProximo();
        atual.setProximo(null);
        tamanho--;


    }

    public void removerElemento(T dado){
        if(primeiro.getDado() == dado)
            removerPrimeiro();
        else if(primeiro.getDado() == dado)
            removerUltimo();
        else if(!existe(dado))
            return;
        else{
            Node atual = primeiro;
            while (atual.getProximo().getDado()!=dado)
                atual = atual.getProximo();
            Node aux = atual.getProximo().getProximo();
            atual.getProximo().setProximo(null);
            atual.setProximo(aux);
        }
        tamanho--;
    }

    public T procurar(T dado){
        for(Node i=primeiro;i!=null;i=i.getProximo()){
            if(i.getDado() == dado)
                return (T) i.getDado();
        }
        return null;
    }

    public boolean existe(T dado){
        for(Node i=primeiro;i!=null;i=i.getProximo()){
            if(i.getDado() == dado)
                return true;
        }
        return false;
    }

    public void imprimir(){
        for(Node i=primeiro;i!=null;i=i.getProximo())
            System.out.print("{" + i.getDado() + "}-");
        System.out.println();
    }




}


public class ListaSE<E> {

    private No primeiro;
    private No ultimo;


    public ListaSE() {
        primeiro = null;
        ultimo = null;
    }




    public No getPrimeiro() {
        return primeiro;
    }




    public No getUltimo() {
        return ultimo;
    }




    public boolean vazia() {
        return (primeiro == null);
    }


    public void inserePrimeiro(int dado) {
        if(vazia()) {
            No x = new No(dado,null);
            primeiro = x;
            ultimo = x;

        }else {
            No x = new No(dado,primeiro);
            primeiro = x;
        }

    }

    public void insereUltimo(int dado) {
        if(vazia()) {
            inserePrimeiro(dado);

        }else {
            No x = new No(dado,null);
            ultimo.setProximo(x);
            ultimo = x;
        }


    }

    public void insereDepois(No noDefault, int dado) {
        if(vazia()) {
            System.out.println("Lista vazia");

        }else {
            No e = primeiro;
            while(e!= noDefault) {
                e = e.getProximo();
            }
            No novo = new No(dado,e.getProximo());
            e.setProximo(novo);

        }


    }

    public void insereOrdenado(int dado) {
        if(vazia() || (primeiro.getDado()>=dado)) {
            inserePrimeiro(dado);
        }else if(ultimo.getDado()<=dado){
            insereUltimo(dado);
        }else {
            No p = primeiro;
            while(dado>=p.getProximo().getDado())
                p = p.getProximo();
            insereDepois(p, dado);
        }


    }



    public int retiraPrimeiro() {
        if(vazia()) {
            System.out.println("Lista vazia!");
            return (Integer) null;
        }
        No e = primeiro;
        primeiro = primeiro.getProximo();
        e.setProximo(null);
        return e.getDado();
    }


    public int retiraUltimo() {
        if(vazia()) {
            System.out.println("Lista vazia!");
            return (Integer) null;
        }
        No p = primeiro;
        while(p.getProximo()!=ultimo) {
            p = p.getProximo();
        }
        No e = p.getProximo();
        p.setProximo(null);
        return e.getDado();


    }


    public int ultimoElemento() {
        return ultimo.getDado();
    }



    public ListaSE inverte(){
        ListaSE invertida = new ListaSE();
        No aux = primeiro;
        while (aux != null){
            invertida.inserePrimeiro(aux.getDado());
            aux = aux.getProximo();
        }
        return invertida;
    }

    public void imprimir() {
        No p;
        p = primeiro;
        String s = "[ ";
        while(p!=null) {
            s+=p.getDado() + " ";
            p = p.getProximo();
        }
        System.out.println(s + "]\n");



    }




}

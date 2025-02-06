public class ArvoreBinaria {


    public class Node {

        private int dado;
        private Node esquerda,direita;


        public Node() {
            esquerda = null;
            direita = null;
        }


        public Node(int dado) {
            this.dado = dado;
            esquerda = null;
            direita = null;
        }





        public int getDado() {
            return dado;
        }
        public void setDado(int dado) {
            this.dado = dado;
        }
        public Node getEsquerda() {
            return esquerda;
        }
        public void setEsquerda(Node esquerda) {
            this.esquerda = esquerda;
        }
        public Node getDireita() {
            return direita;
        }
        public void setDireita(Node direita) {
            this.direita = direita;
        }










    }

    private Node raiz;



    public ArvoreBinaria(){
        this.raiz = null;
    }

    public Node getRaiz(){
        return this.raiz;
    }

    public void inserir(int chave) {
        if (raiz == null) {
            raiz = new Node(chave);
        } else {
            Node atual = raiz, anterior;
            while (true) {
                if(chave == atual.getDado())
                    return;
                anterior = atual;
                if (chave < atual.getDado()) {
                    atual = atual.getEsquerda();
                     if (atual == null) {
                        anterior.setEsquerda(new Node(chave));
                        return;
                    }
                } else {
                    atual = atual.getDireita();

                    if (atual == null) {
                        anterior.setDireita(new Node(chave));
                        return;
                    }
                }
            }


        }


    }

    public void remover(int elemento) {
        Node remove = procurarInterno(elemento);
        Node pai = acharPai(remove);
        Node folha;


        if (folha(remove)) {
            if (remove.getDado() > pai.getDado()) {
                pai.setDireita(null);


            }else {
                pai.setEsquerda(null);

            }

        } else if (remove.getEsquerda() == null) {
            Node substituto = menorDireita(remove);
            substituto.setDireita(remove.getDireita());
            substituto.setEsquerda(null);
            if (remove.getDado() > pai.getDado()) {
                pai.setDireita(substituto);

            }
            else {
                pai.setEsquerda(substituto);

            }

        } else {
            Node substituto = maiorEsquerda(remove);
            substituto.setDireita(remove.getDireita());
            substituto.setEsquerda(null);
            if (remove.getDado() > pai.getDado()) {
                pai.setDireita(substituto);

            }
            else{
                pai.setEsquerda(substituto);

            }
        }


    }


    public void pesquisar(int elemento){
        Node no = procurarInterno(elemento);
       try {
           System.out.println("Elemento " + "\"" + no.getDado() + "\"" + " encontrado!!");
       }catch (NullPointerException e){
           System.out.println("Elemento " + "\"" + elemento + "\"" +" não encontrado!!");
       }

    }

    public Node procurar(int elemento) throws  NullPointerException{
        return procurarInterno(elemento);
    }

















    private Node procurarInterno(int elemento){
        if(raiz.getDado() == elemento)
            return raiz;
        Node p = raiz;
        while(p!=null){
            if(elemento > p.getDado())
                p = p.getDireita();
            else if(elemento < p.getDado())
                p = p.getEsquerda();
            else
                return p;
        }
        return null;
    }

    private Node acharPai(Node x){
        Node atual = raiz, pai;
        while(atual!=null){
            pai = atual;
            if(x.getDado() > atual.getDado()){
                atual = atual.getDireita();
                if(atual!=null)
                    if(atual.getDado() == x.getDado())
                        return pai;
            }else{
                atual = atual.getEsquerda();
                if(atual!=null)
                    if(atual.getDado() == x.getDado())
                        return pai;
            }

        }
        return null;

    }

    private Node maiorEsquerda(Node x){
        Node atual = x.getEsquerda(), anterior = null;
        while(atual!=null){
            anterior = atual;
            atual = atual.getDireita();
        }
        return anterior;
    }

    private Node menorDireita(Node x){
        Node atual = x.getDireita(), anterior = null;
        while(atual!=null){
            anterior = atual;
            atual = atual.getEsquerda();
        }
        return anterior;

    }

    public boolean folha(Node x){
        if(x.getDireita() == null && x.getEsquerda() == null)
            return true;
        return false;
    }

    private int altura(Node atual) {
        if (atual == null) {
            return -1;
        }

        if (atual.getEsquerda() == null && atual.getDireita() == null) {
            return 0;

        } else if (atual.getEsquerda() == null) {
            return 1 + altura(atual.getDireita());

        } else if (atual.getDireita() == null) {
            return 1 + altura(atual.getEsquerda());

        } else {
            return 1 + Math.max(altura(atual.getEsquerda()), altura(atual.getDireita()));
        }
    }



    public void imprimirPreOrdem() {
        imprimirPreOrdem(this.raiz);
    }

    public void imprimirPosOrdem(){
        imprimirPosOrdem(this.raiz);
    }

    public void imprimirInOrdem(){
        imprimirInOrdem(this.raiz);
    }

    private void imprimirPreOrdem(Node raiz) {
        if(raiz != null){
            System.out.print(raiz.getDado() + " ");
            imprimirPreOrdem(raiz.getEsquerda());
            imprimirPreOrdem(raiz.getDireita());
        }
    }

    private void imprimirPosOrdem(Node raiz) {
        if(raiz != null){
            imprimirPosOrdem(raiz.getEsquerda());
            imprimirPosOrdem(raiz.getDireita());
            System.out.print(raiz.getDado() + " ");
        }
    }

    private void imprimirInOrdem(Node raiz) {
        if(raiz != null){
            imprimirInOrdem(raiz.getEsquerda());
            System.out.print(raiz.getDado() + " ");
            imprimirInOrdem(raiz.getDireita());
        }
    }














}

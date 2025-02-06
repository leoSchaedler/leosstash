public class ArvoreAvl <T extends Comparable<T>>{

public class Node{
    T chave;
    int fb;
    Node esquerda;
    Node direita;
    Node pai;

    public Node(T chave, Node pai){
        this.chave = chave;
        this.pai = pai;
        esquerda = null;
        direita = null;
    }
    public Node(){
        this.pai = null;
        esquerda = null;
        direita = null;
    }

    public T getChave() {
        return chave;
    }

    public void setChave(T chave) {
        this.chave = chave;
    }

    public int getFb() {
        return fb;
    }

    public void setFb(int fb) {
        this.fb = fb;
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

    public Node getPai() {
        return pai;
    }

    public void setPai(Node pai) {
        this.pai = pai;
    }
}

    Node raiz;

    public ArvoreAvl(){
        raiz = null;
    }


    public void inserir(T elemento){
        if(raiz == null){
            raiz = new Node(elemento,null);
            raiz.fb = 0;
        }else{
            Node p = raiz, q = null;
            while(p!=null){
                q = p;
                if(elemento.compareTo(p.chave) > 0)
                    p = p.direita;
                else if (elemento.compareTo(p.chave) < 0)
                    p = p.esquerda;
                else
                    break;
            }
            if(elemento.compareTo(q.chave) > 0) {
                q.direita = new Node(elemento, q);
                atualizar(q.direita);
                balancear(q);
            }
            else if((elemento.compareTo(q.chave) < 0)) {
                q.esquerda = new Node(elemento, q);
                atualizar(q.esquerda);
                balancear(q);
            }else if(elemento.compareTo(q.chave) == 0)
                return;

        }

    }

    public void remover(T elemento) {
        Node aRemover = procurarInterno(elemento);
     //   Node pai = acharPai(remove);

        if (aRemover.esquerda == null && aRemover.direita == null) {
            if (aRemover.chave.compareTo(aRemover.pai.chave) > 0){
                aRemover.pai.direita = null;
                atualizar(aRemover.pai);
                balancear(aRemover.pai);

            }else {
                aRemover.pai.esquerda = null;
                atualizar(aRemover.pai);
                balancear(aRemover.pai);

            }

        } else if (aRemover.esquerda == null) {
            Node substituto = menorDireita(aRemover);
            substituto.direita = aRemover.direita; aRemover.direita.pai = substituto;
            substituto.esquerda = null;
            if (aRemover.chave.compareTo(aRemover.pai.chave) > 0){
                aRemover.pai.direita = substituto; substituto.pai = aRemover.pai;
                atualizar(substituto);
                balancear(substituto);

            }
            else {
                aRemover.pai.esquerda = substituto; substituto.pai = aRemover.pai;
                atualizar(substituto);
                balancear(substituto);

            }

        } else {
            Node substituto = maiorEsquerda(aRemover);
            substituto.direita = aRemover.direita; aRemover.direita.pai = substituto;
            substituto.esquerda = null;
            if (aRemover.chave.compareTo(aRemover.pai.chave) > 0){
                aRemover.pai.direita = substituto; substituto.pai = aRemover.pai;
                atualizar(substituto);
                balancear(substituto);

            }
            else{
                aRemover.pai.esquerda = substituto; substituto.pai = aRemover.pai;
                atualizar(substituto);
                balancear(substituto);

            }
        }

    }


    public boolean existe(T elemento){
        Node no = procurarInterno(elemento);
        if(no!=null)
            return true;
        return false;

    }

    public Node procurar(T elemento) throws  NullPointerException{
        return procurarInterno(elemento);
    }

    private Node procurarInterno(T elemento){
        if(raiz.chave == elemento)
            return raiz;
        Node p = raiz;
        while(p!=null){
            if(elemento.compareTo(p.chave) > 0)
                p = p.direita;
            else if(elemento.compareTo(p.chave) < 0)
                p = p.getEsquerda();
            else
                return p;
        }
        return null;
    }


    public void rotacionarDireita(Node x) {
        Node esquerda = x.getEsquerda();
        if (x.pai != null) {
            esquerda.pai = x.pai;
            x.esquerda = esquerda.direita;
            if (x.esquerda != null) {
                x.esquerda.pai = x;
            }
            esquerda.direita = x;
            x.pai = esquerda;
            if (esquerda.pai != null) {

                if (esquerda.pai.direita == x) {
                    esquerda.pai.direita = esquerda;

                } else if (esquerda.pai.esquerda == x) {
                    esquerda.pai.esquerda = esquerda;
                }
            }
        }
        atualizar(x);
        atualizar(esquerda);

    }
    public void rotacionarEsquerda(Node x) {
        Node direita = x.direita;
        if (x.pai != null) {
            x.direita.pai = x.pai;
            x.direita = direita.esquerda;
            if (x.direita != null) {
                x.direita.pai = x;
            }
            direita.esquerda = x;
            x.pai = direita;
            if (direita.pai != null) {

                if (direita.pai.direita == x) {
                    direita.pai.direita = direita;

                } else if (direita.pai.esquerda == x) {
                    direita.pai.esquerda = direita;
                }
            }
        }

        atualizar(x);
        atualizar(direita);

    }

    public void rotacionarDirEsq(Node x){
       rotacionarDireita(x.direita);
       rotacionarEsquerda(x);

    }
    public void rotacionarEsqDir(Node x){
        rotacionarEsquerda(x.esquerda);
        rotacionarDireita(x);
    }

    public void balancear(Node x){
        Node b = x;
        while (b.pai!=null){
            verificarBalanceamento(b);
            b = b.pai;
        }
        verificarBalanceamento(this.raiz);
    }

    public void verificarBalanceamento(Node x){
            if (x.fb == 2) {
                if (altura(x.esquerda.direita) >= altura(x.esquerda.esquerda)) {
                    rotacionarEsqDir(x);
                } else
                    rotacionarDireita(x);
            } else if (x.fb == -2) {
                if (altura(x.direita.esquerda) >= altura(x.direita.direita)) {
                    rotacionarDirEsq(x);
                } else
                    rotacionarEsquerda(x);
            }




    }

    public void atualizar(Node x){
        if(x!=null) {
            x.fb = altura(x.esquerda) - altura(x.direita);
            atualizar(x.pai);
        }
    }

    public int altura(Node atual) {
        if (atual == null) {
            return -1;
        }
        if (atual.esquerda == null && atual.direita == null) {
            return 0;
        } else if (atual.esquerda == null) {
            return 1 + altura(atual.direita);
        } else if (atual.direita == null) {
            return 1 + altura(atual.esquerda);
        } else {
            return 1 + Math.max(altura(atual.esquerda), altura(atual.direita));
        }
    }


    private Node maiorEsquerda(Node x){
        Node atual = x.esquerda, anterior = null;
        while(atual!=null){
            anterior = atual;
            atual = atual.direita;
        }
        return anterior;
    }

    private Node menorDireita(Node x){
        Node atual = x.direita, anterior = null;
        while(atual!=null){
            anterior = atual;
            atual = atual.esquerda;
        }
        return anterior;

    }





    public void imprimirPreOrdem(){
        preOrdem(this.raiz);
    }

    public void imprimirPosOrdem(){
        posOrdem(this.raiz);
    }

    public void imprimirInOrdem(){
        inOrdem(this.raiz);
    }



    private void preOrdem(Node x){
        if (x != null) {
            System.out.print(x.chave + " ");
            preOrdem(x.esquerda);
            preOrdem(x.direita);
        }

    }
    private void inOrdem(Node raiz) {
        if(raiz != null){
            inOrdem(raiz.getEsquerda());
            System.out.print(raiz.chave + " ");
            inOrdem(raiz.getDireita());
        }
    }
    private void posOrdem(Node raiz) {
        if(raiz != null){
            posOrdem(raiz.getEsquerda());
            posOrdem(raiz.getDireita());
            System.out.print(raiz.chave + " ");
        }
    }


}

package Arvore;

public class Arvore {

    private noArvore raiz;

    public Arvore() {
        raiz = null;
    }


    public noArvore getRaiz() {
        return this.raiz;
    }

    public void inserir(String palavra, String arquivo) {
        if (raiz == null) {
            raiz = new noArvore(palavra);
            raiz.setLista(arquivo);
        } else if (raiz.getPalavra().equalsIgnoreCase(palavra))
            raiz.setLista(arquivo);
        else {
            noArvore atual = raiz;
            noArvore anterior;
            while (true) {
                anterior = atual;
                if (palavra.compareToIgnoreCase(atual.getPalavra()) < 0) {
                    atual = atual.getEsquerda();
                    if (atual == null) {
                        anterior.setEsquerda(palavra, arquivo);
                        balancear(anterior);
                        return;
                    } else if (atual.getPalavra().equalsIgnoreCase(palavra)) {
                        atual.setLista(arquivo);
                        return;
                    }
                } else {
                    atual = atual.getDireita();
                    if (atual == null) {
                        anterior.setDireita(palavra, arquivo);
                        balancear(anterior);


                        return;
                    } else if (atual.getPalavra().equalsIgnoreCase(palavra)) {
                        atual.setLista(arquivo);
                        return;
                    }
                }
            }


        }


    }

    public void imprimirPreOrdem() {
        imprimirPreOrdem(this.raiz);
    }

    private void imprimirPreOrdem(noArvore raiz) {
        if (raiz != null) {
            raiz.imprimirNo();
            imprimirPreOrdem(raiz.getEsquerda());
            imprimirPreOrdem(raiz.getDireita());
        }
    }

    public void imprimirPosOrdem() {
        imprimirPosOrdem(raiz);
    }

    private void imprimirPosOrdem(noArvore raiz) {
        if (raiz != null) {
            imprimirPreOrdem(raiz.getEsquerda());
            imprimirPreOrdem(raiz.getDireita());
            raiz.imprimirNo();
        }
    }

    public void imprimirInOrdem() {
        imprimirInOrdem(raiz);
    }

    private void imprimirInOrdem(noArvore raiz) {
        if (raiz != null) {
            imprimirPreOrdem(raiz.getEsquerda());
            raiz.imprimirNo();
            imprimirPreOrdem(raiz.getDireita());

        }
    }

    public void remover(String x) {
        noArvore remove = procurarInterno(x);
        noArvore pai = acharPai(remove);
        if (folha(remove)) {
            if (remove.getPalavra().compareToIgnoreCase(pai.getPalavra()) > 0)
                pai.setDireita(null);
            else
                pai.setEsquerda(null);
        } else if (remove.getEsquerda() == null) {
            noArvore substituto = menorDireita(remove);
            substituto.setDireita(remove.getDireita());
            substituto.setEsquerda(null);
            if (remove.getPalavra().compareToIgnoreCase(pai.getPalavra()) > 0)
                pai.setDireita(substituto);
            else
                pai.setEsquerda(substituto);

        } else {
            noArvore substituto = maiorEsquerda(remove);
            substituto.setDireita(remove.getDireita());
            substituto.setEsquerda(null);
            if (remove.getPalavra().compareToIgnoreCase(pai.getPalavra()) > 0)
                pai.setDireita(substituto);
            else
                pai.setEsquerda(substituto);
        }

    }


    private void rotacionarDireita(noArvore raiz) {

       noArvore raizPai = acharPai(raiz);
        if(raizPai==null){
            noArvore p = raiz;
            noArvore sub = raiz.getEsquerda();
            noArvore subDir = sub.getDireita();
            if(subDir==null){
                this.raiz = sub;
                p.setEsquerda(null);
                this.raiz.setDireita(p);
            }else {
               this.raiz = sub;
               p.setEsquerda(subDir);
               this.raiz.setDireita(p);
            }

        }else {
            noArvore sub = raiz.getEsquerda();
            raizPai.setEsquerda(sub);
            sub.setDireita(raiz);
            raiz.setEsquerda(null);
        }


    }

    private void rotacionarEsquerda(noArvore raiz) {
        noArvore raizPai = acharPai(raiz);
        if(raizPai==null){
            noArvore p = raiz;
            noArvore sub = raiz.getDireita();
            noArvore subEsq = sub.getEsquerda();
            if(subEsq==null){
                this.raiz = sub;
                p.setDireita(null);
                this.raiz.setEsquerda(p);
            }else {
                this.raiz = sub;
                p.setDireita(subEsq);
                this.raiz.setEsquerda(p);
            }

        }else {
            noArvore sub = raiz.getEsquerda();
            raizPai.setEsquerda(sub);
            raiz.setEsquerda(null);
            sub.setDireita(raiz);

        };

    }



    public void fatorDeBalanceamento(noArvore x) {
        if (!folha(x)) {
                int tamanhoEsquerda;
                int tamanhoDireita;
                //////////////////////////////
                if(x.getEsquerda()==null)
                    tamanhoEsquerda = 0;
                else
                tamanhoEsquerda = altura(x.getEsquerda());
                /////////////////////////////
                if(x.getDireita() == null)
                    tamanhoDireita = 0;
                else
                tamanhoDireita = altura(x.getDireita());



            if (tamanhoEsquerda - tamanhoDireita < -1)
                rotacionarEsquerda(x);
            else if (tamanhoEsquerda - tamanhoDireita > 1)
                rotacionarDireita(x);
        }else
            return;
    }

    public void balancear(noArvore x){
        if(x!=null) {
            fatorDeBalanceamento(x);
            balancear(acharPai(x));
        }

    }



    private noArvore maiorEsquerda(noArvore x){
        noArvore atual = x.getEsquerda(), anterior = null;
        while(atual!=null){
            anterior = atual;
            atual = atual.getDireita();
        }
        return anterior;
    }

    private noArvore menorDireita(noArvore x){
        noArvore atual = x.getDireita(), anterior = null;
        while(atual!=null){
            anterior = atual;
            atual = atual.getEsquerda();
        }
        return anterior;

    }

    private noArvore acharPai(noArvore x){
        noArvore atual = raiz, pai;
        while(atual!=null){
            pai = atual;
            if(x.getPalavra().compareToIgnoreCase(atual.getPalavra()) > 0){
                atual = atual.getDireita();
                if(atual!=null)
                if(atual.getPalavra().equalsIgnoreCase(x.getPalavra()))
                    return pai;
            }else{
                atual = atual.getEsquerda();
                if(atual!=null)
                if(atual.getPalavra().equalsIgnoreCase(x.getPalavra()))
                    return pai;
            }

        }
            return null;

   }

    public boolean folha(noArvore x){
        if(x.getDireita() == null && x.getEsquerda() == null)
            return true;
        return false;
    }

    public int altura(noArvore raiz){
        if(raiz == null || folha(raiz))
            return 0;
        return Math.max(altura(raiz.getEsquerda()) + 1, altura(raiz.getDireita()) + 1);

    }

    public void pesquisar(String palavra){
        try {
            noArvore x = procurarInterno(palavra);
            System.out.printf("Total de ocorrências da palavra '%s': %d\n", x.getPalavra(), x.total());
            // System.out.println(x.getPalavra() + ": " + x.total());
            x.imprimirLista();
        }catch(NullPointerException e){
            System.out.println("Palavra não encontrada");
        }


    }

    private noArvore procurarInterno(String palavra){
        if(raiz.getPalavra().equalsIgnoreCase(palavra))
            return raiz;
        noArvore p = raiz;
        while(p!=null){
            if(palavra.compareToIgnoreCase(p.getPalavra()) > 0)
                p = p.getDireita();
            else if(palavra.compareToIgnoreCase(p.getPalavra()) < 0)
                p = p.getEsquerda();
            else
                return p;
        }
        return null;
    }





}

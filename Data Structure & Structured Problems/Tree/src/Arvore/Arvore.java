package Arvore;

public class Arvore {

    private noArvore raiz;

    public Arvore(){
        raiz = null;
    }


    public noArvore getRaiz(){
        return this.raiz;
    }

    public void inserir(String palavra, String arquivo){
        if(raiz == null){
            raiz = new noArvore(palavra);
            raiz.setLista(arquivo);
        }else if(raiz.getPalavra().equalsIgnoreCase(palavra))
            raiz.setLista(arquivo);
        else{
            noArvore atual = raiz;
            noArvore anterior;
            while(true){
                anterior = atual;
                if(palavra.compareToIgnoreCase(atual.getPalavra()) < 0) {
                    atual = atual.getEsquerda();
                    if (atual == null) {
                        anterior.setEsquerda(palavra,arquivo);
                        return;
                    }else if(atual.getPalavra().equalsIgnoreCase(palavra)) {
                        atual.setLista(arquivo);
                        return;
                    }
                }
                else{
                    atual = atual.getDireita();
                    if (atual == null){
                        anterior.setDireita(palavra, arquivo);
                        return;
                    }else if(atual.getPalavra().equalsIgnoreCase(palavra)){
                        atual.setLista(arquivo);
                        return;
                    }
                }
            }



        }


    }

    public void imprimirPreOrdem(){
        imprimirPreOrdem(this.raiz);
    }

    private void imprimirPreOrdem(noArvore raiz){
        if(raiz!=null){
            raiz.imprimirNo();
            imprimirPreOrdem(raiz.getEsquerda());
            imprimirPreOrdem(raiz.getDireita());
        }
    }

    public void imprimirPosOrdem(){
        imprimirPosOrdem(raiz);
    }

    private void imprimirPosOrdem(noArvore raiz){
        if(raiz!=null){
            imprimirPreOrdem(raiz.getEsquerda());
            imprimirPreOrdem(raiz.getDireita());
            raiz.imprimirNo();
        }
    }

    public void imprimirInOrdem(){
        imprimirInOrdem(raiz);
    }

    private void imprimirInOrdem(noArvore raiz){
        if(raiz!=null){
            imprimirPreOrdem(raiz.getEsquerda());
            raiz.imprimirNo();
            imprimirPreOrdem(raiz.getDireita());

        }
    }

    public void remover(String x){
        noArvore remove = procurarInterno(x);
        noArvore pai = acharPai(remove);
        if(folha(remove)){
            if(remove.getPalavra().compareToIgnoreCase(pai.getPalavra()) > 0)
                pai.setDireita(null);
            else
                pai.setEsquerda(null);
        }else{
            //  noArvore ptrEsquerdo = remove.getEsquerda(), ptrDireito = remove.getDireita();
            if(remove.getEsquerda() == null){
                noArvore sub = menorDireita(remove);
                noArvore paiSub = acharPai(sub);
                paiSub.setEsquerda(sub.getDireita());
                sub.setEsquerda(remove.getEsquerda());
                sub.setDireita(remove.getDireita());


            }else{
                noArvore sub = maiorEsquerda(remove);
                noArvore paiSub = acharPai(sub);
                paiSub.setDireita(sub.getEsquerda());
                sub.setDireita(remove.getDireita());
                sub.setEsquerda(remove.getEsquerda());

            }

        }


    }

    public void remover2(String elemento){
        noArvore remove = procurarInterno(elemento);
        noArvore pai = acharPai(remove);
        if(folha(remove)){
            if(remove.getPalavra().compareToIgnoreCase(pai.getPalavra()) > 0)
                pai.setDireita(null);
            else
                pai.setEsquerda(null);
        }else{

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
                if(atual.getPalavra().equalsIgnoreCase(x.getPalavra()))
                    return pai;
            }else{
                atual = atual.getEsquerda();
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
        return Math.max(altura(raiz.getEsquerda()), altura(raiz.getDireita()) + 1);

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
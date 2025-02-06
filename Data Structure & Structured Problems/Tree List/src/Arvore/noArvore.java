package Arvore;

import Lista.Lista;

public class noArvore {

    private String palavra;
    private Lista arquivos;
    private noArvore esquerda,direita;


    public noArvore() {
        esquerda = null;
        direita = null;
        arquivos = new Lista();
    }

    public noArvore(String palavra) {
        this.palavra = palavra;
        arquivos = new Lista();
        esquerda = null;
        direita = null;
    }

    public void imprimirNo(){
        System.out.print(palavra + " = ");
        getLista();
    }

    public int total(){
        return arquivos.total();
    }

    public void getLista(){
        arquivos.imprimir();
    }

    public void imprimirLista(){
        arquivos.imprimir();
    }

    public void setLista(String arquivo){
        if(!arquivos.existe(arquivo))
            arquivos.insereOrdenado(arquivo);
        else{
            arquivos.addFreq(arquivo);
        }
    }

    public noArvore getDireita(){
        return direita;
    }

    public void setDireita(String palavra,String arquivo){
        this.direita = new noArvore(palavra);
        this.direita.setLista(arquivo);
    }

    public void setDireita(noArvore no){
        this.direita = no;
    }

    public noArvore getEsquerda(){
        return esquerda;
    }

    public void setEsquerda(String palavra, String arquivo){
        this.esquerda = new noArvore(palavra);
        this.esquerda.setLista(arquivo);
    }

    public void setEsquerda(noArvore no){
       this.esquerda = no;
    }

    public String getPalavra() {
        return palavra;
    }

    public void setPalavra(String palavra) {
        this.palavra = palavra;
    }


}

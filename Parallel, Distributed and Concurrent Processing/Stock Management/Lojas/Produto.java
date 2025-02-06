package br.pucpr.Lojas;

public class Produto {

    private final String titulo;
    private final double preco;

    public Produto(String titulo, double preco){
        this.titulo = titulo;
        this.preco = preco;
    }

    public String getTitulo() {
        return titulo;
    }

    public double getPreco() {
        return preco;
    }

    public void imprimirProduto(){
        System.out.println("Titulo: " + titulo + "; Preço: R$" + preco);
    }
}

package br.pucpr.Lojas;

import java.util.*;

public class Categoria {

    private final Random rnd = new Random();

    private final String nome;
    private final ArrayList<Produto> produtos;
    private final int nProdutos;
    private final boolean existe;

    public Categoria (String nome, int nMaxProdutos){
        this.nome = nome;
        this.nProdutos = rnd.nextInt(nMaxProdutos-1)+2;
        this.produtos = new ArrayList<>();
        this.existe = true;
    }


    public int getnProdutos() {
        return nProdutos;
    }

    public Produto[] getProdutos() {
        Produto[] aux = new Produto[nProdutos];
        for (int i = 0; i < nProdutos; i++)
            aux[i] = produtos.get(i);
        return aux;
    }

    public String getNome() {
        return nome;
    }

    public boolean getExiste(){
        return existe;
    }

    public void addProduto(String titulo, double preco){
        produtos.add(new Produto(titulo, preco));
    }
}

package br.pucpr.Lojas;


public class Catalogo {

    //Categorias
    private final Categoria[] categorias;
    public static final int nMaxCategorias = 5;

    public Catalogo(){
        this.categorias = new Categoria[nMaxCategorias];

        popularCategorias();
    }

    private void popularCategorias(){
        //Comida
        categorias[0] = new Categoria("comidas", 27);
        categorias[0].addProduto("Arroz 1Kg", 10.99);
        categorias[0].addProduto("Feijão 5Kg", 22.99);
        categorias[0].addProduto("Amendoim Crocante", 8.90);
        categorias[0].addProduto("Batata Chips", 6.99);
        categorias[0].addProduto("Batata Palha", 5.99);
        categorias[0].addProduto("Castanha do Pará 500g", 9.99);
        categorias[0].addProduto("Cracker", 4.99);
        categorias[0].addProduto("Ovos 12Un.", 9.00);
        categorias[0].addProduto("Cereal Matinal", 4.90);
        categorias[0].addProduto("Margarina Com Sal", 5.99);
        categorias[0].addProduto("Margarina Sem Sal", 6.50);
        categorias[0].addProduto("Lentilha 600g", 13.90);
        categorias[0].addProduto("Arroz 1Kg", 10.99);
        categorias[0].addProduto("Queijo Minas 500g", 11.99);
        categorias[0].addProduto("Pão de Sal 5Un.", 1.50);
        categorias[0].addProduto("Pão de Forma Un.", 3.50);
        categorias[0].addProduto("Lasanha", 24.99);
        categorias[0].addProduto("Nuggets", 14.90);
        categorias[0].addProduto("Kibe Congelado", 16.99);
        categorias[0].addProduto("Pizza Congelada", 29.49);
        categorias[0].addProduto("Lasanha Congelada", 24.99);
        categorias[0].addProduto("Maçã Kg", 4.99);
        categorias[0].addProduto("Laranja Kg", 3.99);
        categorias[0].addProduto("Uva Kg", 7.99);
        categorias[0].addProduto("Manga Kg", 6.99);
        categorias[0].addProduto("Morango Kg", 9.99);
        categorias[0].addProduto("Iogurte Integral 4Un.", 6.74);

        //Bebida
        categorias[1] = new Categoria("bebidas", 15);
        categorias[1].addProduto("Suco de Uva Integral 1L", 34.99);
        categorias[1].addProduto("Suco de Laranja Integral 1L", 14.99);
        categorias[1].addProduto("Cerveja 350ml", 2.49);
        categorias[1].addProduto("Cerveja sem álcool 350ml", 3.49);
        categorias[1].addProduto("Chá Verde 12Un.", 12.45);
        categorias[1].addProduto("Chá de Boldo 12Un.", 8.10);
        categorias[1].addProduto("Vinho Tinto Catuxa 800ml", 346.00);
        categorias[1].addProduto("Uísque Envelhecido 1,2L", 999.99);
        categorias[1].addProduto("Vodka 1,5L", 9.99);
        categorias[1].addProduto("Suco de Maçã 2L", 25.74);
        categorias[1].addProduto("Refrigerante de Cola 600ml", 3.50);
        categorias[1].addProduto("Refrigerante de Guaraná 600ml", 4.50);
        categorias[1].addProduto("Leite Caixinha 1L", 5.00);
        categorias[1].addProduto("Leite Pacote 1L", 8.50);
        categorias[1].addProduto("Energético 700ml", 6.50);
        categorias[1].addProduto("Energético 1,5L", 9.99);


        //Produto de Limpeza
        categorias[2] = new Categoria("produtos de limpeza", 9);
        categorias[2].addProduto("Multiuso", 2.99);
        categorias[2].addProduto("Detergente", 6.99);
        categorias[2].addProduto("Água Sanitária", 12.45);
        categorias[2].addProduto("Sabão em Barra", 3.90);
        categorias[2].addProduto("Sabão em Pó", 9.99);
        categorias[2].addProduto("Sabão Líquido", 13.78);
        categorias[2].addProduto("Amaciante", 10.90);
        categorias[2].addProduto("Desengordurante", 7.99);
        categorias[2].addProduto("Lustra Móveis", 14.90);

        //Roupas
        categorias[3] = new Categoria("roupas", 18);
        categorias[3].addProduto("Blusa lisa azul", 54.99);
        categorias[3].addProduto("Blusa listrada vermelha", 60.99);
        categorias[3].addProduto("Camisa social cinza", 80.90);
        categorias[3].addProduto("Camisa social roxa", 89.90);
        categorias[3].addProduto("Camiseta banda", 50.00);
        categorias[3].addProduto("Camiseta herói", 70.00);
        categorias[3].addProduto("Bermuda camuflada", 49.90);
        categorias[3].addProduto("Bermuda tan", 45.99);
        categorias[3].addProduto("Bermuda jeans", 52.99);
        categorias[3].addProduto("Calça jeans", 220.49);
        categorias[3].addProduto("Calça jeans preta", 199.90);
        categorias[3].addProduto("Saia amarela", 120.45);
        categorias[3].addProduto("Minissaia rosa", 99.90);
        categorias[3].addProduto("Vestido verde", 346.00);
        categorias[3].addProduto("Meias brancas", 25.74);
        categorias[3].addProduto("Meias pretas cano baixo", 19.50);
        categorias[3].addProduto("Boné aba reta", 119.99);
        categorias[3].addProduto("Boné trucker", 89.90);

        //Eletronicos
        categorias[4] = new Categoria("eletronicos", 13);
        categorias[4].addProduto("Forno Elétrico", 340.99);
        categorias[4].addProduto("Microondas", 147.98);
        categorias[4].addProduto("Liquidificador", 95.98);
        categorias[4].addProduto("Torradeira", 59.78);
        categorias[4].addProduto("Ferro Elétrico", 89.78);
        categorias[4].addProduto("Grill", 60.90);
        categorias[4].addProduto("Airfryer", 499.99);
        categorias[4].addProduto("Caixas de som", 120.99);
        categorias[4].addProduto("Home theater", 600.00);
        categorias[4].addProduto("Notebook", 3200.00);
        categorias[4].addProduto("Tablet", 1400.00);
        categorias[4].addProduto("Smartphone", 2000.00);
        categorias[4].addProduto("Fone de ouvido", 50.00);
        categorias[4].addProduto("Fone de ouvido bluetooth", 120.00);
    }

    public String[] getCategorias(){
        String[] aux = new String[nMaxCategorias];
        for (int i = 0; i < nMaxCategorias; i++){
            aux[i] = categorias[i].getNome();
        }
        return aux;
    }

    public String getStringao(String data){
        StringBuilder stringao = new StringBuilder();
        for (Categoria c : categorias)
            if (c.getNome().equals(data) && c.getExiste()){
                int n = c.getnProdutos();
//                if (n > 1) n--;
                stringao.append(n).append(";");
                for (Produto p : c.getProdutos())
                    stringao.append(p.getTitulo()).append(",").append(p.getPreco()).append(";");
            } else if (!c.getExiste())
                return "produtos_nao_existentes";
        return String.valueOf(stringao);
    }


    public boolean existe(String data){
        for (Categoria s : categorias)
            if (s.getNome().equals(data))
                return true;
        return false;
    }

}

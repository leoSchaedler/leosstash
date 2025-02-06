package br.pucpr.Auxiliares;

import br.pucpr.Servidor.ProtocoloSave.Data;

import java.util.ArrayList;

public class Tabelas {

    public static void produtosTable(String stringao){
        String[] aux = stringao.split(";");
        String[] f = new String[aux.length-1];
        for (int i = 0; i < aux.length-1; i++){
            f[i] = aux[i];
        }
        int bigger = 0;
        for (String s : f){
            int temp = getMaior(s.split(","));
            if (bigger < temp)
                bigger = temp;
        }
        //Infos
        String[] infos = f[0].split(",");
        System.out.println("Nome Loja: " + infos[0]);
        System.out.println("N° de produtos: " + (f.length-1));
        //Cabeçalho
        String titulo01 = "Titulo";
        String titulo02 = "Preço";
        int espacamento = bigger;
        System.out.print(titulo01);
        for (int i = 0; i < espacamento; i++) System.out.print(" ");
        System.out.print(titulo02);
        System.out.println();
        //Dados
        for (int s = 1; s < f.length; s++){
            String[] mem = f[s].split(",");
            System.out.print(mem[0]);
            for (int i = 0; i < getespaçamento(mem[0], espacamento+titulo01.length()); i++) System.out.print(" ");
            System.out.print("R$"+mem[1]);
            System.out.println();
        }
        System.out.println();
    }

    public static void historicoTables(String stringao){
        if (stringao.equals("Nenhum dado foi registrado até o momento")){
            System.out.println("Nenhum dado foi registrado até o momento");
            return;
        }
        //Cabeçalho
        String titulo01 = "Dia/Hora";
        int espacamento01 = 25;
        String titulo02 = "Identificador";
        int espacamento02 = 10;
        String titulo03 = "Texto Parcial";
        int espacamento03 = 10;
        String titulo04 = "Quantidade de Produtos";
        System.out.print(titulo01); for (int i = 0; i < espacamento01; i++) System.out.print(" ");
        System.out.print(titulo02); for (int i = 0; i < espacamento02; i++) System.out.print(" ");
        System.out.print(titulo03); for (int i = 0; i < espacamento03; i++) System.out.print(" ");
        System.out.print(titulo04);
        System.out.println();
        //Dados
        ArrayList<Data> mem = new ArrayList<>();
        if (stringao.length() != 0){
            String[] billy = stringao.split(";");
            for (String s : billy){
                String[] aux = s.split(",");
                mem.add(new Data(aux[0], Integer.parseInt(aux[1]), aux[2], Integer.parseInt(aux[3])));
            }
        }
        for (Data d : mem){
            System.out.print(d.getDiahora());       for (int i = 0; i < getespaçamento(d.getDiahora(), espacamento01+titulo01.length()); i++) System.out.print(" ");
            System.out.print(d.getId());            for (int i = 0; i < getespaçamento(String.valueOf(d.getId()), espacamento02+titulo02.length()); i++) System.out.print(" ");
            System.out.print(d.getTextoParcial());  for (int i = 0; i < getespaçamento(d.getTextoParcial(), espacamento03+titulo03.length()); i++) System.out.print(" ");
            System.out.print(d.getnProdutos());
            System.out.println();
        }
        System.out.println();
    }



    private static int getMaior(String[] s){
        int bigger = s[0].length();
        for (String i : s)
            if (i.length() > bigger && !i.equals(" "))
                bigger = i.length();
        return bigger;
    }

    private static int getespaçamento(String word, int espaçamento){
        return espaçamento - word.length();
    }


}

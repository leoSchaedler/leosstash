package grafo;

import java.util.ArrayList;
import java.util.Arrays;

public class Grafo {

    class Node{
        private String rotulo;

        public String getRotulo(){
            return this.rotulo;
        }
    }

     private static double inf = Double.POSITIVE_INFINITY;
     int linha;
     int coluna;
     double matriz[][];
     Node[] rotulos;


     public Grafo(int linhaColuna) throws Exception{
         if(linhaColuna==0)
            throw new Exception("Valor invalido");
         this.linha = linhaColuna;
         this.coluna = linhaColuna;
         rotulos = new Node[linhaColuna];
         matriz = new double[linhaColuna][linhaColuna];

         for(int i=0;i<linhaColuna;i++) {
             rotulos[i] = new Node();
             for (int j = 0; j < linhaColuna; j++)
                 matriz[i][j] = inf;
         }
     }


     public void cria_adjacencia(int i, int j, double P){
         matriz[i][j] = P;
     }

     public void remove_adjacencia(int i, int j){
         matriz[i][j] = inf;
     }

     public void imprimi_adjacencias(){
         int linha = matriz.length;
         int coluna = matriz[0].length;
         String str = "|\t";

         for(int i=0;i<linha;i++){
             for(int j=0;j<coluna;j++){
                 if(matriz[i][j] == inf)
                     str += "inf" + "\t\t";
                 else
                 str += matriz[i][j] + "\t";
             }

             System.out.println(str + "|");
             str = "|\t";
         }
     }

     public void set_inf(int i, String V){
        rotulos[i].rotulo = V;
     }


     public int adjacentes(int i, Node[] adj){
         int acc = 0;
         ArrayList<Node> myList = new ArrayList<Node>();
         for (int j=0; j< rotulos.length;j++)
             if(matriz[i][j]!=inf) {
                 acc++;
                 myList.add(rotulos[j]);
             }

             for(int k=0;k<acc;k++)
                adj[k] = myList.get(k);


         return acc;
     }








}


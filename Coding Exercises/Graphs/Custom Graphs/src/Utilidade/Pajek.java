package Utilidade;

import Grafo.*;

import java.io.*;
import java.nio.Buffer;

public class Pajek {
    FileWriter arqG;
//    FileReader arqL;
    PrintWriter gravarArq;
    BufferedReader lerArq;



public void Gravar(Grafo G, boolean direcionado) throws IOException {

    BufferedWriter gravarArq = new BufferedWriter(new FileWriter(new File("grafo.txt")));

    gravarArq.write("*Vertices " + G.numVertices());
    gravarArq.newLine();

    for(int i=0; i<G.getTAM();i++){
        gravarArq.append(String.valueOf(i)).append(" \"").append(G.getVertices()[i].getId()).append("\"");
        gravarArq.newLine();
    }

    gravarArq.newLine();

    if (direcionado)
        gravarArq.append("*Arcs");
    else
        gravarArq.append("*Edges");
    gravarArq.newLine();

    for(int i=0; i<G.getTAM();i++)
        for(int j=0; j<G.getVertices()[i].getAdj().size(); j++) {
            gravarArq.append(String.valueOf(i)).append(" ").append(String.valueOf(G.getIn(G.getVertices()[i].getAdj().get(j).getAdj())))
                    .append(" ").append(String.valueOf(G.getVertices()[i].getAdj().get(j).getPeso()));
            gravarArq.newLine();
        }
    gravarArq.close();
}


//TODO
// Ler o peso como double
public Grafo ler(String path) throws IOException {
//    arqL = new FileReader(path);
//    BufferedReader lerArq = new BufferedReader(arqL);

    BufferedReader lerArq = new BufferedReader(new FileReader(new File(path)));

    int i;
    String[] s;
    String linha = lerArq.readLine();   //linha1

//    System.out.println(linha);

    s = linha.split(" ");

    Grafo g = new Grafo(Integer.parseInt(s[1]));

    i = 0;
    while(i < g.getTAM()){
        linha = lerArq.readLine();      //linha2 -> 9
        s = linha.split(" ");
        g.seta_informacao(Integer.parseInt(s[0]), readName(s[1]));
        i++;
    }

//    System.out.println(g.getTAM() + " vertices criados com sucesso");

              //linha10
    lerArq.readLine();
//    System.out.println();

    String[] dic = lerArq.readLine().split(" "); //linha11;
    boolean t = true;
    if (dic[0].equals("*Arcs")){    //direcionado
        while(t){
//        while(!linha.equals("end")){
            linha = lerArq.readLine();
            try {
                String[] mem = linha.split(" ");
                g.cria_adjacenciaIn(Integer.parseInt(mem[0]), Integer.parseInt(mem[1]), Double.parseDouble(mem[2]));
//                System.out.println("Adjacencia " + mem[0] + " -> " + mem[1] + " criada");
            } catch (NullPointerException e){
//                e.printStackTrace();
                t = false;
            } catch (NumberFormatException numberFormatException){
//                numberFormatException.printStackTrace();
                t = false;
            }
        }
    }else if (dic[0].equals("*Edges")){ //nao direcionado
//        while(!linha.equals("end")){
        while(t){
            linha = lerArq.readLine();
            try {
                String[] mem = linha.split(" ");
                g.cria_adjacenciaIn(Integer.parseInt(mem[0]), Integer.parseInt(mem[1]), Double.parseDouble(mem[2]));
                g.cria_adjacenciaIn(Integer.parseInt(mem[1]), Integer.parseInt(mem[0]), Double.parseDouble(mem[2]));
//                System.out.println("Adjacencia " + mem[0] + " <-> " + mem[1] + " criada");
            } catch (NullPointerException e){
                t = false;
            } catch (NumberFormatException numberFormatException){
//                numberFormatException.printStackTrace();
                t = false;
            }
        }
    }
    else
        System.out.println("Comando não identificado");

    lerArq.close();

    return g;
}


    private static double readDouble(String info){
        int p1;
        double p2;
        String[] s = info.split(",");
        p1 = Integer.parseInt(s[0]);
        p2 = (double) Integer.parseInt(s[1])/100;
        return p1+p2;
    }

    private static String readName(String nameDurty){

        StringBuilder sb = new StringBuilder();

        for (int i = 1; i < nameDurty.length()-1; i++)
            sb.append(nameDurty.charAt(i));

        return new String(sb);
    }

}

package br.pucpr;

import br.pucpr.Grafo.Grafo;
import br.pucpr.Grafo.Vertice;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;


public class Main {

        public static int ptr = 0;




        public static void main (String[]args) throws IOException {

        Grafo G;
        BufferedReader read;

        String[] linha;
        File arquivos[];
        File aux[];
        File temp;
        File diretorio = new File("C:\\Users\\hrsch\\Área de Trabalho\\Amostra Eron\\maildir");

        arquivos = diretorio.listFiles();


        G = new Grafo(7750);
        String s = "";
        for (int i = 0; i < arquivos.length; i++) {
            System.out.println(i);
            temp = new File(arquivos[i].getAbsolutePath() + "\\sent");
            if (temp.exists()) {
                aux = temp.listFiles();
                read = new BufferedReader(new FileReader(new File(aux[0].getAbsolutePath())));
                s = lerDe(read);
                G.seta_informacao(ptr++, s);
            } else {
                temp = new File(arquivos[i].getAbsolutePath() + "\\_sent_mail");
                if (temp.exists()) {
                    aux = temp.listFiles();
                    read = new BufferedReader(new FileReader(new File(aux[0].getAbsolutePath())));
                    s = lerDe(read);
                    G.seta_informacao(ptr++, s);
                }
            }
        }


        for (int k = 0; k < arquivos.length; k++) {   //File a: arquivos) {
            String a = "", b = "";

            temp = new File(arquivos[k].getAbsolutePath() + "\\sent");
            if (temp.exists()) {
                aux = temp.listFiles();
                for (int i = 0; i < aux.length; i++) {
                    System.out.println("k: " + i);
                    read = new BufferedReader(new FileReader(new File(aux[i].getAbsolutePath())));
                    a = lerDe(read);

                    b = lerPara(read);
                    if(b.contains(",")){
                        linha = b.split(",");
                        for(String l: linha){
                            if(!G.existeV(b))
                                G.seta_informacao(ptr++,b);
                            G.cria_adjacenciaIn(a,b);

                        }

                    }
                    if (!G.existeV(b))
                        G.seta_informacao(ptr++, b);
                    G.cria_adjacenciaIn(a, b);
                }


            } else {
                temp = new File(arquivos[k].getAbsolutePath() + "\\_sent_mail");
                if (temp.exists()) {
                    aux = temp.listFiles();
                    for (int i = 0; i < aux.length; i++) {
                        System.out.println("else: " + i);
                        read = new BufferedReader(new FileReader(new File(aux[i].getAbsolutePath())));
                        a = lerDe(read);
                        b = lerPara(read);
                        if(b.contains(",")){
                            linha = b.split(",");
                            for(String l: linha){
                                if(!G.existeV(b))
                                    G.seta_informacao(ptr++,b);
                                G.cria_adjacenciaIn(a,b);

                            }

                        }
                        if (!G.existeV(b))
                            G.seta_informacao(ptr++, b);
                        G.cria_adjacenciaIn(a, b);
                    }
                }
            }
        }


        System.out.println("=====================================================================");
        System.out.println("                                GRAFO                                ");
        System.out.println("=====================================================================\n\n");
        G.imprime_adjacencias();


        System.out.println("\n----------------------------------------------------------------------");
        System.out.println("N. de vértices do grafo: ");
        System.out.println("------------------------------------------------------------------------\n");
        System.out.println(G.numVertices());


        System.out.println("\n----------------------------------------------------------------------");
        System.out.println("N. de arestas do grafo: ");
        System.out.println("------------------------------------------------------------------------");
        System.out.println(G.numArestas());

        System.out.println("\n----------------------------------------------------------------------");
        System.out.println("20 indivíduos que possuem maior grau de saída e o valor correspondente: ");
        System.out.println("------------------------------------------------------------------------\n");
        Vertice[] r = G.grauSaida();
        System.out.println();

        System.out.println("\n------------------------------------------------------------------------");
        System.out.println("20 indivíduos que possuem maior grau de entrada e o valor correspondente: ");
        System.out.println("--------------------------------------------------------------------------\n");
        G.maiorGrauEntrada();
        System.out.println();

        System.out.println("\n------------------------------------------------------------------------");
        System.out.println("                       Caminho de maior dependencia:                       ");
        System.out.println("--------------------------------------------------------------------------\n");
        G.maiorCaminho(0, 5);
        System.out.println();


            System.out.println("\n------------------------------------------------------------------------");
            System.out.println("                       Busca em Profundidade:                       ");
            System.out.printf("Usuário: " + G.getVertices()[70].getId() + " Para: " + G.getVertices()[400].getId() + "\n");
            System.out.println("--------------------------------------------------------------------------\n");
        Buscas.ProfundidadeEmail(G,G.getVertices()[70],G.getVertices()[400]);

            G.resetarV();
            System.out.println("\n------------------------------------------------------------------------");
            System.out.println("                       Busca em Largura:                       ");
            System.out.printf("Usuário: " + G.getVertices()[70].getId() + " Para: " + G.getVertices()[400].getId() + "\n");
            System.out.println("--------------------------------------------------------------------------\n");
        Buscas.LarguraEmail(G,G.getVertices()[70], G.getVertices()[400]);
        //     G.maiorDistancia(0,7);

            G.resetarV();
    }


        public static String lerDe (BufferedReader read) throws IOException {
        String line = read.readLine();
        for (int i = 0; i < 2; i++)
            line = read.readLine();
        return line = line.replace("From: ", "");
    }

        public static String lerPara (BufferedReader read) throws IOException {
        String line = read.readLine();
        String[] aux;
        line = line.replace("To: ", "");

        if (line.contains(",")) {
            aux = line.split(",");
            return aux[0];
        }

        return line;
    }

}



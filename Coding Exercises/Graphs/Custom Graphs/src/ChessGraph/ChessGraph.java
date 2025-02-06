package ChessGraph;

import Grafo.Grafo;
import Grafo.Vertice;
import Utilidade.Pajek;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class ChessGraph {


    public static class comparadorCoeficiente implements Comparator<double[]> {

        @Override
        public int compare(double[] doubles, double[] t1) {
            if(doubles[1] < t1[1])
                return 1;
            else if(doubles[1] > t1[1])
                return -1;
            return 0;
        }
    }




    public static void main(String[] args) throws Exception{

        Pajek pajek = new Pajek();

        Grafo g = pajek.ler("C:\\Users\\hrsch\\OneDrive\\PUCPR\\Matérias\\4º Semestre\\Resolução de Problemas com Grafos\\TDE 5\\src\\ChessGraph\\ChessWalOut.txt");



        g.imprime_adjacencias();


        System.out.println();
        System.out.println("Especificações do grafo: ");
        System.out.println("    - Numero de jogadores: " + g.numVertices());
        System.out.println("    - Numero de partidas: " + g.numArestas());
        System.out.println("    - Conexo: " + g.isConexo());
        System.out.println("    - Euleriano: " + g.isEuleriano());
        System.out.println("    - Ciclico: " + g.isCyclic());
        System.out.println("    - Coeficiente de Agrupamento Medio: " + g.caMedio(true));
        Vertice vertice = g.maiorArestas();
        System.out.println("    - Jogador que mais jogou: " + vertice.getId());
        System.out.println("    - Numero de partidas de " + vertice.getId() + ": " + vertice.getNAdj());



        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File("coeficienteDeAgrupamentoLocal.txt")));

        ArrayList<double[]> maioresCoeficentes = new ArrayList<>();

        int acumuladorZeros = 0;
        int acumuladorMaiores = 0;

        for (int i = 0; i < g.getVertices().length; i++){
            double aux = g.caLocal(g.getVertice(i), true);
            bufferedWriter.append(String.valueOf(i)).append(";")
                    .append(String.valueOf(g.getVertice(i).getId())).append(";")
                    .append(String.valueOf(aux)).flush();
            bufferedWriter.newLine();
            if (aux > 0){
//                System.out.println("    - Coeficiente de Agrupamento Local (" + i + "): " + aux);
                maioresCoeficentes.add(new double[] {i, aux});
                acumuladorMaiores++;
            } else
                acumuladorZeros++;
        }

        System.out.println("    - Numero de jogadores que jogam com qualquer jogador: " + acumuladorZeros);
        System.out.println("    - Numero de jogadores que praticam panelinha: " + acumuladorMaiores);

        bufferedWriter.close();

        Collections.sort(maioresCoeficentes, new comparadorCoeficiente());

        System.out.println("    - Top 5 panelinha:");
        for (int i = 0; i < 5; i++){
            System.out.println("        >> Coeficiente de Agrupamento Local (" + g.getVertice((int) maioresCoeficentes.get(i)[0]).getId() + "): " + formatDouble(maioresCoeficentes.get(i)[1]));
//            System.out.println("        >> Coeficiente de Agrupamento Local (" + g.getVertice((int) maioresCoeficentes.get(i)[0]).getId() + "): " + maioresCoeficentes.get(i)[1]);
            System.out.print("          ");
            g.getVertice((int) maioresCoeficentes.get(i)[0]).imprimi_adj();
            System.out.println();
        }
        System.out.println("    - Percentual de escolha de oponentes");
        System.out.println("        >> aleatorio: " + formatDouble(((double) acumuladorZeros/g.getTAM()) *100) + "%");
        System.out.println("        >> panelinha: " + formatDouble(((double) acumuladorMaiores/g.getTAM()) *100) + "%");

        System.out.println(" ");
        System.out.println("    -- Centralidade de Posicionamento: ");
        System.out.println("        (1): " + g.CentralidadePosicionamento(0));
        System.out.println("        (2): " + g.CentralidadePosicionamento(1));
        System.out.println("        (7): " + g.CentralidadePosicionamento(6));
        System.out.println("    -- Centralidade de Intermediação: ");
        System.out.println("        (1): " + g.CentralidadeIntermediacao(g.getVertice(0), false));
        System.out.println("        (2): " + g.CentralidadeIntermediacao(g.getVertice(1), false));
        System.out.println("        (7): " + g.CentralidadeIntermediacao(g.getVertice(6), false));
        System.out.println();
    }

    private static String formatDouble(double num){
        return new DecimalFormat("#0.###").format(num);
    }

}

package br.pucpr.Warshall;

public class Warshall {

    final static int INF = 99999;

    void floydWarshall(Grafo g) throws Exception {
        int V = g.linha;
        Grafo dist = new Grafo(V);
        int i, j, k;

        /*Inicializa a matriz de solução da mesmo tamanho que a matriz do grafo de entrada.
       Os valores iniciais das distâncias mais curtas  baseiam-se nos caminhos mais curtos, sem considerar vertices intermediários.*/

        for (i = 0; i < V; i++)
            for (j = 0; j < V; j++)
                dist.matriz[i][j] = g.matriz[i][j];

            /*Adiciona todos os vértices um por um ao conjunto de vértices intermediários.
        Antes do início de uma iteração, temos a distância mais curta entre todos os pares de vértices de modo que
        as distâncias mais curtas consideram apenas os vértices no conjunto {0, 1, 2, .. k-1} como vértices intermediários.
        Após o final de uma iteração, vértice k é adicionado para o conjunto de vértices intermediários e o conjunto torna-se {0, 1, 2, .. k}*/

        for (k = 0; k < V; k++)
        {
            // Pega todos os vertices como origem um a um
            for (i = 0; i < V; i++)
            {
                // Pega todos os vertices como destino para a origem acima
                for (j = 0; j < V; j++)
                {
                    // Se o vertice k esta no caminho menor de i ate j, entao atualiza o valor de dist.matriz[i][j]
                    if (dist.matriz[i][k] + dist.matriz[k][j] < dist.matriz[i][j])
                        dist.matriz[i][j] = dist.matriz[i][k] + dist.matriz[k][j];
                }
            }
        }
        //Imprime a matriz resultado
        System.out.println("A matriz a seguir mostra o menor caminho "+
                "entre todos os pares de vertices");
        for (int n=0; n<V; ++n)
        {
            for (int m=0; m<V; ++m)
            {
                if (dist.matriz[n][m]==INF)
                    System.out.print("INF ");
                else
                    System.out.print(dist.matriz[n][m]+"   ");
            }
            System.out.println();
        }
    }
    }



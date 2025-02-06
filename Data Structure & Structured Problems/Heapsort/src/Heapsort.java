public class Heapsort {
    // Declaração de "variáveis globais" para utilizar em qualquer função
    private static Integer tam;
    private static Integer[] vetor;

    // Função do Heapsort
    // Essa função provoca o desequilibrio do Max-Heap.
    // Ela faz com que o maior valor, que está no topo, seja trocado pelo último valor do Max-Heap.
    // Após a troca, diminui o valor de 'tam' para que o valor mais alto permaneça na última posição.
    // Então é chamada a função maxHeapifica para o novo valor que está no topo.
    public static void heapsort(Integer[] array) {
        vetor = array;          // vetor global recebe o vetor passado como parâmetro
        tam = vetor.length - 1;     // o tamanho deste vetor é armazenado em 'tam', que também é global

        // Chama a função para construir um Max-Heap
        constroiHeap();

        // Assim que a Max-Heap foi criada, o processo de ordenação pode começar.
        // Através desse loop que a troca do valor do topo com o valor da última posição da Heap é feita
        for (int i = tam; i > 0; i--) {
            troca(0, tam);      // Troca a posição
            tam -= 1;           // Diminui 'tam' para não alterar a posição do maior valor nas próximas iterações
            maxHeapifica(0);    // Como existe um valor menor no topo, é necessário heapificar novamente a árvore inteira
        }
    }

    // Função que constrói o Max-Heap
    private static void constroiHeap() {
        // Como o último nível da árvore não tem filhos, a construção se inicia no último elemento da penúltima.
        // Esse elemento se encontra bem no meio do vetor, ou seja, tam/2:
        int meio = (int) (tam / 2);

        // Para cada elemento do penúltimo nível, chama o maxHeapifica, ou seja
        // encontra o maior elemento e coloca como pai
        for (int i = meio - 1; i >= 0; i--) {
            maxHeapifica(i);
        }
    }

    // Função de troca
    // É uma função simples de swap, para auxiliar na troca de posição dos valores
    private static void troca(int i, int j) {
        int aux;

        aux = vetor[i];
        vetor[i] = vetor[j];
        vetor[j] = aux;
    }

    // Função maxHeapifica
    // Essa função é o core do algoritmo.
    // Ela faz a comparação entre os valores de um Heap e ao encontrar o maior, o coloca como pai da Heap.
    // Isso faz com que qualquer Heap se torne um Max-Heap.
    private static void maxHeapifica(int pai) {
        int maior = pai,            // O maior elemento é o pai, até que se prove o contrário.
                esquerda = 2 * pai + 1,     // Pega a posição do filho da esquerda
                direita = 2 * pai + 2;  // e a do filho da direita.

        // Se o filho da esquerda existe, ou seja, se ele está dentro do intervalo verificável do array E
        // Se este filho é maior que o pai (que no momento é o 'maior')
        if (esquerda <= tam && vetor[esquerda] > vetor[maior])
            maior = esquerda;

        // Se o filho da direita existe, ou seja, se ele está dentro do intervalo verificável do array E
        // Se este filho é maior que o 'maior' (que no momento pode ser o 'pai' ou o 'esquerda')
        if (direita <= tam && vetor[direita] > vetor[maior])
            maior = direita;

        // Se ao chegar até aqui o 'pai' deixou de ser o 'maior' valor
        if (maior != pai) {
            troca(pai, maior);      // Faz a troca de posições
            maxHeapifica(maior);    // Continua heapificando com o valor que foi trocado
        }
    }
}

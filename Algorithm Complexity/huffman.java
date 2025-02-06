// Imports
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
 
// Nó da árvore
class Node // Declaração da classe Nó
{
    Character ch; // Declaração de um char "ch"
    Integer freq; // Declaração de um int "freq"
    Node left = null, right = null; // // Declaração de dois Nós, um "left" e um "right"
 
    Node(Character ch, Integer freq) // Método constructor para o Nó
    {
        this.ch = ch; // Atribuindo o char "ch"
        this.freq = freq; // Atribuindo o int "freq"
    }
 
    public Node(Character ch, Integer freq, Node left, Node right) // Método de criação para o Nó
    {
        this.ch = ch; // Atribuindo o char "ch"
        this.freq = freq; // Atribuindo o int "freq"
        this.left = left; // Atribuindo o Nó "left"
        this.right = right; // Atribuindo o Nó "right"
    }
}

// Main
public class Main // Declaração da classe Main para execução
{
    // Declaração da função de codificação Huffman, o objetivo aqui é percorrer a Árvore de Huffman 
    // e armazenar os códigos de Huffman em um Map
    public static void encode(Node root, String str, // Declaração dos atributos para codificação, 
                        Map<Character, String> huffmanCode)  // primeiramente um Nó raiz, uma String para condificação, 
                                                            // e um Map que é o resultado da codificação de Huffman
    {
        if (root == null) { // Caso a raiz seja nula,
            return; // encerra a função
        }
 
        
        if (isLeaf(root)) { // Caso a raiz providenciada seja uma folha
            huffmanCode.put(root.ch, str.length() > 0 ? str : "1"); // Colocando o char da folha e a string dentro do Map
        }
 
        encode(root.left, str + '0', huffmanCode); // Chamada recursiva para a folha a esquerda do nó, 
                                                  // providenciando a string + '0' e o próprio Map.
        encode(root.right, str + '1', huffmanCode); // Chamada recursiva para a folha a direita do nó, 
                                                   // providenciando a string + '1' e o próprio Map.
    }
 
    // Declaração da função de decodificação, o objetivo aqui é percorrer a Árvore de Huffman e 
    // decodificar a string codificada
    public static int decode(Node root, int index, StringBuilder sb) // Declaração dos atributos para decodificação,  
                                                                    // primeiramente um Nó raiz, um int que atua como index e
                                                                   // um StringBuilder "sb" que ajudará a transformar os códigos da árvore na string original
    {
        if (root == null) { // Caso a raiz seja nula,
            return index; // encerra a função
        }
 
        if (isLeaf(root)) // Caso a raiz providenciada seja uma folha
        {
            System.out.print(root.ch); // Printa o char daquele nó
            return index; // retorna o index providenciado
        }
 
        index++; // incrementa o index
 
        root = (sb.charAt(index) == '0') ? root.left : root.right; // Redefine a raiz utilizando sb, caso o char do index incrementado seja igual a zero,
                                                                  // utiliza-se a folha à esquerda, caso contrário a folha à direita
        index = decode(root, index, sb); // define index como um chamada recursiva da própria função de decodificação, providenciando a raiz, o index e o sb
        return index; // retorna o index alterado
    }
 
    // Função utilitária que checa se a árvore de Huffman contém somente a raiz
    public static boolean isLeaf(Node root) { // Declaração do atributo da função, um nó Raiz
        return root.left == null && root.right == null; // Retorna true caso a raiz esteja sozinha na árvore e falso caso tenha algum nó
    }
 
    // Função para construção da Árvore de Huffman, assim como decodificação de um texto via String dos parâmetros
    public static void buildHuffmanTree(String text) // Declaração do atributo da função, um texto no formato String
    {
        // Caso base, uma string vazia
        if (text == null || text.length() == 0) { // Verifica se a String é vazia ou nula
            return; // Encerra a função
        }
 
        // Conta a frequência de cada char e armazena essa contagem dentro de um novo Map
        Map<Character, Integer> freq = new HashMap<>(); // Cria um map de frequência
        for (char c: text.toCharArray()) { // Para cada char "c" na string "text" convertida para um array de chars
            freq.put(c, freq.getOrDefault(c, 0) + 1); // Colocando o char "c" e o valor mapeado para a chave char "c" do Map incrementado de 1
        }
 
 
        PriorityQueue<Node> pq; // Define uma fila de prioridade com a finalidade de armazenar nós da árvore de Huffman
        pq = new PriorityQueue<>(Comparator.comparingInt(l -> l.freq)); // Cria fila de prioridade. A prioridade têm uma relação inversa a frequência
 
 
        for (var entry: freq.entrySet()) { // Um loop for para uma var "entry" no Map tranformado em um set, efetivamente cria um nó folha para cada char
            pq.add(new Node(entry.getKey(), entry.getValue())); // Adiciona um novo nó na fila de prioridade, com a chave e valor da "entry"
        }
 
        while (pq.size() != 1) // Loop while até que a fila tenha mais do que 1 elemento
        {
            // Remove os dois nós da maior prioridade (menor frequência) da fila
            Node left = pq.poll();
            Node right = pq.poll();
 
            int sum = left.freq + right.freq; // Soma das frequências dos nós declarados acima, atribuído a "sum"
            pq.add(new Node(null, sum, left, right)); // Adiciona a fila de prioridade um novo nó que possui ambos os nós declarados acima como folhas
                                                     // Além de possuir frequência igual a "sum"
        }
 
        Node root = pq.peek(); // O Nó "root" armazena um ponteiro para a raiz da árvore de Huffman
 
        Map<Character, String> huffmanCode = new HashMap<>(); // Cria um novo HashMap chamado huffmanCode
        encode(root, "", huffmanCode); // Chama a função de condificação, passando como atributos a raiz, uma string vazia e o HashMap declarado acima
 
        System.out.println("Códigos de Huffman: " + huffmanCode); // Printa o HashMap
        System.out.println("Texto recebido: " + text); // Printa o texto recebido como parâmetro
 
        StringBuilder sb = new StringBuilder(); // Cria um novo StringBuilder "sb"
        for (char c: text.toCharArray()) { // Para cada char "c" na string "text" convertida para um array de chars
            sb.append(huffmanCode.get(c)); // Adciona a "sb" o char "c" dentro do HashMap
        }
 
        System.out.println("O texto codificado é: " + sb); // Printa a string codificada
        System.out.print("O texto descodificado é: "); // Print
 
        if (isLeaf(root)) // Caso a raiz seja uma folha
        {
            // Caso especial para casos de repetição, como: a, aa, aaa...
            while (root.freq-- > 0) { // Enquanto a frequência decrementada for maior que 0
                System.out.print(root.ch); // Printa o char da raiz
            }
        }
        else {
            int index = -1; // Declara index como -1
            while (index < sb.length() - 1) { // Enquanto index for menor que o tamanho de "sb" decrescido de 1
                index = decode(root, index, sb); // redefine o index como uma chamada recursiva, passando a raiz, index e o StringBuilder definidos
            }
        }
    }
 
    // Algoritmo de codificação Huffman
    public static void main(String[] args) // Public main
    {
        String text = "Compreender a complexidade de algoritmos é importante para desenvolver programas mais eficientes"; // String a ser codificada
        buildHuffmanTree(text); // Chamada do método de construção da árvore de Huffman com a string definida acima
    }
}
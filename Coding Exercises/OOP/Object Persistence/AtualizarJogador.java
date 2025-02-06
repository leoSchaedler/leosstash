import java.io.*;
public class AtualizarJogador {
    public static void main(String[] args) {
        String nome_arquivo = "BlueJedi.ser";
        try {
            Jogador jogador = Jogador.abrir(nome_arquivo);
            System.out.println("Jogador recuperado com sucesso!");
            jogador.exibir();
            jogador.posicionar(20.0f);
            jogador.pontuar();
            jogador.exibir();
            jogador.salvar(nome_arquivo);
            System.out.println("Jogador salvo com sucesso!");
        } catch (IOException e) {
            System.out.println("Excecao de I/O");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Excecao de classe desconhecida");
            e.printStackTrace();
        }
    }
}

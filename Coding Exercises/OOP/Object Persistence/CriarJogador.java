import java.io.*;
public class CriarJogador {
    public static void main(String[] args) {
        Jogador jogador = new Jogador("Blue Jedi", 100);
        jogador.posicionar(5.0f);
        jogador.exibir();
        try {
            jogador.salvar("BlueJedi.ser");
            System.out.println("Jogador criado e salvo com sucesso!");
        } catch (IOException e) {
            System.out.println("Excecao de I/O");
            e.printStackTrace();
        }
    }
}

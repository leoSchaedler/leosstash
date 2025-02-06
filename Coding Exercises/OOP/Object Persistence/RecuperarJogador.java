import java.io.*;
public class RecuperarJogador {
    public static void main(String[] args) {
        try {
            Jogador jogador = Jogador.abrir("BlueJedi.ser");
            System.out.println("Jogador recuperado com sucesso!");
            jogador.exibir();
        } catch (IOException e) {
            System.out.println("Excecao de I/O");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Excecao de classe desconhecida");
            e.printStackTrace();
        }
    }
}

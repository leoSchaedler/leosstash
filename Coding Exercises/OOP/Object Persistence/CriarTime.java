import java.io.*;
public class CriarTime {
    public static void main(String[] args) {
        Time time = new Time("Bugs", "Spider", 30, "Flea", 60, "Snake");
        time.exibir();
        try {
            time.salvar("Bugs.ser");
            System.out.println("Time criado e salvo com sucesso!");
        } catch (IOException e) {
            System.out.println("Excecao de I/O");
            e.printStackTrace();
        }
    }
}

import java.io.*;
public class RecuperarTime {
    public static void main(String[] args) {
        try {
            Time time = Time.abrir("Bugs.ser");
            System.out.println("Time recuperado com sucesso!");
            time.exibir();
        } catch (IOException e) {
            System.out.println("Excecao de I/O");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Excecao de classe desconhecida");
            e.printStackTrace();
        }
    }
}
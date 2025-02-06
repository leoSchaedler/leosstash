import Components.IO;
import Components.InvalidAddres;
import Components.RAM;

public class Aula01 {
    public static void main(String[] args) {

        IO es = new IO(System.out);
        RAM ram = new RAM(7);

        es.Output("Olá");

        try {
            ram.Write(122, 11);
            System.out.println(ram.Read(122));

            ram.Write(128, 111);
        } catch (InvalidAddres addres){
            System.err.println(addres);
        }
    }
}

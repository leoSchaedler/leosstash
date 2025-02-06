import Components.*;

public class Aula02 {
    public static void main(String[] args) {
        IO io = new IO(System.out);
        RAM ram = new RAM(7);
        Cache cache = new Cache(8, ram);
        CPU cpu = new CPU(cache, io);

        try {
            final int start = 10;

            ram.Write(start, 118);
            ram.Write(start + 1, 130);

            cpu.Run(start);
        } catch (InvalidAddres e) {
            System.out.println("Erro: " + e);
        }
    }
}

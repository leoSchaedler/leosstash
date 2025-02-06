import Components.CPU;
import Components.Cache;
import Components.IO;
import Components.RAM;

public class Testes {
    public static void main(String[] args) throws Exception{

        IO io = new IO(System.out);
        RAM ram = new RAM(10);
        Cache cache = new Cache(8, ram);
        CPU cpu = new CPU(cache, io);

        final int start = 10;

        ram.Write(start, 118);
        ram.Write(start+1, 130);

        cpu.Run(start);
    }
}

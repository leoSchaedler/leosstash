import exception.InvalidAddres;
import jdk.jshell.spi.ExecutionControl;
import util.Address;
import util.InputReaderService;
import util.Memory;

import java.util.Random;

public class CPU {

    private final Random random = new Random();
    private final InputReaderService input = new InputReaderService();

    private final Memory memory;

    private final Integer mainMemorySize;   //MB
    private final Integer generatedAddress; //bits
    private final Integer lineSize;         //k

    public CPU(Memory Ram, Integer mainMemorySize, Integer generatedAddress, Integer lineSize) {
        this.memory = Ram;
        this.mainMemorySize = mainMemorySize;
        this.generatedAddress = generatedAddress;
        this.lineSize = lineSize;
    }

    public void Run(int address) throws InvalidAddres, ExecutionControl.NotImplementedException {
        int register_a = this.memory.Read(address++);
        int register_b = this.memory.Read(address);

        for (int i = register_a; i < register_b; ++i) {
            memory.Write(i, i - register_a + 1);
            System.out.println("\tcpu> " + i + "->" + (i - register_a + 1));
        }
    }

    public void ReadMemory(Integer address) throws InvalidAddres {
        System.out.println("__CPU  >> Acessando " + address);
        System.out.printf("Informação acessada: %s%n", memory.Read(new Address(address, lineSize, generatedAddress)));
    }

    public void WriteMemory(Address address, Integer word) throws InvalidAddres {
        System.out.println("__CPU  >> Acessando e escrevendo em " + address.getFullAddress());
        memory.Write(address, word);
    }

    public void DefaultReadTest() throws InvalidAddres {
        int realSize = (int) Math.pow(2, mainMemorySize);
        Integer sorted = random.nextInt(realSize);
        System.out.println("    >> Número sorteado para ser acessado: " + sorted);
        ReadMemory(sorted);
    }

    public void DefaultWriteTest() throws InvalidAddres {
        int realSize = (int) Math.pow(2, mainMemorySize);
        Integer address = random.nextInt(realSize);
        Integer word = random.nextInt(realSize);
        System.out.printf("    >>  Palavra sorteada: %s no endereço %s%n", word, address);
        WriteMemory(new Address(address, lineSize, generatedAddress), word);
        System.out.println(word + " salvo com sucesso na posição " + address);
    }

    public void Write(Integer scanned) throws InvalidAddres {
        System.out.print("    >>  Digite a palavra a ser gravada: ");
        Integer dataIn = input.ReadInteger("Info incorreta ... apenas número");
        WriteMemory(new Address(scanned, lineSize, generatedAddress), dataIn);
        System.out.println(dataIn + " salvo com sucesso na posição " + scanned);
    }

}

package Components;

public class CPU {

    private final Memory memory;
    private final IO io;

    public CPU (Memory Ram, IO Io){
        this.memory = Ram;
        this.io = Io;
    }

    public void Run(int address) throws InvalidAddres{
        int register_a = this.memory.Read(address++);
        int register_b = this.memory.Read(address);

        for (int i = register_a; i < register_b; ++i) {
            memory.Write(i, i - register_a + 1);
            io.Output("\tcpu> " + i + "->" + (i-register_a+1));
        }
    }


}

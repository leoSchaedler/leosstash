package Components;

public interface Memory {

    Integer Read(int address) throws InvalidAddres;

    void Write(int address, int p) throws InvalidAddres;

}



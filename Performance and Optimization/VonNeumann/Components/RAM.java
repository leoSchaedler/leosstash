package Components;

import Util.ArrayPrinter;

public class RAM implements Memory {

    private final Integer[] mem;
    private final int tam;

    public RAM(int k){
        this.tam = (int) Math.pow(2, k);
        this.mem = new Integer[this.tam];
    }

    public Integer[] getMem() {
        return mem;
    }

    @Override
    public void Write(int address, int word) throws InvalidAddres{
        if (isValid(address)) {
            this.mem[address] = word;
            System.out.println("Address: " + address + " word: " + word);
            ArrayPrinter.printArray(this.mem);
        }
        else throw new InvalidAddres(address);
    }

    @Override
    public Integer Read(int address) throws InvalidAddres{
        try {
            return this.mem[address];
        } catch (Exception e) {
            throw new InvalidAddres(address);
        }
    }

    private boolean isValid(int e){
        return ( e>= 0 && e < this.tam);
    }
}

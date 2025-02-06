package util;

import exception.InvalidAddres;

public abstract class Memory {

    protected final Integer size;

    protected Memory(Integer size) {
        this.size = size;
    }

    public Integer getSize() {
        return size;
    }

    public Integer Read(int address) throws InvalidAddres{
        return Integer.MIN_VALUE;
    }

    public void Write(int address, int word) throws InvalidAddres {}

    public Integer Read(Address address) throws InvalidAddres {
        return Integer.MIN_VALUE;
    }

    public void Write(Address address, int word) throws InvalidAddres {}

}



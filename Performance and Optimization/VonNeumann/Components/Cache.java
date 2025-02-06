package Components;

import Util.ArrayPrinter;

import java.util.Objects;

public class Cache implements Memory {

    private Integer firstAddress;
    private Integer size;
    private Integer[] ramCopy;
    private RAM ram;

    public Cache(int size, RAM ram) {
        this.size = (int) Math.pow(2, size);
        this.ramCopy = new Integer[this.size];
        this.ram = ram;
        this.firstAddress = -1;
    }


    @Override
    public Integer Read(int address) throws InvalidAddres {
        haveToReachRam(address);
        int a = normalizeIndex(address);
        try {
            return this.ramCopy[a];
        } catch (Exception e) {
            throw new InvalidAddres(a);
        }
    }


    @Override
    public void Write(int address, int word) throws InvalidAddres {
        System.out.println(">> Write >> address: " + address + " word: " + word);
        haveToReachRam(address);
        try {
            this.ramCopy[address] = word;
        } catch (Exception e) {
            System.out.println("Error -> " + address);
            throw new InvalidAddres(address);
        }
    }

    private void haveToReachRam(Integer address) throws InvalidAddres {
        if (isEmpty() || !isBetweenList(address))
            reachRAM(address);
    }

    private Boolean isEmpty() {
        return this.firstAddress == -1;
    }

    private Boolean isBetweenList(Integer address) {
        return normalizeIndex(address) < this.size;
    }

    private void reachRAM(Integer address) throws InvalidAddres {
        saveCacheToRam();
        changeCopyRam(address);
    }

    private void saveCacheToRam() throws InvalidAddres {
        if (!isEmpty())
            for (int i = 0; i < this.size; i++)
                try {
                    if (this.ram.getMem().length > i + this.firstAddress && !Objects.equals(this.ram.getMem()[i + this.firstAddress], this.ramCopy[i]))
                        this.ram.getMem()[+this.firstAddress] = this.ramCopy[i];
                } catch (Exception e) {
                    System.out.println("Error -> " + i);
                    throw new InvalidAddres(i);
                }
    }

    private void changeCopyRam(Integer address) throws InvalidAddres {
        this.firstAddress = address;
        for (int i = 0; i < this.size; ++i) {
            try {
                this.ramCopy[i] = this.ram.getMem()[i + this.firstAddress];
            } catch (Exception e) {
                throw new InvalidAddres(i + this.firstAddress);
            }
        }
        System.out.println("RAM Copy>> SUCCESS");
        ArrayPrinter.printArray(this.ramCopy);
    }

    private Integer normalizeIndex(Integer index) {
        return index - this.firstAddress;
    }
}

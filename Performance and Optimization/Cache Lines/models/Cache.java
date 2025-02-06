package models;

import exception.InvalidAddres;
import util.Address;
import util.Memory;

import java.util.Objects;

public class Cache extends Memory {

    private final RAM ram;
    private Line[] lines;

    public Cache(int cacheSize, int lineSize, RAM ram) {
        super(cacheSize);
        this.ram = ram;
        initializeLines(lineSize);
    }

    public Line[] getLines() {
        return lines;
    }

    @Override
    public Integer Read(Address address) throws InvalidAddres { // Metodo para ler um espaço de memoria na RAM
        System.out.println(">> Read >> address: " + address.getFullAddress());
        accessReport(address);      // Printa uma mensagem na tela para acompanhamento do sistema
        reachRam(address);          // Se necessário, faz o acesso a RAM
        try {
            Integer consulted = consult(address);   // Consulta um endereço de memoria
            System.out.println("Readed the word: " + consulted);
            return consulted;                       // Retorna o valor consultado
        } catch (Exception e) {
            throw new InvalidAddres(address);
        }
    }

    @Override
    public void Write(Address address, int word) throws InvalidAddres { // Metodo para acessar e escrever em um espaço
        System.out.println(">> Write >> address: " + address.getFullAddress() + " word: " + word);
        accessReport(address);      // Printa uma mensagem na tela para acompanhamento do sistema
        reachRam(address);          // Se necessário, faz o acesso a RAM
        try {
            this.lines[address.getR()].getMemoryBlock()[address.getW()] = word; // Acessa e altera o espaço de memoria
            this.lines[address.getR()].setHasChange(true);                      // Define como valor alterado
        } catch (Exception e) {
            throw new RuntimeException(
                    String.format("Error trying to access memory in line %s on %s memory block", address.getR(),
                            address.getW())
            );
        }
    }


    private Integer consult(Address address) {  // Acesso a um determinado endereço na memoria
        try {
            return this.lines[address.getR()].getMemoryBlock()[address.getW()];
        } catch (Exception e) {
            throw new RuntimeException(
                    String.format("Error trying to access memory in line %s on %s memory block", address.getR(),
                            address.getW())
            );
        }
    }

    private void initializeLines(int k) {   //Inicializa (instancia) as lines na cache
        this.lines = new Line[size];
        for (int i = 0; i < this.size; i++)
            this.lines[i] = new Line(i);
    }

    private void reachRam(Address address) {    // Print para acompanhamento do processo
        if (!isInsideCache(address)) {
            System.out.println("!!!CACHE MISS!!! >> Causado pelo endereço " + address.getFullAddress());
            System.out.printf("                 >> Value of 'w (6)': %s%n", address.getW());
            System.out.printf("                 >> Value of 'r (7)': %s%n", address.getR());
            System.out.printf("                 >> Value of 't (11)': %s%n", address.getT());
            reachRAM(address);
        } else
            System.out.println("!!!CACHE HIT!!!");
    }

    private Boolean isInsideCache(Address address) {    // Conferencia se o endereço requerido já está na cache
        try {
            return !this.lines[address.getR()].isEmpty()    //Ve se a linha está vazia ou existem valores
                    && Objects.equals(this.lines[address.getR()].getTag(), address.getT()); // Comparação entre as tags
        } catch (Exception e) {
            System.out.println("some problem with 'isInsideCache'");
            return false;
        }
    }

    private void reachRAM(Address address) {    // Solicita acesso a RAM a line
        this.lines[address.getR()].reachRAM(address, this.ram);
    }


    private void accessReport(Address address) {    // Print para acompanhamento do processo
        System.out.println(">> Acessando endereço: " + address.getFullAddress() + " na memoria");
        System.out.println(">> Índice da cache line de " + address.getR() + " (r)");
        System.out.println(">> Índice da cache line de " + address.getT() + " (t)");
        System.out.println(">> Posição de " + address.getW() + " na cache line (w)");
    }


}

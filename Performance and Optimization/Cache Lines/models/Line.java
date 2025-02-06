package models;

import util.Address;

public class Line {

    private Integer ramInitialPointer;

    private Boolean hasChange;

    private Integer tag;  //t'

    private Integer[] memoryBlock;

    public Line(Integer tag) {
        this.ramInitialPointer = -1;
        this.hasChange = false;
        this.tag = tag;
        this.memoryBlock = null;
    }

    public Boolean isEmpty() {
        return this.ramInitialPointer == -1;
    }

    public void setHasChange(Boolean hasChange) {
        this.hasChange = hasChange;
    }

    public Integer getTag() {
        return tag;
    }

    public Integer[] getMemoryBlock() {
        return memoryBlock;
    }

    public Integer getRamInitialPointer() {
        return ramInitialPointer;
    }

    public void reachRAM(Address address, RAM ram) {
        if (!isEmpty() && hasChange)
            saveLineToRam(address, ram);
        saveRamToLine(address, ram);
    }


    private void saveRamToLine(Address ramIndex, RAM ram) {     // Salvando os valores da Ram na Line (Cache Miss)
        System.out.printf("    >> SAVING RAM %s INTO CACHE %s  <<%n", ramIndex.getFullAddress(), ramIndex.getR());
        this.memoryBlock = new Integer[ramIndex.getK()];    // instancia da memoria da line
        this.ramInitialPointer = ramIndex.getFullAddress(); // define um ponteiro para a primeira posicão da ram
        this.tag = ramIndex.getT();                         // define a tag (t)
        this.hasChange = false;                             // restaura a coferencia por mudanças

        for (int i = 0; i < ramIndex.getK(); i++)           // percorre o tamanho da memoria copiada
            try {
                if (ramIndex.getFullAddress() + i < ram.getSize())  // salvando o valor da ram na line da cache
                    this.memoryBlock[i] = ram.getMem()[ramIndex.getFullAddress() + i];
            } catch (Exception e) {
                throw new RuntimeException("Bad request memory access during save ram to cache");
            }
    }

    private void saveLineToRam(Address ramIndex, RAM ram) {     // Salvando os valores alterados da Line na Ram
        System.out.printf("    >> SAVING LINE %s INTO RAM %s  <<%n", ramIndex.getR(), ramIndex.getFullAddress());
        for (int i = 0; i < ramIndex.getK(); i++)
            try {       // Salvando os valores que foram alterados na line de volta da ram
                ram.getMem()[ramIndex.getFullAddress() + i] = this.memoryBlock[i];
            } catch (Exception e) {
                throw new RuntimeException("Bad request memory access during save cache to ram");
            }
    }
}

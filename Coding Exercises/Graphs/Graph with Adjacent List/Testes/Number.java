package br.pucpr.Testes;

import java.util.Comparator;

public class Number implements Comparable<Number> {
    private int value;

    public Number(int value) { this.value = value; }
    public int compareTo(Number anotherInstance) {
        return this.value - anotherInstance.value;
    }
}

class Chair {
    private int weight;
    private int height;

    public Chair(int weight, int height) {
        this.weight = weight;
        this.height = height;
    }

    public int getWeight(){
        return this.weight;
    }

    public int getHeight() {
        return height;
    }
}

class ChairWeightComparator implements Comparator<Chair> {
    public int compare(Chair chair1, Chair chair2) {
        return chair1.getWeight() - chair2.getWeight();
    }
}
class ChairHeightComparator implements Comparator<Chair> {
    public int compare(Chair chair1, Chair chair2) {
        return chair1.getHeight() - chair2.getHeight();
    }
}

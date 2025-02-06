package br.pucpr.Auxiliares;

import java.util.Random;

public class Sleep {

    private final int minSleep;
    private final int maxSleep;

    private final Random rand;

    public Sleep(int minSleep, int maxSleep){
        this.minSleep = minSleep;
        this.maxSleep = maxSleep;
        this.rand = new Random();
    }

    public int dormir() throws InterruptedException {
        int aux = rand.nextInt(maxSleep-minSleep)+maxSleep;
        Thread.sleep(aux);
        return aux;
    }

}

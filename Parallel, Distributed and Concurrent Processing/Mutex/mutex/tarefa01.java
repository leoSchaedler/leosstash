package mutex;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class tarefa01 extends Thread{

    private int[] x;
    private Semaphore mutex;

    public tarefa01(int[] x, Semaphore mutex){
        this.x = x;
        this.mutex = mutex;
    }

    @Override
    public void run() {
        Random r = new Random();
        try {
            Thread.sleep(r.nextInt(7000));
            mutex.acquire();
            int temp = this.x[0];
            System.out.println("Tafera 1: " + x[0] + "copy");
            Thread.sleep(r.nextInt(7000));
            temp = temp + 1;
            x[0] = temp;
            System.out.println("Tafera 1: " + x[0]);
            mutex.release();
        } catch (Exception e){
            e.printStackTrace();
        }

//        this.x[0] = this.x[0] + 1;

    }
}

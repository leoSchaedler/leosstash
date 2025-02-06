package mutex;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class tarefa02 extends Thread{

    private int[] x;
    private Semaphore mutex;

    public tarefa02(int[] x, Semaphore mutex){
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
            System.out.println("Tarefa 2: " + this.x[0] + "copy");
            Thread.sleep(r.nextInt(7000));
            temp = temp * 2;
            x[0] = temp;
            System.out.println("Tarefa 2: " + this.x[0]);
            mutex.release();
        } catch (Exception e){
            e.printStackTrace();
        }

//        this.x[0] = this.x[0] * 2;

    }
}

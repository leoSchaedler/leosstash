package mutex;

import mutex.tarefa01;
import mutex.tarefa02;

import java.util.concurrent.Semaphore;

public class testeMutex {

    public static void main(String[] args) {
        int[] x = {10};
        Semaphore mutex = new Semaphore(1);

        tarefa01 t1 = new tarefa01(x, mutex);  //tem que compartilhar a varivel passando como copia
        tarefa02 t2 = new tarefa02(x, mutex);  //para que as threads ascessem a msm alocação de memoria
                                        //se passar direto ira esta fazendo uma cópia do valor
        t1.start();                     //então cada thread tera a sua propria cópia
        t2.start();

        try {       //JUNTAR A EXECUÇÃO, sincronização das threads para terminarem
            t1.join();                // a execução juntas
            t2.join();
            System.out.println("Main: " + x[0]);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}

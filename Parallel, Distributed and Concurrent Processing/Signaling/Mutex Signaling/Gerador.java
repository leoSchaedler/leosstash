package sinalizacaoMutex;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Gerador extends Thread{

    private String alpha = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz";
    private char[] charArray;
    private Semaphore gerador;
    private Semaphore filtro;
    private String text = "";


    public Gerador(char[] charArray, Semaphore gerador, Semaphore filtro){
        this.charArray = charArray;
        this.gerador = gerador;
        this.filtro = filtro;
    }

    @Override
    public void run(){
        Random r = new Random();
        try {
            while(true) {
                gerador.acquire();
                for (int i = 0; i < 10; i++) {
                    char letter = alpha.charAt(r.nextInt(alpha.length()));
                    charArray[i] = letter;
                }
                text = new String(charArray);
                System.out.println("Gerado agora: " + text);
                filtro.release();
            }

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}

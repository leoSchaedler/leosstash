package sinalizacaoMutex;

import java.util.concurrent.Semaphore;

public class Filtro extends Thread{

    private char[] originalCharArray;
    private char[] upperCharArray;
    private Semaphore filtro;
    private Semaphore gerador;
    private Semaphore filtroEXT;
    private Semaphore analisador;
    private String text = "";

    public Filtro(char[] originalCharArray, char[] upperCharArray, Semaphore gerador,
                  Semaphore filtro, Semaphore filtroEXT, Semaphore analisador){
        this.originalCharArray = originalCharArray;
        this.upperCharArray = upperCharArray;
        this.filtro = filtro;
        this.gerador = gerador;
        this.filtroEXT = filtroEXT;
        this.analisador = analisador;
    }

    @Override
    public void run() {
        try {
            while (true) {
                filtro.acquire();
                filtroEXT.acquire();
                for (int i = 0; i < this.originalCharArray.length; i++) {
                    this.upperCharArray[i] = Character.toUpperCase(this.originalCharArray[i]);
                }
                text = new String(upperCharArray);
                System.out.println("Filtrado agora: " + text);
                gerador.release();
                analisador.release();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}

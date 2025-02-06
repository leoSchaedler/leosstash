package sinalizacaoMutex;

import java.util.concurrent.Semaphore;

public class Analisador extends Thread{

    private char[] upperCharArray;
    private Semaphore gerador;
    private Semaphore filtroEXT;
    private Semaphore analisador;

    public Analisador(char[] UpperCharArray, Semaphore gerador, Semaphore filtroEXT, Semaphore analisador){
        this.upperCharArray = UpperCharArray;
        this.filtroEXT = filtroEXT;
        this.gerador = gerador;
        this.analisador = analisador;
    }

    @Override
    public void run(){
        try {
            while (true) {
                int a = 0;
                int e = 0;
                int i = 0;
                int o = 0;
                int u = 0;
                analisador.acquire();
                for (int j = 0; j < upperCharArray.length; j++) {
                    if (upperCharArray[j] == 'A') {
                        a = a + 1;
                    }
                    if (upperCharArray[j] == 'E') {
                        e = e + 1;
                    }
                    if (upperCharArray[j] == 'I') {
                        i = i + 1;
                    }
                    if (upperCharArray[j] == 'O') {
                        o = o + 1;
                    }
                    if (upperCharArray[j] == 'U') {
                        u = u + 1;
                    }
                }
                int vogaisTotais = a + e + i + o + u;
                //System.out.println("Número de ocorrência da vogal a: " + a);
                //System.out.println("Número de ocorrência da vogal e: " + e);
                //System.out.println("Número de ocorrência da vogal i: " + i);
                //System.out.println("Número de ocorrência da vogal o: " + o);
                //System.out.println("Número de ocorrência da vogal u: " + u);
                System.out.print("No texto ");
                for (char k : this.upperCharArray) {
                    System.out.print(k + " ");
                }
                System.out.println(" existem " + vogaisTotais + " vogais.");
                System.out.println("");
                filtroEXT.release();
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}

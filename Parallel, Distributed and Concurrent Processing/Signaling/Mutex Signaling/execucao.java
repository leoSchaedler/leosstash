package sinalizacaoMutex;

import sinalizacaoMutex.Gerador;
import sinalizacaoMutex.Filtro;
import sinalizacaoMutex.Analisador;

import javax.print.attribute.standard.OrientationRequested;
import java.util.concurrent.Semaphore;

public class execucao {
    public static void main(String[] args) {

        char[] originalCharArray = new char[10];
        char[] upperCharArray = new char[10];
        Semaphore gerador = new Semaphore(1);
        Semaphore filtro = new Semaphore(0);
        Semaphore filtroEXT = new Semaphore(1);
        Semaphore analisador = new Semaphore(0);

        Gerador generator = new Gerador(originalCharArray, gerador, filtro);
        Filtro filter = new Filtro(originalCharArray, upperCharArray, gerador, filtro, filtroEXT, analisador);
        Analisador analyzer = new Analisador(upperCharArray, gerador, filtroEXT, analisador);

        try{
                generator.start();
                filter.start();
                analyzer.start();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}

package util;

import models.Empresa;
import models.EmpresaA;
import models.EmpresaB;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class CSC {

    /*
    TODO: Grupo:
        >> Bernardo Tinti
        >> Felipe Gomes
        >> João Król
        >> Leonardo Ribeiro
        >> Patrick Caetano
     */


    private static final int multiplexValue = 3;

    public static final Semaphore EAMultiplex = new Semaphore(multiplexValue);  // Limitante de funcionarios da empresa A
    public static final Semaphore EBMultiplex = new Semaphore(multiplexValue);  // Limitante de funcionarios da empresa B

    private static final int mutexValue = 1;

    public static final Semaphore EAMutex = new Semaphore(mutexValue);   // Exclusção mútua entre empresas A
    public static final Semaphore EBMutex = new Semaphore(mutexValue);   // Exclusção mútua entre empresas B

    public static final Semaphore empty = new Semaphore(1);     // Exclusão mútua entre empresas

    private static final int initCount = 0;

    public static int EBCount = initCount;  // Contador da empresa B
    public static int EACount = initCount;  // Contador da empresa A


    public static final String razaoSocialEmpresaA = "A";   // Nome da empresa A
    public static final String razaoSocialEmpresaB = "B";   // Nome da empresa B


    public static void main(String[] args) {

        int numeroDeEmpresas = 10;

        ArrayList<Empresa> empresas = new ArrayList<>();

        for (int i = 0; i < numeroDeEmpresas; i++)  // Instanciando empresas A
            empresas.add(new EmpresaA(razaoSocialEmpresaA, String.format("F%s", (i + 1))));

        for (int i = 0; i < numeroDeEmpresas; i++)  // Instanciando empresas B
            empresas.add(new EmpresaB(razaoSocialEmpresaB, String.format("F%s", (i + 1))));

        empresas.forEach(Thread::start);    // dando start em todas as empresas (threads)
    }

}

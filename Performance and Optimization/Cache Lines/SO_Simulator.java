import exception.InvalidAddres;
import models.Cache;
import models.Line;
import models.RAM;
import util.Address;
import util.InputReaderService;

import java.util.Random;

public class SO_Simulator {

    /*TODO:
        Ciência da Computação
        Elementos:
            -   Bernardo Tinti
            -   Felipe Gomes
            -   João krol
            -   Leonardo Ribeiro
            -   Patrick Caetano
        */

    private static final Integer mainMemorySize = 16;   //MB
    private static final Integer generatedAddress = 24; //bits

    private static final Integer lineSize = 64;         //k
    private static final Integer totalWordsCount = 8192;
    private static final Integer cacheSize = totalWordsCount / lineSize;   //m

    private static final InputReaderService input = new InputReaderService();
    private static final Random random = new Random();

    private static CPU cpu;
    private static RAM ram;
    private static Cache cache;

    public static void main(String[] args) throws InvalidAddres {

        System.out.println("|--------------------|");
        System.out.println("|   Bem-vindo ao SO  |");
        System.out.println("|--------------------|");
        System.out.println();
        System.out.println("- Informações do sistema: ");
        System.out.printf("    >> RAM: %s MB.%n", mainMemorySize);
        System.out.printf("    >> Tamanho de endereço: %s bits.%n", generatedAddress);
        System.out.printf("    >> Tamanho da Cache: %s palavras.%n", totalWordsCount);

        System.out.println();
        System.out.println("...   Inicializando sistema   ...");

        ram = new RAM(mainMemorySize);
        System.out.println(".... RAM     -- OK");

        cache = new Cache(cacheSize, lineSize, ram);
        System.out.println(".... CACHE   -- OK");

        cpu = new CPU(cache, mainMemorySize, generatedAddress, lineSize);
        System.out.println(".... CPU     -- OK");

        System.out.println("...   Sistema iniciado   ...");
        System.out.println();

        boolean aux = true;
        while (aux)
            aux = menu();

        System.out.println("Sistema desligado com sucesso ....");
    }

    private static boolean menu() throws InvalidAddres {

        String q01 = "    [1] Leitura Padrão (tentativa de acesso a um endereço aleatório).";
        String q02 = "    [2] Escrita Padrão (tentativa de escrita de uma palavra aleatoria em um endereço aleatório).";
        String q03 = "    [3] Escreva uma informação em um determinado endereço de memoria.";
        String q04 = "    [4] Leia uma informação em um determinado endereço de memoria.";
        String q05 = "    [5] Relatório de escrita (informa todas os endereços que contem informação.";
        String q06 = "    [6] Desligar sistema.";

        System.out.println();
        System.out.println("- Digite um comando para a CPU executar: ");
        System.out.println(q01);
        System.out.println(q02);
        System.out.println(q03);
        System.out.println(q04);
        System.out.println(q05);
        System.out.println(q06);
        System.out.println();
        System.out.print(">>  Digite um comando: ");

        Integer dataInputted = input.ReadInteger(-1, 6);
        System.out.println("Opção selecionada: ");
        switch (dataInputted) {
            case 1:
                System.out.println(q01);
                cpu.DefaultReadTest();
                return true;
            case 2:
                System.out.println(q02);
                cpu.DefaultWriteTest();
                return true;
            case 3:
                System.out.println(q03);
                cpu.Write(getIndex());
                return true;
            case 4:
                System.out.println(q04);
                cpu.ReadMemory(getIndex());
                return true;
            case 5:
                System.out.println(q05);
                Report();
                return true;
            case 0:
                System.out.println("    >>  ERROR FORCE.");
                forceError();
                return true;
            default:
                System.out.println(q06);
                return false;
        }
    }

    private static Integer getIndex() {
        System.out.print("    >> Qual o endereço de memória deseja acessar? ");
        Integer indexMem = input.ReadInteger(0, ram.getSize());
        System.out.println();
        return indexMem;
    }

    private static void Report() {
        System.out.println();
        System.out.println("Memória principal: ");
        for (int i = 0; i < ram.getSize(); i++)
            if (ram.getMem()[i] != null)
                System.out.printf("     >> cache index -> [%s] %s%n", i, ram.getMem()[i]);
        System.out.println("Memória cache: ");
        for (int i = 0; i < cache.getSize(); i++)
            for (int j = 0; j < lineSize; j++)
                if (cache.getLines()[i].getMemoryBlock() != null && cache.getLines()[i].getMemoryBlock()[j] != null)
                    System.out.printf("     >> line[%s] - w[%s] cache index -> [%s]  %s%n", i, j,
                            cache.getLines()[i].getMemoryBlock()[j] + cache.getLines()[i].getRamInitialPointer(),
                            cache.getLines()[i].getMemoryBlock()[j]);

    }

    private static void forceError() throws InvalidAddres {
        for (int i = 0; i < lineSize + 1; i++)
            cpu.DefaultWriteTest();
        Report();
    }
}

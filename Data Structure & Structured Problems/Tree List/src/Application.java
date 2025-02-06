import Arvore.Arvore;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;


public class Application {


    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        Arvore x = new Arvore();


            System.out.print("Informe um diretorio: ");
            String dir = input.next();

            Path path = Paths.get(dir);

            if (!Files.exists(path)) {
                while (!Files.exists(path)) {
                    System.out.println("Diretorio não encontrado");
                    dir = input.next();
                    path = Paths.get(dir);
                }
            } else
                System.out.println("Diretorio encontrado");

            FileFilter filter = new FileFilter() {
                public boolean accept(File file) {
                    return file.getName().endsWith(".txt");
                }
            };

            File arquivos[];
            File diretorio = new File(dir);

            arquivos = diretorio.listFiles(filter);
            if (arquivos.length == 0) {
                System.out.println("Não existem arquivos .txt neste diretorio");
                System.exit(0);
            }



        for(int i = 0; i < arquivos.length; i++){
            String nome = arquivos[i].getName();
            String palavra = null;
            try {
                FileReader arq = new FileReader(arquivos[i].getAbsolutePath());
                BufferedReader lerArq = new BufferedReader(arq);
                palavra =  lerArq.readLine();
                x.inserir(palavra, nome);

                while (palavra != null){
                    palavra = lerArq.readLine();
                    if(palavra!=null)
                        x.inserir(palavra, nome);
                }

                arq.close();
            } catch (IOException e) {
                System.err.printf("Erro na abertura do arquivo: %s.\n",
                        e.getMessage());
            }

        }

        System.out.println("=============================");
        System.out.println("*      Arquivos Lidos       *");
        System.out.println("=============================");
        for(int i=0; i<arquivos.length; i++)
            System.out.println(arquivos[i].getName());
        System.out.println("=============================\n\n");



       while(true){
            System.out.print("Pesquisar: ");
            String p = input.next();
            System.out.println();
            x.pesquisar(p);
            System.out.println("------------------------");
            System.out.println();

        }






    }


}

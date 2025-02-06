package pucpr;

import java.io.*;
import java.net.Socket;
import java.util.zip.ZipOutputStream;

class ConsoleOutputStream extends OutputStream
{
    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        System.out.print("Chamou write(byte[], off, len): ");
        System.out.println(new String(b));
    }

    @Override
    public void write(int b) throws IOException {
        System.out.print("Chamou write(int): ");
        char ch = (char)b;
        System.out.println(ch);
    }
}
public class Main {
    public static void main(String[] args) throws IOException {
        ConsoleOutputStream out = new ConsoleOutputStream();
        //FileOutputStream fos = new FileOutputStream(new File("texto.txt"));
        BufferedOutputStream bos = new BufferedOutputStream(out);
        PrintWriter pw = new PrintWriter(bos);
        pw.printf("Abacate!%n");
        pw.flush();
    }
}

import java.util.Scanner;

public class cesar {

    public static void main(String[] args) {
        System.out.println(Encrypt("ABC",1));
        System.out.println(Decrypt("BCD",1));
    }

    public static String Encrypt(String texto, int key){
        String crypt = "";
        for(int i = 0 ; i < texto.length() ; i++){
            char myChar = texto.charAt(i);
            int meu_ascii = (int) myChar;
            meu_ascii += key;
            char newChar = (char) meu_ascii;
            crypt += new Character(newChar).toString();
        }
        return crypt;
    }
    public static String Decrypt(String texto, int key) {
        String decrypt = "";
        for (int i = 0; i < texto.length(); i++) {
            char myChar = texto.charAt(i);
            int meu_ascii = (int) myChar;
            meu_ascii -= key;
            char newChar = (char) meu_ascii;
            decrypt += new Character(newChar).toString();
        }
        return decrypt;
    }
}

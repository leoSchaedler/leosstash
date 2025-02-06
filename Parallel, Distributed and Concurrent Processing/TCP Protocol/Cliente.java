import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;


public class Cliente {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub

        // abre conexão com o servidor
        try {
            Socket s = new Socket("127.0.0.1", 5000);
            System.out.println( "Cliente: conexao feita" );

            DataOutputStream saida = new DataOutputStream( s.getOutputStream());

            saida.writeInt( 10 );
            saida.writeFloat( 3.14F );

            DataInputStream entrada = new DataInputStream( s.getInputStream());

            double resultado = entrada.readDouble();
            //float resultado = entrada.readFloat(); // pode?
            System.out.println( "resultado = " + resultado );

            //float resto = entrada.readFloat();
            //System.out.println( "resto = " + resto );

            s.close(); // encerra a conexao com o servidor
            System.out.println( "Cliente: conexao encerrada");
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }

}

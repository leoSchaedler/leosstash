import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Servidor {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub

        try {
            ServerSocket ss = new ServerSocket( 5000 );

            while (true)
            {
                System.out.println( "Servidor aguardando um cliente ...");
                Socket t = ss.accept(); // bloqueia até receber pedido de conexão do cliente
                System.out.println( "Servidor: conexao feita");

                Trabalhador trab = new Trabalhador( t );
                trab.start();

            }

            //ss.close();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}

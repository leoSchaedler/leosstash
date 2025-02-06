import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;


public class Trabalhador extends Thread {

    private Socket t;

    public Trabalhador( Socket t )
    {
        this.t = t;
    }

    public void run()
    {
        try
        {
            DataInputStream entrada = new DataInputStream( t.getInputStream());
            DataOutputStream saida = new DataOutputStream( t.getOutputStream());

            int k = entrada.readInt();
            float f = entrada.readFloat();
            System.out.println( "Recebidos: " + k + " e " + f );

            Thread.sleep( 5000 ); // dorme 5 segundos

            double produto = k * f;
            //float produto = k * f;
            System.out.println( "produto = " + produto );
            saida.writeDouble( produto );
            //saida.writeFloat( produto );

            t.close();
            System.out.println( "Servidor: conexao encerrada");
        }
        catch( Exception e )
        {
            System.out.println( e );
        }
    }
}

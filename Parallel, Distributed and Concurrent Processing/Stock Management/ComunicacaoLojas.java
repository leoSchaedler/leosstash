package br.pucpr;

import br.pucpr.Servidor.ProtocoloMultiplaComunicacao.ProtocoloComunicacaoLoja.LeituraLojas;
import br.pucpr.Servidor.Servidor;

import java.net.*;
import java.util.*;
import java.util.concurrent.Semaphore;

public class ComunicacaoLojas extends Thread{

    protected static final ArrayList<String> infos = new ArrayList<>();
    private static final Semaphore mutexSaves = new Semaphore(1);

    //TODO - Comunicação UDP
    //Multicast
    private final String addressGroup = Servidor.goupAddress_Str;
    private final int porta = Servidor.multcastPort;
    //Unicast
    private  int porta_l01;
    private  int porta_l02;
    private  int porta_l03;




    public ComunicacaoLojas(int porta_l01, int porta_l02, int porta_l03) throws Exception{
        this.porta_l01 = porta_l01;
        this.porta_l02 = porta_l02;
        this.porta_l03 = porta_l03;
    }

    public static void accessInfos (String info) throws Exception{
        mutexSaves.acquire();
            infos.add(info);
        mutexSaves.release();
    }

    public static String[] accessInfos () throws Exception{
        mutexSaves.acquire();
            String[] aux = new String[infos.size()];
            for (int i = 0; i < infos.size(); i++)
                aux[i] = infos.get(i);
        mutexSaves.release();
        return aux;
    }

    //Envio da categoria
    public String envio_multcast (String address, int porta, String data) throws Exception{
        InetAddress group = InetAddress.getByName(address);
        MulticastSocket multicastSocket = new MulticastSocket(porta);

        DatagramPacket datagramPacket = new DatagramPacket(data.getBytes(), data.length(), group, porta);
        multicastSocket.send(datagramPacket);
        multicastSocket.close();

        return data;
    }

    private String prepararEnvio(){
        StringBuilder result = new StringBuilder();
        for (String s : infos)
            result.append(s).append("!");
        return String.valueOf(result);
    }


    @Override
    public void run() {
        while (true)
        try {

            Scanner sc = new Scanner(System.in);
            System.out.print("algum numero: ");
            String m = sc.nextLine();
            System.out.println();
            System.out.println(envio_multcast(addressGroup, porta, m));

            LeituraLojas a = new LeituraLojas("A", porta_l01);
            LeituraLojas b = new LeituraLojas("B", porta_l02);
            LeituraLojas c = new LeituraLojas("C", porta_l03);

            a.start();
            b.start();
            c.start();

            a.join();
            b.join();
            c.join();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}

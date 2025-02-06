package br.pucpr.Lojas;

import br.pucpr.Auxiliares.Sleep;
import br.pucpr.Servidor.Servidor;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class Loja extends Thread{


    private final Catalogo catalogo;
    private final String idLoja;

    private String data;

    //todo: Comunicação - UDP
    //Unicast
    private final int portaServidor_mult;
    private DatagramSocket socketServidor;
    private int portaServidor_uni;
    //Multicast


    public Loja(String idLoja, int datagramSocket, int portaServidor_uni, int portaServidor_mult) throws Exception{
        this.idLoja = idLoja;
        this.catalogo = new Catalogo();
        //Unicast
        socketServidor = new DatagramSocket(datagramSocket);
        this.portaServidor_uni = portaServidor_uni;
        //Multcast
        this.portaServidor_mult = portaServidor_mult;
    }

    @Override
    public void run() {
        while (true)
            try {
                System.out.print(Servidor.legenda.Loja);
                System.out.println(this.idLoja + ": ON");

                COMUNICACAO();
            } catch (Exception e){
                e.printStackTrace();
            }
    }


    private String COMUNICACAO() throws Exception {

        String aux = receberMultcast();

        System.out.println("Loja: " + idLoja + " > recebido > " + aux);

        String mem = enviarUnicast(getStringao(correct(aux)), portaServidor_uni);

        System.out.println("Loja: " + idLoja + " > envio > " + mem);

        return aux;
    }

    //auxilixares

    private String correct(String a) throws Exception{
        String aux;
        switch (Integer.parseInt(a)){
            case 1:
                aux = "comidas";
                break;
            case 2:
                aux = "bebidas";
                break;
            case 3:
                aux = "produtos de limpeza";
                break;
            case 4:
                aux = "roupas";
                break;
            case 5:
                aux = "eletronicos";
                break;
            default:
                aux = idLoja + ",0;0,0";
        }
        return aux;
    }

    public String getStringao(String categoria){
        StringBuilder aux = new StringBuilder();
        String str = catalogo.getStringao(categoria);
        aux.append(idLoja).append(",").append(str).append("@");
        return String.valueOf(aux);
    }

    private String enviarUnicast(String data, int porta) throws Exception{
//        System.out.println("vou enviar: " + data);
        socketServidor.send(new DatagramPacket(
                data.getBytes(),
                data.length(),
                InetAddress.getLocalHost(),
                porta
        ));
//        System.out.println("enviei: " + data);
        return data;
    }

    private String receberMultcast() throws Exception {
        InetAddress group = InetAddress.getByName(Servidor.goupAddress_Str);
        MulticastSocket multicastSocket = new MulticastSocket(Servidor.multcastPort);
        multicastSocket.joinGroup(group);

        byte[] buffer = new byte[1];
        DatagramPacket datagramPacket = new DatagramPacket(buffer, 0, buffer.length);
        multicastSocket.receive(datagramPacket);

        return new String(buffer);
    }





    //LIXO
    public void comunicacaoCliente() throws Exception{

        data = receberMultcast();
//        System.out.println(Servidor.legenda.Loja + this.idLoja + " Recebido: " + data);
        String aux;
        switch (Integer.parseInt(data)){
            case 1:
                aux = getStringao("comidas");
                break;
            case 2:
                aux = getStringao("bebidas");
                break;
            case 3:
                aux = getStringao("produtos de limpeza");
                break;
            case 4:
                aux = getStringao("roupas");
                break;
            case 5:
                aux = getStringao("eletronicos");
                break;
            default:
                aux = idLoja + ",0;0,0";
        }
        printarConsumidor(data);
//        System.out.println(Servidor.legenda.Loja + this.idLoja + " getStringao: " + aux);
        new Sleep(100, 500).dormir();
        String mem = enviarUnicast(aux, portaServidor_uni);
//        System.out.println(Servidor.legenda.Loja + this.idLoja + " Enviado: " + mem);

    }

    private void printarConsumidor(String info){
        System.out.print(Servidor.legenda.Consumidor + "Consumidor: ");
        System.out.print(Servidor.legenda.Servidor + info);
        System.out.println();
    }
}

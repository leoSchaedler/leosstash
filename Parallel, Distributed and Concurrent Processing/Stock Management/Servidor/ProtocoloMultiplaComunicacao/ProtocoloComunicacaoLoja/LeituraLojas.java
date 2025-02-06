package br.pucpr.Servidor.ProtocoloMultiplaComunicacao.ProtocoloComunicacaoLoja;

import br.pucpr.Servidor.ProtocoloMultiplaComunicacao.ComunicacaoClienteLoja;
import br.pucpr.Servidor.Servidor;

import java.io.*;
import java.net.*;

public class LeituraLojas extends Thread{

    private final String nomeLoja;

    //TODO: Comunicação - UDP
    private  DatagramSocket clientSocket;
    private DatagramPacket datagramPacket;

    public LeituraLojas (String nomeLoja, int porta) throws Exception{
        this.nomeLoja = nomeLoja;
        clientSocket = new DatagramSocket(porta);
    }

    private String recebeLoja() throws Exception{
        String compilado;
        byte[] buffer;
        try {
            buffer = new byte[65507];
            clientSocket.setSoTimeout(Servidor.acessoTMax());
//            System.out.println(nomeLoja + ": esperando");
            datagramPacket = new DatagramPacket(buffer, 0, buffer.length);
            clientSocket.receive(datagramPacket);
//            System.out.println(nomeLoja + ": quase");
            compilado = new String(datagramPacket.getData());
            System.out.println("LeituraLojas >>" + nomeLoja + " >> recebido: " + compilado);
        }catch (IOException e){
            compilado = Servidor.error_Time;
        }finally {
            clientSocket.close();
        }
        return compilado;
    }

    private void saveData(String stringao) throws Exception{
        ComunicacaoClienteLoja.accessInfos(stringao);
    }

    @Override
    public void run() {
        try {

            String aux = recebeLoja();
            saveData(aux);

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}

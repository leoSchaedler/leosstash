package br.pucpr.Servidor.ProtocoloMultiplaComunicacao.ProtocoloCliente;

import br.pucpr.Servidor.ProtocoloMultiplaComunicacao.ComunicacaoClienteLoja;
import br.pucpr.Servidor.Servidor;

import java.io.*;
import java.net.*;
import java.util.*;

public class ComunicacaoConsumidor extends Thread{

    private final ServerSocket server;
    private Socket consumidor;
    private PrintWriter sendData;

    private final ArrayList<Integer> memPortas;
    private int autoPortas;

    public ComunicacaoConsumidor (ServerSocket serverSocket) {
        this.server = serverSocket;

        this.memPortas = new ArrayList<>();
        this.autoPortas = 5888;
    }

    @Override
    public void run() {
        while (true)
            try {

                controladorConsumidor();

            } catch (Exception e){
                e.printStackTrace();
            }
    }

    private void controladorConsumidor() throws Exception {
        consumidor = server.accept();
        sendData = new PrintWriter(consumidor.getOutputStream());

        printarConsumidor("Nova porta enviada: " + autoPortas);

        enviar(sendData, String.valueOf(autoPortas));

        printarConsumidor("new SubComunicacaoConsumidor/testeKK");

//        new SubComunicacaoConsumidor(autoPortas).start();

        new ComunicacaoClienteLoja(autoPortas).start();

        memPortas.add(++autoPortas);
    }





    private void saveSearch(int nOfProducts, String nameOfCateegory) throws Exception {
        Servidor.acessoMem(String.valueOf(new Date()), autoPortas, nameOfCateegory, nOfProducts);
    }

    private String receber(Socket socket) throws Exception {
        return new BufferedReader(new InputStreamReader(socket.getInputStream())).readLine();
    }

    private String enviar(PrintWriter printWriter, String data) throws Exception {
        printWriter.println(data);
        printWriter.flush();
        return data;
    }

    private void printarConsumidor(String info){
        System.out.print(Servidor.legenda.Consumidor + "Consumidor: ");
        System.out.print(Servidor.legenda.Servidor + info);
        System.out.println();
    }

}

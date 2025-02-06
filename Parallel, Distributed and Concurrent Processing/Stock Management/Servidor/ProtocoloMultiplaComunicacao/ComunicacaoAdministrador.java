package br.pucpr.Servidor.ProtocoloMultiplaComunicacao;

import br.pucpr.Servidor.Servidor;

import java.io.*;
import java.net.*;

public class ComunicacaoAdministrador extends Thread{

    private final ServerSocket server;
    private Socket administrador;
    private PrintWriter sendData;

    private String data;


    public ComunicacaoAdministrador (ServerSocket serverSocket){
        this.server = serverSocket;
    }

    @Override
    public void run() {
        while (true)
        try {
            printarAdministrador(admCommunication());
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    //TODO: protocolo para o Adm vizualizar e modificar o Tmax
    private String admCommunication() throws Exception {
        System.out.print(Servidor.legenda.Servidor);
        administrador = server.accept();
        sendData = new PrintWriter(administrador.getOutputStream());

        printarAdministrador("Nova solicitação do Administrador");

        data = receber(administrador);

        if (data.equals("vizualizar"))
        {
            return VIZUALIZAR();
        }
        else if (data.equals("configurar"))
        {
            return CONFIGURAR(receber(administrador));
        }
        else if (data.equals("historico"))
        {
            return HISTORICO();
        }
        else if (data.equals("sair"))
        {
            return "Administrador encerrou a sessão";
        }
        else
        {
            return ERROR();
        }
    }


    private String VIZUALIZAR() throws Exception {
        String aux = String.valueOf(Servidor.acessoTMax());
        enviar(sendData, aux);
        return "O Tempo Máximo de Espera (Tmax) está configurado para " + aux + "ms";
    }

    private String CONFIGURAR(String data) throws Exception {
        Servidor.acessoTMax(Integer.parseInt(data));
        String success = "Tmax alterado para " + data + " ms com sucesso!";
        enviar(sendData, success);
        return success;
    }

    private String HISTORICO() throws Exception{
        enviar(sendData, Servidor.memoriaServidor.toSend());
        String win = "Historico enviado com sucesso";
        return win;
    }

    private String ERROR() {
        enviar(sendData, "ERROR -> Comando inválido");
        return "ERROR -> Comando inválido";
    }


    private String receber(Socket socket) throws Exception {
        return new BufferedReader(new InputStreamReader(socket.getInputStream())).readLine();
    }

    private String enviar(PrintWriter printWriter, String data) {
        printWriter.println(data);
        printWriter.flush();
        return data;
    }

    private void printarAdministrador(String info){
        System.out.print(Servidor.legenda.Adiministrador + "Administrador: ");
        System.out.print(Servidor.legenda.Servidor + info);
        System.out.println();
    }
}

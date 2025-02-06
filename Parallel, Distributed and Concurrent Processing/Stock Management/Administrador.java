package br.pucpr;

import br.pucpr.Auxiliares.Tabelas;
import br.pucpr.Servidor.ProtocoloSave.Data;
import br.pucpr.Servidor.Servidor;

import java.io.*;
import java.net.*;
import java.util.*;

public class Administrador {

    private final Scanner input;

    private String data;
    private String newTmax;

    //TODO: Comunicação
    private Socket socket;
    private PrintWriter sendData;

    public Administrador(){
        input = new Scanner(System.in);
    }

    public void comunicacaoServidor() throws Exception {
        this.socket = new Socket("localhost", Servidor.portaAdm);

        MENU(); //Interface Humano Computador

        data = input.nextLine();
        System.out.println();
        enviar(data);

        //Recebimento
        if (data.equals("vizualizar")){
            VIZUALIZAR();
            AGAIN();
        }
        else if (data.equals("configurar")){
            CONFIGURAR();
            AGAIN();
        }
        else if (data.equals("historico")){
            HISTORICO();
            AGAIN();
        }
        else if (data.equals("sair")){
            SAIR();
        }
        else {
            System.out.println("Comando inválido, tente novamente.");
            AGAIN();
        }

    }



    private void MENU(){
        System.out.print(Servidor.legenda.Adiministrador);
        System.out.println(".-------------------------------------------.");
        System.out.println("|            Bem Vindo(a) ao SBP            |");
        System.out.println("|    Sistema de Busca de Produtos Online    |");
        System.out.println("|===========================================|");
        System.out.println("|               Adiministrador              |");
        System.out.println("|===========================================|");
        System.out.println("|   Você tem acesso aos controles do Tempo  |");
        System.out.println("|   Máximo de Envio das mensagens.          |");
        System.out.println("|   Digite 'vizualizar' para ver o Tmax.    |");
        System.out.println("|   Digite 'configurar' para mudar o Tmax.  |");
        System.out.println("|   Digite 'historico' para ver os dados.   |");
        System.out.println("|===========================================|");
        System.out.println("|   Se deseja encerrar a sessão, digite     |");
        System.out.println("|   'sair'.                                 |");
        System.out.println("°-------------------------------------------°" + "\n");
        System.out.print("->> Comando: ");
    }

    private void VIZUALIZAR() throws Exception {
        String recived = receber();
        System.out.println("O Tempo Máximo de Espera (Tmax) está configurado para " + recived + " ms.");
    }

    private void CONFIGURAR() throws Exception {
        System.out.print("->> Tmax: ");
        newTmax = input.nextLine();

        enviar(newTmax);

        String success = receber();
        System.out.println(success);
    }

    private void HISTORICO() throws Exception {
        Tabelas.historicoTables(receber());
    }

    private void SAIR() throws Exception{
        System.out.println(".-------------------------------------------.");
        System.out.println("|    Sistema de Busca de Produtos Online    |");
        System.out.println("|===========================================|");
        System.out.println("|               Adiministrador              |");
        System.out.println("|===========================================|");
        System.out.println("|   Muito obrigado pela vizita!             |");
        System.out.println("|   Até outra hora.                         |");
        System.out.println("°-------------------------------------------°" + "\n");

        socket.close();
    }

    private void AGAIN() throws Exception {
        System.out.println();
        socket.close();
        comunicacaoServidor();
    }

    private String enviar(String data) throws Exception {
        this.sendData = new PrintWriter(socket.getOutputStream());
            sendData.println(data);
            sendData.flush();
        return data;
    }

    private String receber() throws Exception {
        return new BufferedReader(new InputStreamReader(socket.getInputStream())).readLine();
    }

}

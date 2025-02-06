package br.pucpr.Clientes;

import br.pucpr.Auxiliares.Tabelas;
import br.pucpr.Servidor.Servidor;

import java.io.*;
import java.net.*;
import java.util.*;

public class Consumidor {

    private final Scanner input;

    private String data;

    private String arrived;

    //TODO: Comunicação - TCP
    private Socket socketFixo;
    private Socket socketVariavel;
    private PrintWriter sendData;
    private int novaPorta;

    public Consumidor() throws Exception{
        input = new Scanner(System.in);


        BEGIN_CONNECTION();
    }


    public void comunicacaoServidor() throws Exception {

        System.out.print(Servidor.legenda.Consumidor);

        this.socketVariavel = new Socket("localhost", novaPorta);

        MENU(); //Interface Humano Computador

        data = input.nextLine();


        if (check(data))
        {
            enviar(data);

            RECEBER();

            IMPRIMIR_CATALOGOS();

            AGAIN();
        }
        else if (data.equals("sair"))
        {
            enviar(data);
            SAIR();
        }
        else
        {
            System.out.println("Error >> comando '" + data + "' invalido ... tente novamente");
            AGAIN();
        }

    }





    private void BEGIN_CONNECTION() throws Exception{
        this.socketFixo = new Socket("localhost", Servidor.portaCon);
//        System.out.println("conexão com o servidor estabelecida");
        novaPorta = Integer.parseInt(receber(socketFixo));
//        System.out.println("Nova porta recebida: " + novaPorta);
        socketFixo.close();
//        System.out.println("nova conexão criada");
    }

    private void MENU(){
        System.out.print(Servidor.legenda.Consumidor);
        System.out.println(".-------------------------------------------.");
        System.out.println("|            Bem Vindo(a) ao SBP            |");
        System.out.println("|    Sistema de Busca de Produtos Online    |");
        System.out.println("|===========================================|");
        System.out.println("|                Consumidor                 |");
        System.out.println("|===========================================|");
        System.out.println("|   Digite o código da categoria do produto |");
        System.out.println("|   procurado e deixe eu te mostrar o que   |");
        System.out.println("|   eu encontro em minhas lojas cadastradas |");
        System.out.println("|===========================================|");
        System.out.println("|       - 1 -> comidas                      |");
        System.out.println("|       - 2 -> bebidas                      |");
        System.out.println("|       - 3 -> produtos de limpeza          |");
        System.out.println("|       - 4 -> roupas                       |");
        System.out.println("|       - 5 -> eletronicos                  |");
        System.out.println("|===========================================|");
        System.out.println("|   Se deseja encerrar a sessão, digite     |");
        System.out.println("|   'sair'.                                 |");
        System.out.println("°-------------------------------------------°" + "\n");
        System.out.print("->> Comando: ");
    }

    private void RECEBER() throws Exception{
        System.out.println("    Informação enviada -> " + conver(data));

        arrived = receber(socketVariavel);

        System.out.println("    Informações recebidas: ");
        System.out.println();
    }

    private void IMPRIMIR_CATALOGOS(){
        if (arrived.length() == 0)
            System.out.println("        - Nenhum produto foi encontrado");
        else {
            String[] stringao = arrived.split("!");
            for (String s : stringao)
                    Tabelas.produtosTable(s);
//                System.out.println(s);

        }
    }

    private void AGAIN() throws Exception {
        System.out.println();
        socketVariavel.close();
        comunicacaoServidor();
    }

    private void SAIR() throws Exception{
        System.out.println();
        System.out.println(".-------------------------------------------.");
        System.out.println("|    Sistema de Busca de Produtos Online    |");
        System.out.println("|===========================================|");
        System.out.println("|                Consumidor                 |");
        System.out.println("|===========================================|");
        System.out.println("|   Muito obrigado pela vizita!             |");
        System.out.println("|   Até outra hora.                         |");
        System.out.println("°-------------------------------------------°" + "\n");

        socketVariavel.close();
    }


    //auxiliares

    private boolean check (String input){
        String[] aux = {"1", "2", "3", "4", "5"};
        for (String s : aux)
            if (s.equals(input))
                return true;
        return false;
    }

    private String enviar(String data) throws Exception {
        this.sendData = new PrintWriter(socketVariavel.getOutputStream());
        sendData.println(data);
        sendData.flush();
        return data;
    }

    private String receber(Socket socket) throws Exception {
        return new BufferedReader(new InputStreamReader(socket.getInputStream())).readLine();
    }

    private String conver(String data){
        switch (Integer.parseInt(data)){
            case 1:
                return "comidas";
            case 2:
                return "bebidas";
            case 3:
                return "produtos de limpeza";
            case 4:
                return "roupas";
            case 5:
                return "eletronicos";
            default:
                return "error";
        }
    }

}

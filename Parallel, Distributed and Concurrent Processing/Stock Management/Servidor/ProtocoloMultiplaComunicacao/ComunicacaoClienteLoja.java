package br.pucpr.Servidor.ProtocoloMultiplaComunicacao;

import br.pucpr.Servidor.ProtocoloMultiplaComunicacao.ProtocoloComunicacaoLoja.LeituraLojas;
import br.pucpr.Servidor.Servidor;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.Semaphore;

public class ComunicacaoClienteLoja extends Thread{

    private final int id;

    private String data;


    //TODO: Saves - loja
    protected static final ArrayList<String> infos = new ArrayList<>();
    private static final Semaphore mutexSaves = new Semaphore(1);

    private String dataReady;

    //TODO: Comunicação - TCP
    private final ServerSocket server;
    private Socket consumidor;
    private PrintWriter sendData;

    //TODO: Comunicação - UDP
    //Multicast
    private final String addressGroup;
    private final int porta_mult;
    //Unicast
    private final int porta_l01;
    private final int porta_l02;
    private final int porta_l03;
    //Lojas
    private LeituraLojas loja01;
    private LeituraLojas loja02;
    private LeituraLojas loja03;


    public ComunicacaoClienteLoja(int id) throws Exception {
        this.id = id;
        //TCP
        this.server = new ServerSocket(id);
        //UDP
        //Multcast
        this.addressGroup = Servidor.goupAddress_Str;
        this.porta_mult = Servidor.multcastPort;
        //Unicast
        this.porta_l01 = Servidor.portasLojas_Unicast[0];
        this.porta_l02 = Servidor.portasLojas_Unicast[1];
        this.porta_l03 = Servidor.portasLojas_Unicast[2];
    }


    @Override
    public void run() {
        while (true)
            try {


                    consComunicacao();


            } catch (Exception e){
                e.printStackTrace();
            }
    }

    private void consComunicacao() throws Exception {
        System.out.print(Servidor.legenda.Servidor);
        consumidor = server.accept();
        sendData = new PrintWriter(consumidor.getOutputStream());

        printarConsumidor("Nova solicitação do cliente: " + id);

        data = receber(consumidor);

        printarConsumidor(id + " informação recebida -> " + data);

        if (check(data))
        {
            Servidor.mutex.acquire();

            envio_multcast(data);

            NOVAS_LOJAS();

            LER_DADOS();

            enviar(sendData, dataReady);
            printarConsumidor("enviado");

            Servidor.mutex.release();
        }
        else if (data.equals("sair"))
        {
            printarConsumidor(id + " encerrou a sessão");
        }
        else
            ERROR();
    }





    private void NOVAS_LOJAS() throws Exception{
        printarConsumidor("começou a busca nas lojas");

        loja01 = new LeituraLojas(Servidor.nomeLojas[0], porta_l01);
        loja02 = new LeituraLojas(Servidor.nomeLojas[1], porta_l02);
        loja03 = new LeituraLojas(Servidor.nomeLojas[2], porta_l03);

        loja01.start();
        loja02.start();
        loja03.start();

        loja01.join();
        loja02.join();
        loja03.join();

        printarConsumidor("terminou a busca nas lojas");
    }

    private void LER_DADOS() throws Exception{

        int nProdutos = 0;

        StringBuilder aux = new StringBuilder();

        for (String s : accessInfos()){

            if (!outOf(s)){
                printarConsumidor("diferente de outTime");
                nProdutos += lerNumeros(s);
                aux.append(s).append("!");
            }
            else {
                printarConsumidor("deu outTime");
            }
        }

        dataReady = String.valueOf(aux);

        saveSearch(nProdutos, conver(data));

        infos.clear();
    }

    private void ERROR() throws Exception{
        String msg = "ERROR -> Comando '" + data + "' inválido";
        enviar(sendData, msg);
        printarConsumidor(msg);
    }


    //staticas
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

    private String saveSearch(int nOfProducts, String nameOfCateegory) throws Exception {
        Servidor.acessoMem(String.valueOf(new Date()), id, nameOfCateegory, nOfProducts);
        return nameOfCateegory;
    }

    //ENVIOS

    //TCP
    private String receber(Socket socket) throws Exception {
        return new BufferedReader(new InputStreamReader(socket.getInputStream())).readLine();
    }

    private String enviar(PrintWriter printWriter, String data) throws Exception {
        printWriter.println(data);
        printWriter.flush();
        return data;
    }


    //UDP
    public String envio_multcast (String data) throws Exception{
        InetAddress group = InetAddress.getByName(Servidor.goupAddress_Str);
        MulticastSocket multicastSocket = new MulticastSocket(Servidor.multcastPort);

        DatagramPacket datagramPacket = new DatagramPacket(data.getBytes(), data.length(), group, Servidor.multcastPort);
        multicastSocket.send(datagramPacket);
        multicastSocket.close();

        return data;
    }


    //auxiliares

    private int lerNumeros(String stringao) throws Exception {
        String[] bucher = stringao.split(";");
        String[] billy = bucher[0].split(",");
        return Integer.parseInt(billy[1]);
    }

    private void printarConsumidor(String info){
        System.out.print(Servidor.legenda.Consumidor + "Consumidor: ");
        System.out.print(Servidor.legenda.Servidor + info);
        System.out.println();
    }

    private boolean outOf(String data){
        return data.equals(Servidor.error_Time);
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

    private boolean check (String input){
        String[] aux = {"1", "2", "3", "4", "5"};
        for (String s : aux)
            if (s.equals(input))
                return true;
        return false;
    }

}

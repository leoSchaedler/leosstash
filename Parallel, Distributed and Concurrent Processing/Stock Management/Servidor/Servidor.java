package br.pucpr.Servidor;

import br.pucpr.Auxiliares.Legendar;
import br.pucpr.Servidor.ProtocoloMultiplaComunicacao.ComunicacaoAdministrador;
import br.pucpr.Servidor.ProtocoloMultiplaComunicacao.ProtocoloCliente.ComunicacaoConsumidor;
import br.pucpr.Servidor.ProtocoloSave.MemoriaServer;

import java.net.*;
import java.util.concurrent.Semaphore;

public class Servidor {

    //TODO: Staticos
    public static final Legendar legenda = new Legendar();

    //Portas de cada comunicação TCP
    public static final int portaAdm = 5788;
    public static final int portaCon = 5688;

    //Portas de cada comunicação UDP
    public static final Semaphore mutex = new Semaphore(1);
    //unicast
    public static final String[] nomeLojas = {"Loja A", "Loja B", "Loja C"};
    public static final int[] portasLojas_Unicast = {5002, 5003, 5004};
    public static final int[] datagramSockets = {50000, 50001, 50002};
    public static final int nLojas = portasLojas_Unicast.length;
    //multcast
    public static final int multcastPort = 3456;
    public static final String goupAddress_Str = "225.6.7.8";


    //Protocolo para salvar os dados
    public static final MemoriaServer memoriaServidor = new MemoriaServer();
    public static final Semaphore mutexMem = new Semaphore(1);

    //Tempo Maximo de espera
    public static int tMax = 5000;
    public static final Semaphore mutexT = new Semaphore(1);

    //Mensagens
    public static final String error_Time = "outOfTime";



    //Todo: Comunicação
    private final ServerSocket server_Adm;
    private final ServerSocket server_Cons;



    public Servidor() throws Exception {
        this.server_Adm = new ServerSocket(portaAdm);       //Server
        this.server_Cons = new ServerSocket(portaCon);       //Server

        System.out.println(legenda.Servidor + "servidor: ON");
    }



    //TODO: 'Protegidas'

    public static int acessoTMax() throws Exception {
        mutexT.acquire();
            int aux = tMax;
        mutexT.release();
        return aux;
    }

    public static void acessoTMax(int newTmax) throws Exception {
        mutexT.acquire();
            tMax = newTmax;
        mutexT.release();
    }

    public static MemoriaServer acessoMem() throws Exception {
        mutexMem.acquire();
            MemoriaServer aux = memoriaServidor;
        mutexMem.release();
        return aux;
    }

    public static void acessoMem(String diahora, int id, String textoParcial, int nProdutos) throws Exception {
        mutexMem.acquire();
            memoriaServidor.addSave(diahora, id, textoParcial, nProdutos);
        mutexMem.release();
    }


    //TODO: Comunicação

    public void comunicacao() throws Exception {

        new ComunicacaoAdministrador(server_Adm).start();

        new ComunicacaoConsumidor(server_Cons).start();

    }

}

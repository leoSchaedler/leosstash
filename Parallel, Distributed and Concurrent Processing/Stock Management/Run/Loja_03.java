package br.pucpr.Run;

import br.pucpr.Lojas.Loja;
import br.pucpr.Servidor.Servidor;

public class Loja_03 {

    public static void main(String[] args) throws Exception{

        new Loja(Servidor.nomeLojas[2], Servidor.datagramSockets[2],Servidor.portasLojas_Unicast[2], Servidor.multcastPort).start();
    }

}

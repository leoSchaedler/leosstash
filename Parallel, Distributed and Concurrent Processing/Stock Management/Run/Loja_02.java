package br.pucpr.Run;

import br.pucpr.Lojas.Loja;
import br.pucpr.Servidor.Servidor;

public class Loja_02 {

    public static void main(String[] args) throws Exception{

        new Loja(Servidor.nomeLojas[1], Servidor.datagramSockets[1],Servidor.portasLojas_Unicast[1], Servidor.multcastPort).start();

    }

}

package br.pucpr.Run;

import br.pucpr.Lojas.Loja;
import br.pucpr.Servidor.Servidor;

public class Loja_01 {

    public static void main(String[] args) throws Exception{

        new Loja(Servidor.nomeLojas[0], Servidor.datagramSockets[0],Servidor.portasLojas_Unicast[0], Servidor.multcastPort).start();

    }

}

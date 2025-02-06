package br.pucpr.Servidor.ProtocoloSave;

import java.util.*;

public class MemoriaServer {

    private ArrayList<Data> saves;

    public MemoriaServer(){
        this.saves = new ArrayList<>();
    }

    public ArrayList<Data> getSaves() {
        return saves;
    }

    public void addSave(String diahora, int id, String textoParcial, int nProdutos){
        saves.add(new Data(diahora, id, textoParcial, nProdutos));
    }


    public String toSend(){
        if (saves.size() != 0){
            StringBuilder stringao = new StringBuilder();
            for (Data d : saves)
                stringao.append(d.concatenate()).append(";");
            return String.valueOf(stringao);
        }else {
            return "Nenhum dado foi registrado até o momento";
        }
    }

}

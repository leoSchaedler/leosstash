package br.pucpr.Servidor.ProtocoloSave;

public class Data {

    private final String diahora;
    private final int id;
    private final String textoParcial;
    private final int nProdutos;

    public Data(String diahora, int id, String textoParcial, int nProdutos){
        this.diahora = diahora;
        this.id = id;
        this.textoParcial = textoParcial;
        this.nProdutos = nProdutos;
    }

    public int getId() {
        return id;
    }

    public int getnProdutos() {
        return nProdutos;
    }

    public String getDiahora() {
        return diahora;
    }

    public String getTextoParcial() {
        return textoParcial;
    }

    public String concatenate(){
        StringBuilder concatenar = new StringBuilder();
        concatenar.append(diahora).append(",");
        concatenar.append(id).append(",");
        concatenar.append(textoParcial).append(",");
        concatenar.append(nProdutos);
        return String.valueOf(concatenar);
    }
}

package br.pucpr.Auxiliares;

public class Legendar implements Legenda {

    /*
    BLACK("\033[0;30m"),    // BLACK
    RED("\033[0;31m"),      // RED
    GREEN("\033[0;32m"),    // GREEN
    YELLOW("\033[0;33m"),   // YELLOW
    BLUE("\033[0;34m"),     // BLUE
    MAGENTA("\033[0;35m"),  // MAGENTA
    CYAN("\033[0;36m"),     // CYAN
    WHITE("\033[0;37m"),    // WHITE
     */

    public final String Consumidor;
    public final String Adiministrador;
    public final String Servidor;
    public final String Loja;
    public final String Sistema;

    public Legendar(){
        this.Consumidor = legendar("amarelo", false, false);
        this.Adiministrador = legendar("azul", false, false);
        this.Servidor = legendar("vermelho", false, false);
        this.Loja = legendar("magenta", false, false);
        this.Sistema = legendar("verde", true, false);
    }

    @Override
    public String legendar(String cor, boolean negrito, boolean sublinhado) {
        String mem = "\033[";
        if (negrito) mem += "1;";
        else if (sublinhado) mem += "4;";
        switch (cor){
            case "preto":
                mem += "30";
                break;
            case "vermelho":
                mem += "31";
                break;
            case "verde":
                mem += "32";
                break;
            case "amarelo":
                mem += "33";
                break;
            case "azul":
                mem += "34";
                break;
            case "magenta":
                mem += "35";
                break;
            case "ciano":
                mem += "36";
                break;
            default:
                mem += "37";
                break;
        }
        mem += "m";
        return mem;
    }
}

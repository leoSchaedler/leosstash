package br.pucpr;

public class Legendas {

    public static String getColor(int n){
        switch (n){
            case -1:
                return "\033[0;95m";   // MAGENTA
            case 0:
                return  "\033[0;32m";   // GREEN
            case 1:
                return "\033[0;34m";    // BLUE
            case 2:
                return "\033[0;35m";  // PURPLE
            case 3:
                return "\033[0;36m";    // CYAN
            case 4:
                return "\033[0;31m";     // RED
            case 5:
                return "\033[0;33m";  // YELLOW
            default:
                return "\033[0;37m";   // WHITE
        }
    }

    public static String getComeco(){
        return "\033[4;36m";    // CYAN_UNDERLINED
    }

    public static String getFim(){
        return "\033[4;31m";    // RED_UNDERLINED
    }


}

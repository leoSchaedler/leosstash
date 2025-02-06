public class BuscaBinaria {


    public static int buscar(int[] array, int chave) {
        return buscaBinaria(array, 0, array.length - 1, chave);
    }


    private static int buscaBinaria(int[] array, int menor, int maior, int chave) {
        int media = (maior + menor) / 2;
        int valorMeio = array[media];

        if (menor > maior)
            return -1;
        else if(valorMeio == chave)
            return media;
        else if (valorMeio < chave)
            return buscaBinaria(array, media+1, maior, chave);
        else
            return buscaBinaria(array, menor, media-1, chave);
    }



}

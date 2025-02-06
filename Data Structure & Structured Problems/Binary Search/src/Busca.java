public class Busca {



    public static int buscaBinaria(int[] lista, int elemento) {
        return buscaBinariaRecursiva(lista, 0, lista.length - 1, elemento);
    }


    private static int buscaBinariaRecursiva(int[] lista, int menor, int maior,int elemento) {
        int media = (maior + menor) / 2;
        int valorMeio = lista[media];

        if (menor > maior)
            return -1;
        else if(valorMeio == elemento)
            return media;
        else if (valorMeio < elemento)
            return buscaBinariaRecursiva(lista, media+1, maior, elemento);
        else
            return buscaBinariaRecursiva(lista, menor, media-1, elemento);
    }


}

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class MAIN {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        Random r = new Random();
        int TAMANHO;

        Integer[] numeros;

        Integer[] arrayDesordenadoQuickSort;
        Integer[] arrayParcialmenteOrdenadoQuickSort;
        Integer[] arrayOrdenadoDecQuickSort;

        Integer[] arrayDesordenadoMergeSort;
        Integer[] arrayParcialmenteOrdenadoMergeSort;
        Integer[] arrayOrdenadoDecMergeSort;

        Integer[] arrayDesordenadoSelectionSort;
        Integer[] arrayParcialmenteOrdenadoSelectionSort;
        Integer[] arrayOrdenadoDecSelectionSort;

        Integer[] arrayDesordenadoInsertionSort;
        Integer[] arrayParcialmenteOrdenadoInsertionSort;
        Integer[] arrayOrdenadoDecInsertionSort;

        Integer[] arrayDesordenadoShellSort;
        Integer[] arrayParcialmenteOrdenadoShellSort;
        Integer[] arrayOrdenadoDecShellSort;

        Integer[] arrayDesordenadoHeapsort;
        Integer[] arrayParcialmenteOrdenadoHeapsort;
        Integer[] arrayOrdenadoDecHeapsort;


        System.out.println("=================================================");
        System.out.println("*          Comparação empirica entre            *");
        System.out.println("*           Algoritmos de Ordenação             *");
        System.out.println("=================================================\n");
        System.out.println("- Algoritmos utilizados:\n");
        System.out.println("+ QuickSort");
        System.out.println("+ ShellSort");
        System.out.println("+ Heapsort");
        System.out.println("+ InsertionSort");
        System.out.println("+ SelectionSort");
        System.out.println("+ MergeSort");
        System.out.println("-------------------------------------------------\n");

        System.out.print("Digite o tamanho do array: ");
        TAMANHO = input.nextInt();
        System.out.println("\n\n\n\n\n");


        //Quick
        arrayDesordenadoQuickSort = new Integer[TAMANHO];
        arrayOrdenadoDecQuickSort = new Integer[TAMANHO];
        arrayParcialmenteOrdenadoQuickSort = new Integer[TAMANHO];

        //Merge
        arrayDesordenadoMergeSort = new Integer[TAMANHO];
        arrayParcialmenteOrdenadoMergeSort = new Integer[TAMANHO];
        arrayOrdenadoDecMergeSort = new Integer[TAMANHO];

        //SelectionSort
        arrayDesordenadoSelectionSort = new Integer[TAMANHO];
        arrayOrdenadoDecSelectionSort = new Integer[TAMANHO];
        arrayParcialmenteOrdenadoSelectionSort = new Integer[TAMANHO];

        //InsertionSort
        arrayDesordenadoInsertionSort = new Integer[TAMANHO];
        arrayOrdenadoDecInsertionSort = new Integer[TAMANHO];
        arrayParcialmenteOrdenadoInsertionSort = new Integer[TAMANHO];


        //ShellSort
        arrayDesordenadoShellSort = new Integer[TAMANHO];
        arrayOrdenadoDecShellSort = new Integer[TAMANHO];
        arrayParcialmenteOrdenadoShellSort = new Integer[TAMANHO];


        //Heapsort
        arrayDesordenadoHeapsort = new Integer[TAMANHO];
        arrayOrdenadoDecHeapsort = new Integer[TAMANHO];
        arrayParcialmenteOrdenadoHeapsort = new Integer[TAMANHO];

        numeros = new Integer[TAMANHO];

        for(int i = 0; i<TAMANHO; i++){
            numeros[i] = r.nextInt(100) * r.nextInt(100);
        }

       // System.out.printf("%d números aleatorios gerados\n\n\n", TAMANHO);

        for(int i=0; i<TAMANHO; i++){
            arrayDesordenadoQuickSort[i] = numeros[i];
            arrayOrdenadoDecQuickSort[i] = numeros[i];
            arrayParcialmenteOrdenadoQuickSort[i] = numeros[i];

            arrayDesordenadoMergeSort[i] = numeros[i];
            arrayOrdenadoDecMergeSort[i] = numeros[i];
            arrayParcialmenteOrdenadoMergeSort[i] = numeros[i];

            arrayDesordenadoSelectionSort[i] = numeros[i];
            arrayOrdenadoDecSelectionSort[i] = numeros[i];
            arrayParcialmenteOrdenadoSelectionSort[i] = numeros[i];

            arrayDesordenadoInsertionSort[i] = numeros[i];
            arrayOrdenadoDecInsertionSort[i] = numeros[i];
            arrayParcialmenteOrdenadoInsertionSort[i] = numeros[i];

            arrayDesordenadoShellSort[i] = numeros[i];
            arrayOrdenadoDecShellSort[i] = numeros[i];
            arrayParcialmenteOrdenadoShellSort[i] = numeros[i];

            arrayDesordenadoHeapsort[i] = numeros[i];
            arrayOrdenadoDecHeapsort[i] = numeros[i];
            arrayParcialmenteOrdenadoHeapsort[i] = numeros[i];
        }

        Arrays.sort(arrayParcialmenteOrdenadoQuickSort, 0, (TAMANHO/2));    //Ordenação Parcial
        Arrays.sort(arrayOrdenadoDecQuickSort, Collections.reverseOrder());         //Ordenação dec

        Arrays.sort(arrayParcialmenteOrdenadoMergeSort, 0, (TAMANHO/2));    //Ordenação Parcial
        Arrays.sort(arrayOrdenadoDecMergeSort, Collections.reverseOrder());         //Ordenação dec

        Arrays.sort(arrayParcialmenteOrdenadoSelectionSort, 0, (TAMANHO/2));    //Ordenação Parcial
        Arrays.sort(arrayOrdenadoDecSelectionSort, Collections.reverseOrder());         //Ordenação dec

        Arrays.sort(arrayParcialmenteOrdenadoInsertionSort, 0, (TAMANHO/2));    //Ordenação Parcial
        Arrays.sort(arrayOrdenadoDecInsertionSort, Collections.reverseOrder());         //Ordenação dec

        Arrays.sort(arrayParcialmenteOrdenadoShellSort, 0, (TAMANHO/2));    //Ordenação Parcial
        Arrays.sort(arrayOrdenadoDecShellSort, Collections.reverseOrder());         //Ordenação dec

        Arrays.sort(arrayParcialmenteOrdenadoHeapsort, 0, (TAMANHO/2));    //Ordenação Parcial
        Arrays.sort(arrayOrdenadoDecHeapsort, Collections.reverseOrder());         //Ordenação dec

        ///////////////////Quick Sort/////////////////////////////////

        //Array desordenado
        long startQuickD = System.currentTimeMillis();
        quickSort(arrayDesordenadoQuickSort,0,(TAMANHO-1));
        long endQuickD = System.currentTimeMillis();
        float tempoQuickD = (endQuickD - startQuickD) / 1000F;

        //Array Parcialmente Ordenado
        long startQuickP = System.currentTimeMillis();
        quickSort(arrayParcialmenteOrdenadoQuickSort,0,(TAMANHO-1));
        long endQuickP = System.currentTimeMillis();
        float tempoQuickP = (endQuickP - startQuickP) / 1000F;

        //Array Ordenado em ordem decrescente
        long startQuickO = System.currentTimeMillis();
        quickSort(arrayOrdenadoDecQuickSort,0,(TAMANHO-1));
        long endQuickO = System.currentTimeMillis();
        float tempoQuickO = (endQuickO - startQuickO) / 1000F;

        ///////////////////Merge Sort/////////////////////////////////

        //Array desordenado
        long startMergeD = System.currentTimeMillis();
        mergeSort(arrayDesordenadoMergeSort,0,(TAMANHO-1));
        long endMergeD = System.currentTimeMillis();
        float tempoMergeD = (endMergeD - startMergeD) / 1000F;

        //Array Parcialmente Ordenado
        long startMergeP = System.currentTimeMillis();
        quickSort(arrayParcialmenteOrdenadoMergeSort,0,(TAMANHO-1));
        long endMergeP = System.currentTimeMillis();
        float tempoMergeP = (endMergeP - startMergeP) / 1000F;

        //Array Ordenado em ordem decrescente
        long startMergeO = System.currentTimeMillis();
        quickSort(arrayOrdenadoDecMergeSort,0,(TAMANHO-1));
        long endMergeO = System.currentTimeMillis();
        float tempoMergeO = (endMergeO - startMergeO) / 1000F;

        ///////////////////Selection Sort/////////////////////////////////

        //Array desordenado
        long startSelectionD = System.currentTimeMillis();
        selectionSort(arrayDesordenadoSelectionSort);
        long endSelectionD = System.currentTimeMillis();
        float tempoSelectionD = (endSelectionD - startSelectionD) / 1000F;

        //Array Parcialmente Ordenado
        long startSelectionP = System.currentTimeMillis();
        selectionSort(arrayParcialmenteOrdenadoSelectionSort);
        long endSelectionP = System.currentTimeMillis();
        float tempoSelectionP = (endSelectionP - startSelectionP) / 1000F;

        //Array Ordenado em ordem decrescente
        long startSelectionO = System.currentTimeMillis();
        selectionSort(arrayOrdenadoDecSelectionSort);
        long endSelectionO = System.currentTimeMillis();
        float tempoSelectionO = (endSelectionO - startSelectionO) / 1000F;

        ///////////////////Insertion Sort/////////////////////////////////

        //Array desordenado
        long startInsertionD = System.currentTimeMillis();
        insertionSort(arrayDesordenadoInsertionSort);
        long endInsertionD = System.currentTimeMillis();
        float tempoInsertionD = (endInsertionD - startInsertionD) / 1000F;

        //Array Parcialmente Ordenado
        long startInsertionP = System.currentTimeMillis();
        insertionSort(arrayParcialmenteOrdenadoInsertionSort);
        long endInsertionP = System.currentTimeMillis();
        float tempoInsertionP = (endInsertionP - startInsertionP) / 1000F;

        //Array Ordenado em ordem decrescente
        long startInsertionO = System.currentTimeMillis();
        insertionSort(arrayOrdenadoDecInsertionSort);
        long endInsertionO = System.currentTimeMillis();
        float tempoInsertionO = (endInsertionO - startInsertionO) / 1000F;

        ///////////////////Shell Sort/////////////////////////////////

        //Array desordenado
        long startShellD = System.currentTimeMillis();
        ShellSort(arrayDesordenadoShellSort);
        long endShellD = System.currentTimeMillis();
        float tempoShellD = (endShellD - startShellD) / 1000F;

        //Array Parcialmente Ordenado
        long startShellP = System.currentTimeMillis();
        ShellSort(arrayParcialmenteOrdenadoShellSort);
        long endShellP = System.currentTimeMillis();
        float tempoShellP = (endShellP - startShellP) / 1000F;

        //Array Ordenado em ordem decrescente
        long startShellO = System.currentTimeMillis();
        ShellSort(arrayOrdenadoDecShellSort);
        long endShellO = System.currentTimeMillis();
        float tempoShellO = (endShellO - startShellO) / 1000F;

        ///////////////////Heapsort/////////////////////////////////

        //Array desordenado
        long startHeapD = System.currentTimeMillis();
        Heapsort.heapsort(arrayDesordenadoHeapsort);
        long endHeapD = System.currentTimeMillis();
        float tempoHeapD = (endHeapD - startHeapD) / 1000F;

        //Array Parcialmente Ordenado
        long startHeapP = System.currentTimeMillis();
        Heapsort.heapsort(arrayParcialmenteOrdenadoHeapsort);
        long endHeapP = System.currentTimeMillis();
        float tempoHeapP = (endHeapP - startHeapP) / 1000F;

        //Array Ordenado em ordem decrescente
        long startHeapO = System.currentTimeMillis();
        Heapsort.heapsort(arrayOrdenadoDecHeapsort);
        long endHeapO = System.currentTimeMillis();
        float tempoHeapO = (endHeapO - startHeapO) / 1000F;





        System.out.printf("Tamanho do conjunto: %d\n\n", TAMANHO);
        System.out.printf("Algoritmo \t%17s \t%22s \t%27s\n", "Desordenado","Quase ordenado","Ordem Decrescente");
        System.out.println("-------------------------------------------------------------------------------------");
        System.out.printf("QuickSort \t%15.2fs %22.2fs %27.2fs\n", tempoQuickD,tempoQuickP,tempoQuickO);
        System.out.printf("MergeSort \t%15.2fs %22.2fs %27.2fs\n", tempoMergeD,tempoMergeP,tempoMergeO);
        System.out.printf("SelectionSort %13.2fs %22.2fs %27.2fs\n", tempoSelectionD,tempoSelectionP,tempoSelectionO);
        System.out.printf("InsertionSort %13.2fs %22.2fs %27.2fs\n", tempoInsertionD,tempoInsertionP,tempoInsertionO);
        System.out.printf("ShellSort \t%15.2fs %22.2fs %27.2fs\n", tempoShellD,tempoShellP,tempoShellO);
        System.out.printf("Heapsort \t%15.2fs %22.2fs %27.2fs\n", tempoHeapD,tempoHeapP,tempoHeapO);


















    }

    public static void mergeSort(Integer[] array, int left, int right) {
        if (right <= left) return;
        int mid = (left+right)/2;
        mergeSort(array, left, mid);
        mergeSort(array, mid+1, right);
        merge(array, left, mid, right);
    }

    public static void merge(Integer[] array, int left, int mid, int right) {
        // calculating lengths
        int lengthLeft = mid - left + 1;
        int lengthRight = right - mid;

        // creating temporary subarrays
        int leftArray[] = new int [lengthLeft];
        int rightArray[] = new int [lengthRight];

        // copying our sorted subarrays into temporaries
        for (int i = 0; i < lengthLeft; i++)
            leftArray[i] = array[left+i];
        for (int i = 0; i < lengthRight; i++)
            rightArray[i] = array[mid+i+1];

        // iterators containing current index of temp subarrays
        int leftIndex = 0;
        int rightIndex = 0;

        // copying from leftArray and rightArray back into array
        for (int i = left; i < right + 1; i++) {
            // if there are still uncopied elements in R and L, copy minimum of the two
            if (leftIndex < lengthLeft && rightIndex < lengthRight) {
                if (leftArray[leftIndex] < rightArray[rightIndex]) {
                    array[i] = leftArray[leftIndex];
                    leftIndex++;
                }
                else {
                    array[i] = rightArray[rightIndex];
                    rightIndex++;
                }
            }
            // if all the elements have been copied from rightArray, copy the rest of leftArray
            else if (leftIndex < lengthLeft) {
                array[i] = leftArray[leftIndex];
                leftIndex++;
            }
            // if all the elements have been copied from leftArray, copy the rest of rightArray
            else if (rightIndex < lengthRight) {
                array[i] = rightArray[rightIndex];
                rightIndex++;
            }
        }
    }

    public static void selectionSort(Integer[] array) {
        for (int fixo = 0; fixo < array.length - 1; fixo++) {
            int menor = fixo;

            for (int i = menor + 1; i < array.length; i++) {
                if (array[i] < array[menor]) {
                    menor = i;
                }
            }
            if (menor != fixo) {
                int t = array[fixo];
                array[fixo] = array[menor];
                array[menor] = t;
            }
        }
    }

    public static void insertionSort(Integer[] vetor) {
        int j;
        int key;
        int i;

        for (j = 1; j < vetor.length; j++) {
            key = vetor[j];
            for (i = j - 1; (i >= 0) && (vetor[i] > key); i--) {
                vetor[i + 1] = vetor[i];
            }
            vetor[i + 1] = key;
        }
    }

    public static void ShellSort (Integer[] vet){
        int i , j , temp, size = vet.length;

        int incremento = 1;
        while(incremento < size) {
            incremento = 3 * incremento + 1;
        }

        while (incremento > 1) {
            incremento /= 3;

            for(i = incremento; i < size; i++) {
                temp = vet[i];
                j = i - incremento;
                while (j >= 0 && temp < vet[j]) {
                    vet[j + incremento] = vet[j];
                    j -= incremento;
                }
                vet [j + incremento] = temp;
            }
        }
    }

    private static void quickSort(Integer[] vetor, int inicio, int fim) {
        if (inicio < fim) {
            int posicaoPivo = separar(vetor, inicio, fim);
            quickSort(vetor, inicio, posicaoPivo - 1);
            quickSort(vetor, posicaoPivo + 1, fim);
        }
    }

    private static int separar(Integer[] vetor, int inicio, int fim) {
        int pivo = vetor[inicio];
        int i = inicio + 1, f = fim;
        while (i <= f) {
            if (vetor[i] <= pivo)
                i++;
            else if (pivo < vetor[f])
                f--;
            else {
                int troca = vetor[i];
                vetor[i] = vetor[f];
                vetor[f] = troca;
                i++;
                f--;
            }
        }
        vetor[inicio] = vetor[f];
        vetor[f] = pivo;
        return f;
    }





}

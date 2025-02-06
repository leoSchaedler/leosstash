package util;

public class ArrayPrinter {

    public static void printArray(Integer[] array){
        System.out.print("[");
        for (Integer i : array)
            System.out.print(i + ", ");
        System.out.print("]");
        System.out.println();

    }

}

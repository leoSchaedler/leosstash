package util;

import java.util.Scanner;

public class InputReaderService {

    private final Scanner input;

    public InputReaderService(){
        this.input = new Scanner(System.in);
    }

    public int ReadInteger (){
        int mem = Integer.MIN_VALUE;
        boolean validInput = false;
        while (!validInput){
            try {
                mem = Integer.parseInt(input.nextLine());
                validInput = true;
            } catch (Exception e){
                System.out.println();
                System.out.println("-- Comando inexiste --");
                System.out.println("Tente novamente!");
                System.out.print(">> ");
            }
        }
        if (mem == Integer.MIN_VALUE)
            return ReadInteger();
        return mem;
    }

    public int ReadInteger (int minValue, int maxValue){
        int mem = Integer.MIN_VALUE;
        boolean validInput = false;
        while (!validInput){
            try {
                mem = Integer.parseInt(input.nextLine());
                if (mem > maxValue || mem <= minValue){
                    System.out.println();
                    System.out.println("Atributo Inexistente ... tente algo entre " + (minValue+1) + " e " + maxValue);
                    System.out.print(">> ");
                    validInput = false;
                } else
                    validInput = true;
            } catch (Exception e){
                System.out.println();
                System.out.println("-- Comando inexiste --");
                System.out.println("Tente novamente!");
                System.out.print(">> ");
            }
        }
        if (mem == Integer.MIN_VALUE)
            return ReadInteger();
        return mem;
    }

    public int ReadInteger (String errorMessage){
        int mem = Integer.MIN_VALUE;
        boolean validInput = false;
        while (!validInput){
            try {
                mem = Integer.parseInt(input.nextLine());
                validInput = true;
            } catch (Exception e){
                System.out.println();
                System.out.println(errorMessage);
                System.out.print(">> ");
            }
        }
        if (mem == Integer.MIN_VALUE)
            return ReadInteger();
        return mem;
    }

}

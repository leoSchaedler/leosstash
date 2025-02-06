package services;

import java.util.Random;

public class RandomTimerService {

    private static final Integer valorMinimo = 5000;
    private static final Integer valorMaximo = 10000;

    private static final Random random = new Random();

    private static Integer getNextInt() {
        int gap = valorMaximo - valorMinimo;
        return random.nextInt(gap) + valorMinimo;
    }

    public static Long getRandomTimeProcessGenerator() {
        return (long) getNextInt();
    }

    public static Long getRandomTimeWaitGenerator() {
        int gap = valorMinimo - 500;
        return (long) random.nextInt(gap) + 500;
    }

}

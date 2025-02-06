package br.pucpr;

import br.pucpr.negocio.Animal;

import java.io.*;
import java.util.Random;

interface Dice {
    int roll();
}

class D6 implements Dice {
    private static final Random RND = new Random();
    @Override
    public int roll() {
        return RND.nextInt(6)+1;
    }
}

class DiceTest implements Dice {
    @Override
    public int roll() {
        return 3;
    }
}

class DiceFactory {
    public Dice createDice() {
        try (var reader = new BufferedReader(new FileReader(new File("config.txt")))) {
            var linha = reader.readLine();
            var clazz = Class.forName(linha);
            return (Dice) clazz.getDeclaredConstructor().newInstance();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
public class Reflection {
    private void attack() {
        Dice dice = new DiceFactory().createDice();
        int roll = dice.roll() + dice.roll();
        if (roll <= 6) {
            System.out.println("Acertou!");
        } else {
            System.out.println("Errou!");
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Reflection().attack();
        }
    }
}

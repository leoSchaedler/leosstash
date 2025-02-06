package pucpr;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Animal
{
    private String nome;
    private int idade;

    public Animal(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }
}

class Planeta
{
    private String nome;
    private float raio;
    private float massa;

    public Planeta(String nome, float raio, float massa) {
        this.nome = nome;
        this.raio = raio;
        this.massa = massa;
    }

    public String getNome() {
        return nome;
    }

    public float getRaio() {
        return raio;
    }

    public float getMassa() {
        return massa;
    }
}

class AnimalTableModel extends TableModel
{
    private String nome;
    private int idade;

    public Animal(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }
}

public class ExercicioTabela<E>{

    public static void imprimirAnimais(List<Animal> animais) {
        System.out.println("Nome             idade");
        for (int i = 0; i < animais.size(); i++) {
            var animal = animais.get(i);
            System.out.printf("%-17s", animal.getNome());
            System.out.printf("%02d", animal.getIdade());
            System.out.println();
        }
    }

    public static void imprimirPlanetas(List<Planeta> planetas) {
        System.out.println("Nome             raio   massa");
        for (int i = 0; i < planetas.size(); i++) {
            var planeta = planetas.get(i);
            System.out.printf("%-17s", planeta.getNome());
            System.out.printf("%02.2f   ", planeta.getRaio());
            System.out.printf("%03.5f", planeta.getMassa());
            System.out.println();
        }

    }

    public static void imprimirTableModel(TableModel model) {
        System.out.println(model.getHeader());
        for(int row = 0; row < model.getRowCount(); row++){
            for(int col = 0; col < model.getColumnCount(); col++){
                System.out.printf(model.getFormat(col), model.getValueAt(row, col));
            }
        }
            System.out.println();
    }


        public static void main(String[] args) {
        List<Animal> animais = Arrays.asList(
                new Animal("Preta", 8),
                new Animal("Mimosa", 15),
                new Animal("Labrador", 9)
        );
        imprimirAnimais(animais);
        System.out.println();

        List<Planeta> planetas = Arrays.asList(
            new Planeta("Mercurio", 1, 12.34f),
            new Planeta("Venus", 2.1f, 25.3f),
            new Planeta("Terra", 3.2f, 50.134f),
            new Planeta("Marte", 4.5f, 45.871f)
        );
        imprimirPlanetas(planetas);
    }
}

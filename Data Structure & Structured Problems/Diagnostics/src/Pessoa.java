import javax.swing.*;
import java.util.Scanner;

public class Pessoa {

    private String name;
    private String surname;
    private int age;
    private double pay;
    private int tamanho;
    private Pessoa [] pessoas;

    Scanner input = new Scanner( System.in);

    public Pessoa(String name, String surname, int age, double pay)
    {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.pay = pay;
    }

    public String getName() {
        return name;
    }

    public double getPay() {
        return pay;
    }

    public String getSurname() {
        return surname;
    }

    public int getAge() {
        return age;
    }

    public void showName ()
    {
        System.out.println("The name is "+ name + " " + surname);
    }

    public void showAge ()
    {
        System.out.println("The age is "+ age);
    }
    public void showPay()
    {
        System.out.println("The pay is "+ pay);
    }

    public void askUser4People(){
        System.out.println("Insira o tamanho do vetor de pessoas");
        tamanho = input.nextInt();
        pessoas = new Pessoa[tamanho];

        for(int i=0;i<pessoas.length;i++){
            System.out.print("Nome:");
            String nome = input.next();
            System.out.print("Sobrenome:");
            String sobrenome = input.next();
            System.out.print("Idade:");
            int idade = input.nextInt();
            System.out.print("Salário:");
            double salário = input.nextDouble();

            pessoas[i] = new Pessoa(nome,sobrenome,idade,salário);



        }


        for(int i=0;i<pessoas.length;i++){
            System.out.printf("Seu nome é "+pessoas[i].getName()+" "+pessoas[i].getSurname()+" e sua idade é: "+pessoas[i].getAge()+" e seu salário é: "+pessoas[i].getPay());
        }

    }

}

public class Teste {

    public static void main(String[] args) {
        Jogador jogador = new Jogador("Jão",10);
        Principiante principiante = new Principiante("Carlos",5,5);
        Profissional profissional = new Profissional("Especial",8,6);
        Senior senior = new Senior("Orlando",4,2,1);

        jogador.imprimir();
        principiante.imprimir();
        principiante.ganhar(2); // 7 e 5.2
        principiante.imprimir();
        principiante.perder(2); // 5 e 5
        principiante.imprimir();
        profissional.imprimir();
        profissional.ganhar(1); // 9 e 10
        profissional.imprimir();
        profissional.perder(1); // 8 e 6
        profissional.imprimir();
        senior.imprimir();
        senior.ganhar(1); // 5 e 5 e 4
        senior.imprimir();
        senior.perder(1); // 6 e 9 e 2
        senior.imprimir();
    }
}

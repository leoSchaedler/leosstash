public class Trabalho1_1 {
    public statica void main ( String[] args) {
        Trabalho1 semaforo = new Trabalho1();


        semaforo.VerCor();


        for ( int i = 0; i < 10; i++) {
            semaforo.MudaProximaCor();
            semaforo.VerCor();

        }

        semaforo.MudaCorFixa();
        semaforo.VerCor();

    }
}

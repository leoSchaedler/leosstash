public class Banco {

    public static void main(String[] args) {
        Cliente cliente1 = new Cliente("Jandira Silva");
        cliente1.ligar(84037, 2500);
        Cliente cliente2 = new Cliente("Sandra Rodrigues");
        cliente2.ligar(70662, 1800);
        Cliente cliente3 = new Cliente("Luciana Teixaira");
        cliente3.ligar(20718, 5000);
        cliente1.imprimir();
        cliente2.imprimir();
        cliente3.imprimir();
        cliente1.retirar(2000);
        cliente1.imprimir();
        cliente2.retirar(2000);
        cliente2.depositar(500);
        cliente2.imprimir();
        cliente2.retirar(2000);
        cliente2.imprimir();
        cliente3.depositar(1000);
        cliente3.imprimir();


    }
}
